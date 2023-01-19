package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LocationManager {
    private Location spawnLocation;
    private Location lobbyLocation;

    public void load(ConfigManager config) {
        String spawnLocation = config.getString("locations.spawnLocation");
        this.spawnLocation = LocationUtils.stringToLocation(spawnLocation);

        String lobbyLocation = config.getString("locations.lobbyLocation");
        this.lobbyLocation = LocationUtils.stringToLocation(lobbyLocation);
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }
    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    public void teleport(UUID uuid, Location location) {
        Player player = Bukkit.getPlayer(uuid);
        if(player == null) {
            return;
        }
        System.out.println("Spawn: " + location);
        player.teleport(location);
    }

}
