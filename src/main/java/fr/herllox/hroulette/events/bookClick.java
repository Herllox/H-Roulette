package fr.herllox.hroulette.events;

import fr.herllox.hroulette.HRoulette;
import fr.herllox.hroulette.runnable.inventoryRunnable;
import fr.herllox.hroulette.utils.Util;
import fr.herllox.hroulette.utils.ecoUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class bookClick implements Listener {

    @EventHandler
    public void onBookClick(InventoryClickEvent e){
        if (e.getClickedInventory() == null) {
            e.setCancelled(true);
        }
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(HRoulette.getInstance().getConfig().getString("settings.inventory.menu").replace("&","§"))) {
            e.setCancelled(true);
            if (e.getInventory() == null || e.getCurrentItem() == null || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta()) {
                return;
            }

            if(e.getCurrentItem().getType() == Material.BOOK){
                if(e.getClick() == ClickType.RIGHT){

                    ItemStack it = e.getCurrentItem();
                    ItemMeta itx = it.getItemMeta();
                    List<String> list = itx.getLore();


                    if(itx.getLore().get(2).contains(Util.getIcon())){
                        list.set(2, list.get(2).replace(Util.getIcon(), Util.getNotIcon()));
                        list.set(3, list.get(3).replace(Util.getNotIcon(), Util.getIcon()));
                    }else if(itx.getLore().get(3).contains(Util.getIcon())){
                        list.set(3, list.get(3).replace(Util.getIcon(), Util.getNotIcon()));
                        list.set(4, list.get(4).replace(Util.getNotIcon(), Util.getIcon()));
                    }else if(itx.getLore().get(4).contains(Util.getIcon())){
                        list.set(4, list.get(4).replace(Util.getIcon(), Util.getNotIcon()));
                        list.set(2, list.get(2).replace(Util.getNotIcon(), Util.getIcon()));
                    }

                    itx.setLore(list);
                    it.setItemMeta(itx);
                    p.updateInventory();
                }else if(e.getClick() == ClickType.LEFT){

                    List<String> list = e.getCurrentItem().getItemMeta().getLore();
                    String extract = list.get(0).replace("&","§");
                    String removeColor = ChatColor.stripColor(extract);
                    Integer result = Integer.valueOf(removeColor.replaceAll("[^0-9]", ""));

                    if(result >= HRoulette.getInstance().getConfig().getInt("settings.min-bet")
                            && result <= HRoulette.getInstance().getConfig().getInt("settings.max-bet")) {
                        if(ecoUtil.getPlayerMoney(p) >= Double.valueOf(result)) {

                            ecoUtil.removePlayerMoney(p, Double.valueOf(result));

                            inventoryRunnable runnable = new inventoryRunnable(p, e.getInventory());
                            runnable.runTaskTimer(HRoulette.getInstance(), 3L,3L);

                            e.getInventory().setItem(47, new ItemStack(Material.BARRIER));
                            e.getInventory().setItem(51, new ItemStack(Material.BARRIER));

                            p.updateInventory();
                        }else{
                            p.sendMessage(HRoulette.getInstance().getConfig().getString("messages.need-money").replace("%prefix%", HRoulette.getInstance().getConfig().getString("messages.prefix")).replace("&", "§"));
                        }
                    }

                }
            }
        }
    }

}
