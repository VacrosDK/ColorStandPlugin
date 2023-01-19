package com.gmail.vacrosdk.managers;

import java.util.Set;
import java.util.UUID;

public class TimeManager {
    private final PlayerManager playerManager;

    public TimeManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void startFaseOneCountdown() {
        Set<UUID> playerSet = playerManager.getPlayerSet();
    }

}
