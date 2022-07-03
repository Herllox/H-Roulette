package fr.herllox.hroulette.utils;

import fr.herllox.hroulette.HRoulette;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class ecoUtil {

    public static Economy econ = null;

    public static boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        ecoUtil.econ = rsp.getProvider();
        return ecoUtil.econ != null;
    }

    public static Double getPlayerMoney(Player p){
        return econ.getBalance(p);
    }

    public static EconomyResponse removePlayerMoney(Player p, Double amount){
        return econ.withdrawPlayer(p, amount);
    }

    public static EconomyResponse addPlayerMoney(Player p, Double amount){
        return econ.depositPlayer(p, amount);
    }

}
