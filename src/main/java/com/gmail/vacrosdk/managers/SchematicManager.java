package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.utils.LocationUtils;
import com.gmail.vacrosdk.utils.RandomElementPicker;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SchematicManager {
    private final Set<String> schematicSet = new HashSet<>();
    private String currentSchematic;
    private Location pasteLocation;

    public void load(ConfigManager config) {
        List<String> schematics = config.getStringList("schematics");
        if(schematics != null) {
            schematicSet.addAll(schematics);
        }
        pasteLocation = LocationUtils.stringToLocation(config.getString("schematicPasteLocation"));
    }

    public void setSchematic() {
        System.out.println("Schematics: " + schematicSet);
        System.out.println("Schematics: Size: " + schematicSet.size());
        currentSchematic = RandomElementPicker.randomStringFromSet(schematicSet);
    }

    public String getSchematic() {
        return currentSchematic;
    }

    public void pasteSchematic() {
        System.out.println("Schematic debug: Current:" + currentSchematic + "");
        System.out.println("/sp paste " + currentSchematic + ".schematic "
                + pasteLocation.getX() + " "
                + pasteLocation.getY() + " "
                + pasteLocation.getZ() + " "
                + pasteLocation.getWorld().getName());
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sp paste " + currentSchematic + ".schematic "
                + pasteLocation.getX() + " "
                + pasteLocation.getY() + " "
                + pasteLocation.getZ() + " "
                + pasteLocation.getWorld().getName());
    }

}
