package ru.totalexx.plugins.insomnia;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class EventListener implements Listener {

    private String[] sleepMessage = {
            "%s лёг в кровать и уснул. Спокойной ночи :)",
            "%s споткнулся об кровать и уснул. Сладких снов...",
            "%s забрался под одеяло от страшных монстров.",
            "%s решил поспать. Мудро.",
            "%s дерётся с подушкой. Кажется, подушка победила...",
            "%s наелся и спит.",
            "%s плотно и вкусно покушал и уснул.",
            "У %s сонный паралич."
    };

    @EventHandler
    public void playerSleeping(PlayerBedEnterEvent e) {
        if(e.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            Bukkit.getServer().getScheduler().runTaskLater(Insomnia.getInstance(), new Runnable() {
                public void run() {
                    e.getPlayer().getWorld().setTime(0L);
                    e.getPlayer().getWorld().setStorm(false);
                    e.getPlayer().getWorld().setThundering(false);
                }
            }, 100L);
            sendSleepMessage(e.getPlayer());
        }
    }

    public void sendSleepMessage(Player sleepingPlayer) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(String.format(sleepMessage[(int)(Math.random() * sleepMessage.length)], sleepingPlayer.getName()));
        }
    }

}
