package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.utils.LocationUtils;
import com.gmail.vacrosdk.utils.TimeTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class ColorScreenManger {
    private String currentMaterial;
    private Location pos1;
    private Location pos2;

    public void load(ConfigManager configManager) {
        pos1 = LocationUtils.stringToLocation(configManager.getString("locations.colorScreen.pos1"));
        pos2 = LocationUtils.stringToLocation(configManager.getString("locations.colorScreen.pos2"));
    }

    public void setCurrentBlock(String material, JavaPlugin plugin) {
        getCornerPositions();
        new TimeTask(plugin, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/set " + material);
            currentMaterial = material;
        }, 20);
    }

    public String getCurrentBlock() {
        return currentMaterial;
    }

    private void getCornerPositions() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/pos1 " + pos1);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/pos2 " + pos2);
    }


}
