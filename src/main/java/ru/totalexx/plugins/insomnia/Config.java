package ru.totalexx.plugins.insomnia;

import java.util.List;

public class Config {
    public static int getInt(String name) {
        return Insomnia.getInstance().getConfig().getInt(name);
    }

    public static String getString(String name) {
        return Insomnia.getInstance().getConfig().getString(name);
    }

    public static List getList(String name) {
        return Insomnia.getInstance().getConfig().getStringList(name);
    }
}
