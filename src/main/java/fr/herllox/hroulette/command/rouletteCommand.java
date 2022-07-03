package fr.herllox.hroulette.command;

import fr.herllox.hroulette.utils.inventoryUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class rouletteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 0){
                if(p.hasPermission("hroulette.use")){

                    Inventory inv = inventoryUtil.mainInventory();
                    p.openInventory(inv);

                }
            }
        }

        return false;
    }
}
