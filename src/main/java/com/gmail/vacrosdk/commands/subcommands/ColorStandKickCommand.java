package com.gmail.vacrosdk.commands.subcommands;

import com.gmail.vacrosdk.commands.SubCommand;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.language.Permissions;
import org.bukkit.command.CommandSender;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class ColorStandKickCommand extends SubCommand {

    public ColorStandKickCommand() {
        super(Permissions.KICK.getPermissionString());
    }

    @Override
    public void run(CommandSender sender, String[] args) {

    }

    @Override
    public void printHelp(CommandSender sender) {
        sendMessage(sender, LanguagePath.PRINT_HELP__KICK);
    }
}
