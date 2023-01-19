package com.gmail.vacrosdk.commands.subcommands;

import com.gmail.vacrosdk.commands.SubCommand;
import com.gmail.vacrosdk.exception.LackOfBlocksException;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.Permissions;
import com.gmail.vacrosdk.managers.StatusManager;
import org.bukkit.command.CommandSender;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class ColorStandCloseCommand extends SubCommand {
    private StatusManager statusManager;

    public ColorStandCloseCommand(StatusManager statusManager) {
        super(Permissions.CLOSE.getPermissionString());
        this.statusManager = statusManager;
    }

    @Override
    public void run(CommandSender sender, String[] args) throws LackOfBlocksException {
        if(!statusManager.isOpen()) {
            sendMessage(sender, LanguagePath.ALREADY_CLOSED);
            return;
        }
        if(statusManager.isStarted()) {
            sendMessage(sender, LanguagePath.GAME__STARTED);
            return;
        }
        statusManager.closeGame();
        sendMessage(sender, LanguagePath.CLOSED_GAME);
    }

    @Override
    public void printHelp(CommandSender sender) {
        sendMessage(sender, LanguagePath.PRINT_HELP__CLOSE);
    }
}
