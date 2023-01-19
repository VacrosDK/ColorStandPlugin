package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColorFloorManager {
    private final MaterialManager materialManager;
    private final JavaPlugin plugin;
    private final Set<String> placeHolderBlocks = new HashSet<>();
    private String defaultGroundBlock;
    private String groundPosition1;
    private String groundPosition2;

    public ColorFloorManager(MaterialManager materialManager, JavaPlugin plugin) {
        this.materialManager = materialManager;
        this.plugin = plugin;
    }

    public void load(ConfigManager configManager) {
        loadPlaceholderBlocks(configManager);
        defaultGroundBlock = configManager.getString("defaultGroundBlock");
        groundPosition1 = configManager.getString("locations.mapGroundSquare.pos1");
        groundPosition2 = configManager.getString("locations.mapGroundSquare.pos2");
    }

    private void loadPlaceholderBlocks(ConfigManager configManager) {
        List<String> blocks = configManager.getStringList("placeHolderBlocks");
        placeHolderBlocks.addAll(blocks);
    }

    public void replacePlaceholderBlocks(ConfigManager config) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/pos1 " + groundPosition1);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/pos2 " + groundPosition2);
        List<String> placeHolderBlocks = config.getStringList("placeHolderBlocks");
        List<String> materialSet = materialManager.getMaterialSet();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for (int i = 0; i <= placeHolderBlocks.size(); i++) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/replace "
                        + placeHolderBlocks.get(i) + " " + materialSet.get(i));
            }
        }, 2);
    }

    public void reset() {
        List<String> materialSet = materialManager.getMaterialSet();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/pos1 " + groundPosition1);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/pos2 " + groundPosition2);
        System.out.println("DefaultGroundBlock: " + defaultGroundBlock);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for (int i = 0; i <= materialSet.size(); i++) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/replace "
                        + materialSet.get(i) + " " + defaultGroundBlock);
            }
        }, 1);
    }
}
