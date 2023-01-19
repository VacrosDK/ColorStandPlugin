package com.gmail.vacrosdk.commands.subcommands;

import com.gmail.vacrosdk.commands.SubCommand;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.Permissions;
import com.gmail.vacrosdk.managers.GameManager;
import com.gmail.vacrosdk.managers.LocationManager;
import com.gmail.vacrosdk.managers.PlayerManager;
import com.gmail.vacrosdk.managers.StatusManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class ColorStandLeaveCommand extends SubCommand {
    private final PlayerManager playerManager;
    private final StatusManager statusManager;
    private final LocationManager locationManager;
    private final GameManager gameManager;

    public ColorStandLeaveCommand(PlayerManager playerManager, StatusManager statusManager, LocationManager locationManager, GameManager gameManager) {
        super(Permissions.LEAVE.getPermissionString());
        this.playerManager = playerManager;
        this.statusManager = statusManager;
        this.locationManager = locationManager;
        this.gameManager = gameManager;
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            printHelp(sender);
            return;
        }
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        if(playerManager.contains(player.getUniqueId())) {
            if(playerManager.isTwoPlayersLeft()) {
                doIfLastPersonIsFound(uuid);
            }
            playerManager.remove(uuid);
            locationManager.teleport(uuid, locationManager.getLobbyLocation());
        }
    }

    private void doIfLastPersonIsFound(UUID uuid) {
        playerManager.remove(uuid);
        Set<UUID> playerSet = playerManager.getPlayerSet();
        UUID[] list = new UUID[1];
        playerSet.forEach(playerUUID -> {
            list[0] = playerUUID;
        });
        gameManager.stopGame(list[0]);
    }

    @Override
    public void printHelp(CommandSender sender) {
        sendMessage(sender, LanguagePath.PRINT_HELP__LEAVE);
    }
}
