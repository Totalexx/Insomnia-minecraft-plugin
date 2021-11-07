package ru.totalexx.plugins.insomnia;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Insomnia extends JavaPlugin {

    private static Insomnia instance;
    private static Logger log;
    @Override
    public void onEnable() {
        instance = this;
        log = getLogger();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new SkipNight(), this);
        logPluginInfo();
    }

    @Override
    public void onDisable() {
    }

    public static Insomnia getInstance() {
        return instance;
    }

    public static Logger getLog() {
        return log;
    }

    public static void logPluginInfo() {
        String nameAndVersion = "|     Insomnia-" + Config.getString("version") + "     |";
        String headerAndFooter = "+" + new String(new char[nameAndVersion.length() - 2]).replace("\0", "-") + "+";
        log.info(headerAndFooter);
        log.info(nameAndVersion);
        log.info(headerAndFooter);
    }

}
