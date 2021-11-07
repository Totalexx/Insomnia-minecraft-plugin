package ru.totalexx.plugins.insomnia;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.util.HashMap;
import java.util.List;

public class SkipNight implements Listener {

    private HashMap<String, Integer> sleepers = new HashMap<>();

    @EventHandler
    public void playerSleeping(PlayerBedEnterEvent e) {
        if (e.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            if (Config.getInt("sleepers") == 0) {
                sendSleepMessage(e.getPlayer());
                skipNight(e.getPlayer());
            } else {
                World nightWorld = e.getPlayer().getWorld();
                String worldName = nightWorld.getName();
                if (!sleepers.containsKey(worldName)) {
                    sleepers.put(worldName, 0);
                }
                sleepers.put(worldName, sleepers.get(worldName) + 1);
                sendSleepMessage(e.getPlayer());

                if (needSkipNight(nightWorld)) {
                    skipNight(nightWorld);
                }
            }
        }
    }

    @EventHandler
    public void playerWakeUp(PlayerBedLeaveEvent e) {
        if (Config.getInt("sleepers") == 0) {
            String nightWorld = e.getPlayer().getWorld().getName();
            sleepers.put(nightWorld, sleepers.get(nightWorld) - 1);
        }
    }

    public boolean needSkipNight(World nightWorld) {
        float needSleepers = Config.getInt("needSleepers") / 100f;
        int playerCount = nightWorld.getPlayerCount();
        int sleepers = this.sleepers.get(nightWorld.getName());
        return sleepers / playerCount >= needSleepers;
    }

    public void sendSleepMessage(Player sleepingPlayer) {
        List<String> messages = Config.getList("messages");
        int numberOfMessage = (int) (Math.random() * messages.size());
        String message = messages.get(numberOfMessage).replace("{player}", sleepingPlayer.getName());

        if (Config.getInt("needSleepers") != 0) {
            String countPlayers = "(" + sleepers.get(sleepingPlayer.getWorld().getName()) +
                    "/" + sleepingPlayer.getWorld().getPlayerCount() + ")";
            message += countPlayers;
        }

        for (Player player : sleepingPlayer.getWorld().getPlayers()) {
            player.sendMessage(message);
        }
    }

    private static void skipNight(Player player) {
        Bukkit.getServer().getScheduler().runTaskLater(Insomnia.getInstance(), new Runnable() {
            public void run() {
                player.getWorld().setTime(0L);
                player.getWorld().setStorm(false);
                player.getWorld().setThundering(false);
                for(Player player : player.getWorld().getPlayers()) {
                    player.sendMessage(Config.getString("messageSkipNight"));
                }
            }
        }, 100L);
    }

    private static void skipNight(World world) {
        Bukkit.getServer().getScheduler().runTaskLater(Insomnia.getInstance(), new Runnable() {
            public void run() {
                world.setTime(0L);
                world.setStorm(false);
                world.setThundering(false);
                for(Player player : world.getPlayers()) {
                    player.sendMessage(Config.getString("messageSkipNight"));
                }
            }
        }, 100L);
    }
}
