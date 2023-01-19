package com.gmail.vacrosdk.commands;

import com.gmail.vacrosdk.ColorStandPlugin;
import com.gmail.vacrosdk.commands.subcommands.*;
import com.gmail.vacrosdk.exception.LackOfBlocksException;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.managers.GameManager;
import com.gmail.vacrosdk.managers.LocationManager;
import com.gmail.vacrosdk.managers.PlayerManager;
import com.gmail.vacrosdk.managers.StatusManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {
    private final Map<String, SubCommand> subCommandMap = new HashMap<>();
    private boolean commandsAreBlocked = false;

    public CommandManager(ColorStandPlugin plugin, PlayerManager playerManager, GameManager gameManager, StatusManager statusManager, ConfigManager configManager, LocationManager locationManager) {
        subCommandMap.put("reload", new ColorStandReloadCommand(plugin));
        subCommandMap.put("start", new ColorStandStartCommand(gameManager, statusManager, playerManager, configManager));
        subCommandMap.put("reset", new ColorStandResetCommand(gameManager));
        subCommandMap.put("playerList", new ColorStandPlayerListCommand(playerManager));
        subCommandMap.put("join", new ColorStandJoinCommand(playerManager));
        subCommandMap.put("leave", new ColorStandLeaveCommand(playerManager, statusManager, locationManager, gameManager));
        subCommandMap.put("kick", new ColorStandKickCommand());
        subCommandMap.put("open", new ColorStandOpenCommand(statusManager));
        subCommandMap.put("close", new ColorStandCloseCommand(statusManager));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(commandsAreBlocked) {
            return true;
        }
        if (args.length == 0) {
            printHelp(sender);
            return true;
        }
        if (!subCommandMap.containsKey(args[0].toLowerCase())) {
            printHelp(sender);
            return true;
        }
        try {
            subCommandMap.get(args[0]).execute(sender, Arrays.copyOfRange(args, 1, args.length));
        } catch (LackOfBlocksException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void printHelp(CommandSender p) {
        p.sendMessage(LanguagePath.PRINT__HELP__MAIN__MESSAGE);
        subCommandMap.forEach((name, subCommand) -> {
            if (subCommand.hasPermission(p)) {
                subCommand.printHelp(p);
                p.sendMessage("");
            }
        });
    }

    public void blockAllCommands() {
        this.commandsAreBlocked = true;
    }

    public void unblockAllCommands() {
        this.commandsAreBlocked = false;
    }
}

