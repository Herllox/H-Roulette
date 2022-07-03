package fr.herllox.hroulette;

import fr.herllox.hroulette.command.rouletteCommand;
import fr.herllox.hroulette.events.bookClick;
import fr.herllox.hroulette.events.limeClick;
import fr.herllox.hroulette.events.redClick;
import fr.herllox.hroulette.utils.ecoUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static fr.herllox.hroulette.utils.ecoUtil.econ;

public final class HRoulette extends JavaPlugin {

    private static HRoulette instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        if (!ecoUtil.setupEconomy() ) {
            Logger.getLogger("Minecraft").severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;
        getCommand("roulette").setExecutor(new rouletteCommand());

        Bukkit.getPluginManager().registerEvents(new bookClick(), this);
        Bukkit.getPluginManager().registerEvents(new limeClick(), this);
        Bukkit.getPluginManager().registerEvents(new redClick(), this);

    }

    public static HRoulette getInstance() {
        return instance;
    }
}
