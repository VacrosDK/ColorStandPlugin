package com.gmail.vacrosdk.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeTask {
    private final JavaPlugin plugin;
    private final Runnable runnable;
    private final int ticks;

    public TimeTask(JavaPlugin plugin, Runnable runnable, int ticks) {
        this.plugin = plugin;
        this.runnable = runnable;
        this.ticks = ticks;
        this.run();
    }

    public void run() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, ticks);
    }

}
