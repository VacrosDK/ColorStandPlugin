package com.gmail.vacrosdk.commands.subcommands;

import com.gmail.vacrosdk.ColorStandPlugin;
import com.gmail.vacrosdk.commands.SubCommand;
import com.gmail.vacrosdk.exception.LackOfBlocksException;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.Permissions;
import org.bukkit.command.CommandSender;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class ColorStandReloadCommand extends SubCommand {
    private final ColorStandPlugin plugin;

    public ColorStandReloadCommand(ColorStandPlugin plugin) {
        super(Permissions.RELOAD.getPermissionString());
        this.plugin = plugin;
    }

    @Override
    public void run(CommandSender sender, String[] args) throws LackOfBlocksException {
        plugin.load();
        sendMessage(sender, LanguagePath.RELOAD);
    }

    @Override
    public void printHelp(CommandSender sender) {
        sendMessage(sender, LanguagePath.PRINT_HELP__RELOAD);
    }
}
