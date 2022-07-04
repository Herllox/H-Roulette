package fr.herllox.hroulette.runnable;

import fr.herllox.hroulette.HRoulette;
import fr.herllox.hroulette.utils.Util;
import fr.herllox.hroulette.utils.inventoryUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class inventoryRunnable extends BukkitRunnable {

    private Player p;
    private Inventory inv;
    private int time;

    public inventoryRunnable(Player p, Inventory inv) {
        this.p = p;
        this.inv = inv;
        this.time = 0;
    }

    @Override
    public void run() {
        time++;
        if(time <= 17) inventoryUtil.rollInv(inv, time, p);
        if(time == 20){

            List<String> lore = inv.getItem(49).getItemMeta().getLore();
            String extract = lore.get(0).replace("&","§");
            String removeColor = ChatColor.stripColor(extract);
            Integer result = Integer.valueOf(removeColor.replaceAll("[^0-9]", ""));

            if(inv.getItem(22).getType() == Material.LIME_STAINED_GLASS_PANE && lore.get(4).contains(Util.getIcon())){
                Util.betWin(p, Double.valueOf(result), (result * HRoulette.getInstance().getConfig().getDouble("settings.multiplier.green")));
            }else if(inv.getItem(22).getType() == Material.RED_STAINED_GLASS_PANE && lore.get(3).contains(Util.getIcon())){
                Util.betWin(p, Double.valueOf(result), (result * HRoulette.getInstance().getConfig().getDouble("settings.multiplier.red")));
            }else if(inv.getItem(22).getType() == Material.BLACK_STAINED_GLASS_PANE && lore.get(2).contains(Util.getIcon())){
                Util.betWin(p, Double.valueOf(result), (result * HRoulette.getInstance().getConfig().getDouble("settings.multiplier.black")));
            }


            ItemStack green = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenx = green.getItemMeta();
            greenx.setDisplayName(HRoulette.getInstance().getConfig().getString("settings.inventory.money-add").replace("&","§"));
            greenx.setLore(Util.getLore("settings.inventory.money-add-lore"));
            green.setItemMeta(greenx);
            inv.setItem(47, green);

            ItemStack red = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redx = red.getItemMeta();
            redx.setDisplayName(HRoulette.getInstance().getConfig().getString("settings.inventory.money-remove").replace("&","§"));
            redx.setLore(Util.getLore("settings.inventory.money-remove-lore"));
            red.setItemMeta(redx);
            inv.setItem(51, red);

            p.updateInventory();

        }
    }
}
