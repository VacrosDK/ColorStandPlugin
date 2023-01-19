package com.gmail.vacrosdk.commands.subcommands;

import com.gmail.vacrosdk.commands.SubCommand;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.Permissions;
import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.managers.GameManager;
import com.gmail.vacrosdk.managers.PlayerManager;
import com.gmail.vacrosdk.managers.StatusManager;
import org.bukkit.command.CommandSender;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class ColorStandStartCommand extends SubCommand {
    private final GameManager gameManager;
    private final StatusManager statusManager;
    private final PlayerManager playerManager;
    private final ConfigManager configManager;
    public ColorStandStartCommand(GameManager gameManager, StatusManager statusManager, PlayerManager playerManager, ConfigManager configManager) {
        super(Permissions.START.getPermissionString());
        this.gameManager = gameManager;
        this.statusManager = statusManager;
        this.playerManager = playerManager;
        this.configManager = configManager;
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(statusManager.isStarted()) {
            return;
        }
        if(!playerManager.isMoreThan1Player()) {
            return;
        }
        gameManager.start(configManager);
    }

    @Override
    public void printHelp(CommandSender sender) {
        sendMessage(sender, LanguagePath.PRINT_HELP__START);
    }
}
