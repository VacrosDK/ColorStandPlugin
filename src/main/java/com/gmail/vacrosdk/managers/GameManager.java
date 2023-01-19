package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.UUID;

public class GameManager {
    private final JavaPlugin plugin;
    private final MaterialManager materialManager;
    private final SchematicManager schematicManager;
    private final PlayerManager playerManager;
    private final StatusManager statusManager;
    private final LocationManager locationManager;
    private final CountdownManager countdownManager;
    private final ColorScreenManger colorScreenManger;
    private final ColorFloorManager colorFloorManager;
    private final TimeManager timeManager;

    public GameManager(JavaPlugin plugin, MaterialManager materialManager, SchematicManager schematicManager, PlayerManager playerManager, StatusManager statusManager, LocationManager locationManager, CountdownManager countdownManager, ColorScreenManger colorScreenManger, ColorFloorManager colorFloorManager, TimeManager timeManager) {
        this.plugin = plugin;
        this.materialManager = materialManager;
        this.schematicManager = schematicManager;
        this.playerManager = playerManager;
        this.statusManager = statusManager;
        this.locationManager = locationManager;
        this.countdownManager = countdownManager;
        this.colorScreenManger = colorScreenManger;
        this.colorFloorManager = colorFloorManager;
        this.timeManager = timeManager;
    }

    public void start(ConfigManager config) {
        statusManager.startGame();
        Set<UUID> playerSet = playerManager.getPlayerSet();
        Location spawnLocation = locationManager.getSpawnLocation();
        playerSet.forEach(uuid -> {
            locationManager.teleport(uuid, spawnLocation);
        });
        countdownManager.startGameCountDown(playerSet);
        Bukkit.getScheduler().runTaskLater(plugin, () -> createFirstRound(config), 100);
    }

    public void reset() {
        statusManager.reset();
        playerManager.reset();
        colorFloorManager.reset();
    }

    private void createFirstRound(ConfigManager config) {
        schematicManager.setSchematic();
        materialManager.createNewMaterialsForRound(statusManager.getCurrentDifficulty());
        schematicManager.pasteSchematic();
        colorFloorManager.replacePlaceholderBlocks(config);
        timeManager.startFaseOneCountdown();
    }

    public void stopGame(UUID winner) {


        reset();
    }

}
