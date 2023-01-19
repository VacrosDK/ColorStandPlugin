package com.gmail.vacrosdk.commands.subcommands;

import com.gmail.vacrosdk.commands.SubCommand;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.Permissions;
import com.gmail.vacrosdk.managers.GameManager;
import org.bukkit.command.CommandSender;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class ColorStandResetCommand extends SubCommand {
    private final GameManager gameManager;

    public ColorStandResetCommand(GameManager gameManager) {
        super(Permissions.RESET.getPermissionString());
        this.gameManager = gameManager;
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        gameManager.reset();
        sendMessage(sender, LanguagePath.RESAT_GAME);
    }

    @Override
    public void printHelp(CommandSender sender) {
        sendMessage(sender, LanguagePath.PRINT_HELP__RESET);
    }
}
