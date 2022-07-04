package fr.herllox.hroulette.events;

import fr.herllox.hroulette.HRoulette;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class limeClick implements Listener {

    @EventHandler
    public void onLimeClick(InventoryClickEvent e){

        if (e.getClickedInventory() == null) {
            e.setCancelled(true);
        }
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(HRoulette.getInstance().getConfig().getString("settings.inventory.menu").replace("&","§"))) {
            e.setCancelled(true);
            if (e.getInventory() == null || e.getCurrentItem() == null || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta()) {
                return;
            }

            if (e.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS_PANE) {

                List<String> lore = HRoulette.getInstance().getConfig().getStringList("settings.inventory.select-lore");
                ItemStack it = e.getInventory().getItem(49);
                ItemMeta itx = it.getItemMeta();
                List<String> list = itx.getLore();

                if(e.getClick() == ClickType.LEFT){
                    list.set(0, lore.get(0).replace("%bet%", moneyFormat(list.get(0), 100, p).toString()).replace("&","§"));
                }else if(e.getClick() == ClickType.RIGHT){
                    list.set(0, lore.get(0).replace("%bet%", moneyFormat(list.get(0), 1000, p).toString()).replace("&","§"));
                }else if(e.getClick() == ClickType.SHIFT_LEFT){
                    list.set(0, lore.get(0).replace("%bet%", moneyFormat(list.get(0), 10000, p).toString()).replace("&","§"));
                }

                itx.setLore(list);
                it.setItemMeta(itx);
                p.updateInventory();

            }
        }
    }

    private Integer moneyFormat(String string, int amount, Player p){

        String extract = string.replace("&","§");
        String removeColor = ChatColor.stripColor(extract);
        Integer result = Integer.valueOf(removeColor.replaceAll("[^0-9]", ""));

        if((amount + result) <= HRoulette.getInstance().getConfig().getInt("settings.max-bet")){
            return amount + result;
        }else{
            p.sendMessage(HRoulette.getInstance().getConfig().getString("messages.max-bet")
                    .replace("%prefix%", HRoulette.getInstance().getConfig().getString("messages.prefix"))
                    .replace("&","§")
                    .replace("%max-bet%", String.valueOf(HRoulette.getInstance().getConfig().getInt("settings.inventory.max-bet"))));
            return amount;
        }

    }

}
