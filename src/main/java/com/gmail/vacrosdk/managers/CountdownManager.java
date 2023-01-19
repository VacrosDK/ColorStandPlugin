package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.utils.RepeatableTask;
import com.gmail.vacrosdk.utils.TitleUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.UUID;

public class CountdownManager {
    private final JavaPlugin plugin;

    public CountdownManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void startGameCountDown(Set<UUID> playerSet) {
        RepeatableTask repeatableTask = new RepeatableTask(plugin, 0, 20, (time) -> {
            TitleUtils.sendTitle(playerSet, LanguagePath.TITLE_COUNTDOWN, LanguagePath.SUBTITLE_COUNTDOWN.
                    replace("%seconds%", String.valueOf(time)));
        }, i -> i-1);
        repeatableTask.setCount(5);
        repeatableTask.run();
    }

    public void createXpCountDown(Set<UUID> uuids, int time) {

    }

    public void createXpCountDown(UUID uuid, int time) {
        Player player = Bukkit.getPlayer(uuid);
        for (int i = 0; i < time; i++) {
            player.setExp(time);
        }
    }

}
