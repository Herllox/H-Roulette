package fr.herllox.hroulette.utils;

import fr.herllox.hroulette.HRoulette;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> getLore(String directory) {
        List<String> getLore = HRoulette.getInstance().getConfig().getStringList(directory);
        List<String> lore = new ArrayList<>();
        for (String s : getLore) {
            lore.add(s.replace("%bet%", "0")
                    .replace("%green%", HRoulette.getInstance().getConfig().getString("settings.inventory.green"))
                    .replace("%red%", HRoulette.getInstance().getConfig().getString("settings.inventory.red"))
                    .replace("%black%", HRoulette.getInstance().getConfig().getString("settings.inventory.black"))
                    .replace("%select-icon%", HRoulette.getInstance().getConfig().getString("settings.inventory.select-icon"))
                    .replace("%not-select-icon%", HRoulette.getInstance().getConfig().getString("settings.inventory.not-select-icon"))
                    .replace("&","§"));
        }
        return lore;
    }


    public static String getIcon(){
        return HRoulette.getInstance().getConfig().getString("settings.inventory.select-icon");
    }

    public static String getNotIcon(){
        return HRoulette.getInstance().getConfig().getString("settings.inventory.not-select-icon");
    }

    public static void betWin(Player p, Double bet, Double money){

        p.sendMessage(HRoulette.getInstance().getConfig().getString("messages.win-message")
                .replace("%prefix%", HRoulette.getInstance().getConfig().getString("messages.prefix"))
                .replace("%bet%", bet.toString())
                .replace("%money%", money.toString())
                .replace("&","§"));

        ecoUtil.addPlayerMoney(p, money);

    }

}
