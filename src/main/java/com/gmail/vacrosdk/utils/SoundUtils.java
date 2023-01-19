package com.gmail.vacrosdk.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SoundUtils {

    public static void playSound(Player p, Sound sound, float volume, float pitch) {
        Location location = p.getLocation();
        p.playSound(location, sound, volume, pitch);
    }

    public static void playSound(UUID uuid, Sound sound, float volume, float pitch) {
        Player player = Bukkit.getPlayer(uuid);
        playSound(player, sound, volume, pitch);
    }

}
