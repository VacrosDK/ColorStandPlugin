package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.utils.TimeTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.UUID;

public class TimeManager {
    private int faseOneTime;
    private int faseTwoTime;
    private int consistentTime;
    private final PlayerManager playerManager;
    private final CountdownManager countdownManager;
    private final ColorFloorManager colorFloorManager;
    private final ColorScreenManger colorScreenManger;
    private final MaterialManager materialManager;

    public void load(ConfigManager configManager) {
        faseOneTime = configManager.getInt("time.lookingAtBlocks");
        faseTwoTime = configManager.getInt("time.runningToBlocks");
        consistentTime = configManager.getInt("time.pauseTime");
    }

    public TimeManager(PlayerManager playerManager, CountdownManager countdownManager, ColorFloorManager colorFloorManager, ColorScreenManger screenManger, MaterialManager materialManager) {
        this.playerManager = playerManager;
        this.countdownManager = countdownManager;
        this.colorFloorManager = colorFloorManager;
        this.colorScreenManger = screenManger;
        this.materialManager = materialManager;
    }

    public void startFaseOneCountdown(JavaPlugin plugin, ConfigManager config) {
        Set<UUID> playerSet = playerManager.getPlayerSet();
        countdownManager.createXpCountDown(playerSet, faseOneTime*20);
        new TimeTask(plugin, () -> approachFaseTwo(plugin, config), faseOneTime*20);
    }

    private void approachFaseTwo(JavaPlugin plugin, ConfigManager config) {
        colorFloorManager.setGreyFloor();
        new TimeTask(plugin, () -> startFaseTwoCountdown(plugin, config), consistentTime*20);
    }

    public void startFaseTwoCountdown(JavaPlugin plugin, ConfigManager config) {
        Set<UUID> playerSet = playerManager.getPlayerSet();
        colorScreenManger.setCurrentBlock(materialManager.getTargetMaterial(), plugin);
        countdownManager.createXpCountDown(playerSet, faseTwoTime);
        new TimeTask(plugin, () -> colorFloorManager.removeAllBlocksButTargetBlock(config), consistentTime);
    }
}
