package com.gmail.vacrosdk;

import com.gmail.vacrosdk.commands.CommandManager;
import com.gmail.vacrosdk.exception.LackOfBlocksException;
import com.gmail.vacrosdk.language.CustomLanguage;
import com.gmail.vacrosdk.language.Language;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.managers.*;
import org.bukkit.plugin.java.JavaPlugin;

public class ColorStandPlugin extends JavaPlugin {
    private final ConfigManager languageManager = new ConfigManager(this, "language.yml");
    private final ConfigManager configManager = new ConfigManager(this, "config.yml");
    private final ConfigManager blockManager = new ConfigManager(this, "blocks.yml");
    private final StatusManager statusManager = new StatusManager();
    private final PlayerManager playerManager = new PlayerManager(statusManager);
    private final MaterialManager materialManager = new MaterialManager(this);
    private final SchematicManager schematicManager = new SchematicManager();
    private final LocationManager locationManager = new LocationManager();
    private final ColorScreenManger colorScreenManger = new ColorScreenManger();
    private final TimeManager timeManager = new TimeManager(playerManager);
    private final CountdownManager countdownManager = new CountdownManager(this);
    private final ColorFloorManager colorFloorManager = new ColorFloorManager(materialManager, this);
    private final GameManager gameManager = new GameManager(this, materialManager, schematicManager, playerManager, statusManager, locationManager, countdownManager, colorScreenManger, colorFloorManager, timeManager);
    private final CommandManager commandManager = new CommandManager(this, playerManager, gameManager, statusManager, configManager, locationManager);

    @Override
    public void onEnable() {
        Language.setConfigManager(languageManager);
        try {
            load();
        } catch (LackOfBlocksException e) {
            e.printStackTrace();
        }
        getCommand("colorStand").setExecutor(commandManager);
        System.out.println("[ColorStandPlugin] Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("[ColorStandPlugin] Plugin has been disabled!");
    }

    public void load() throws LackOfBlocksException {
        loadConfigs();
        locationManager.load(configManager);
        materialManager.load(blockManager);
        schematicManager.load(configManager);
        colorFloorManager.load(configManager);
    }

    private void loadConfigs() {
        languageManager.reloadCustomConfig();
        CustomLanguage.load(languageManager, LanguagePath.class);
    }


}
