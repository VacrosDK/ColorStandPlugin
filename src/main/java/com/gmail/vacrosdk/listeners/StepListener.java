package com.gmail.vacrosdk.listeners;

import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.managers.DeadPlayerManager;
import com.gmail.vacrosdk.managers.LocationManager;
import com.gmail.vacrosdk.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Set;
import java.util.UUID;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class StepListener implements Listener {
    private final PlayerManager playerManager;
    private final DeadPlayerManager deadPlayerManager;
    private final LocationManager locationManager;

    public StepListener(PlayerManager playerManager, DeadPlayerManager deadPlayerManager, LocationManager locationManager) {
        this.playerManager = playerManager;
        this.deadPlayerManager = deadPlayerManager;
        this.locationManager = locationManager;
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if(!playerManager.contains(uuid)) {
            return;
        }
        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WATER) {
            if(deadPlayerManager.contains(uuid)) {
                return;
            }
            if(playerManager.isOnePlayerLeft()) {
                return;
            }
            playerManager.remove(uuid);
            sendDeathMessages(player);
            deadPlayerManager.add(uuid);
            locationManager.teleport(uuid, locationManager.getLobbyLocation());
        }
    }

    private void sendDeathMessages(Player victim) {
        Set<UUID> playerSet = playerManager.getPlayerSet();
        playerSet.forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            sendMessage(player, LanguagePath.PLAYER__ELIMINATED.replace("%victim%", victim.getName()));
        });
        sendMessage(victim, LanguagePath.YOU_LOST);
    }
}