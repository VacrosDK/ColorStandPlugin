package com.gmail.vacrosdk.commands.subcommands;

import com.gmail.vacrosdk.commands.SubCommand;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.Permissions;
import com.gmail.vacrosdk.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class ColorStandPlayerListCommand extends SubCommand {
    private final PlayerManager playerManager;

    public ColorStandPlayerListCommand(PlayerManager playerManager) {
        super(Permissions.PLAYERLIST.getPermissionString());
        this.playerManager = playerManager;
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(!playerManager.isMoreThan1Player()) {
            sendMessage(sender, LanguagePath.PLAYERS_NO__PLAYERS);
            return;
        }
        Set<UUID> players = playerManager.getPlayerSet();
        sendMessage(sender, LanguagePath.PLAYERS_PRINT__PLAYERS.replace("%amount%", String.valueOf(players.size())));
        players.forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            sendMessage(sender, LanguagePath.PLAYERS_PRINT__LAYOUT.replace("%playerName%", player.getName()));
        });
    }

    @Override
    public void printHelp(CommandSender sender) {
        sendMessage(sender, LanguagePath.PRINT_HELP__PLAYER__LIST);
    }
}
