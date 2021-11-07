package ru.totalexx.plugins.insomnia;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Insomnia extends JavaPlugin {

    private static Insomnia instance;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new SkipNight(), this);
        logPluginInfo();
    }

    public static Insomnia getInstance() {
        return instance;
    }

    public static void logPluginInfo() {
        String nameAndVersion = "|     Insomnia-" + Config.getString("version") + "     |";
        String headerAndFooter = "+" + new String(new char[nameAndVersion.length() - 2]).replace("\0", "-") + "+";
        getInstance().getLogger().info(headerAndFooter);
        getInstance().getLogger().info(nameAndVersion);
        getInstance().getLogger().info(headerAndFooter);
    }

}
