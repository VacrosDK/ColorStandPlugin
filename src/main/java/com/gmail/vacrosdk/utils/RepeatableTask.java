package com.gmail.vacrosdk.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;
import java.util.function.Function;

public class RepeatableTask {
    private final JavaPlugin plugin;
    private final int match;
    private final int delay;
    private final Consumer<Integer> consumer;
    private final Function<Integer, Integer> countChangeFunction;
    private int count = 0;
    private BukkitTask bukkitTask;
    private Runnable runWhenFinished;

    public RepeatableTask(JavaPlugin plugin, int match, int delay, Consumer<Integer> consumer, Function<Integer, Integer> countChangeFunction) {
        this.plugin = plugin;
        this.match = match;
        this.delay = delay;
        this.consumer = consumer;
        this.countChangeFunction = countChangeFunction;
    }

    public void run() {
        bukkitTask = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            consumer.accept(count);
            count = countChangeFunction.apply(count);
            if(count == match) {
                if (runWhenFinished != null) {
                    runWhenFinished.run();
                }
                bukkitTask.cancel();
            }
        }, 0, delay);
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRunWhenFinished(Runnable runWhenFinished) {
        this.runWhenFinished = runWhenFinished;
    }
}
