package ru.totalexx.plugins.insomnia;

public class Config {
    public static int getInt(String name) {
        return Insomnia.getInstance().getConfig().getInt(name);
    }

    public static String getString(String name, Object... args) {
        return String.format(Insomnia.getInstance().getConfig().getString(name).replace('&', '\u00a7'), args);
    }

    public static boolean getBoolean(String name) {
        return Insomnia.getInstance().getConfig().getBoolean(name);
    }
}
