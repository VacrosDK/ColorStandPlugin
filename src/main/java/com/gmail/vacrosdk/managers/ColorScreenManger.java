package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;


public class ColorScreenManger {
    private Material currentBlock;
    private Location pos1;
    private Location pos2;

    public void load(ConfigManager configManager) {
        pos1 = LocationUtils.stringToLocation(configManager.getString("locations.colorScreen.pos1"));
        pos2 = LocationUtils.stringToLocation(configManager.getString("locations.colorScreen.pos2"));
    }

    public void setCurrentBlock(Material material) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "//set " + material.toString());
        currentBlock = material;
    }

    public Material getCurrentBlock() {
        return currentBlock;
    }


}
