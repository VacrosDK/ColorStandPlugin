package com.gmail.vacrosdk.commands.subcommands;

import com.gmail.vacrosdk.commands.SubCommand;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.Permissions;
import com.gmail.vacrosdk.managers.PlayerManager;
import com.gmail.vacrosdk.types.JoinType;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class ColorStandJoinCommand extends SubCommand {
    private final PlayerManager playerManager;

    public ColorStandJoinCommand(PlayerManager playerManager) {
        super(Permissions.JOIN.getPermissionString());
        this.playerManager = playerManager;
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        String playerName = args[0];
        if (Bukkit.getPlayer(playerName) == null) {
            return;
        }
        Player p = Bukkit.getPlayer(playerName);
        JoinType joinType = playerManager.canJoin(playerName);
        if (joinType == JoinType.CAN_JOIN) {
            playerManager.add(p.getUniqueId());
            sendMessage(p, LanguagePath.JOIN_YOU__JOINED);
            return;
        }
    }

    @Override
    public void printHelp(CommandSender sender) {
        sendMessage(sender, LanguagePath.PRINT_HELP__JOIN);
    }
}
