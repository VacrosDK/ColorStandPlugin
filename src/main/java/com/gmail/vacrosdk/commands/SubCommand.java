package com.gmail.vacrosdk.commands;

import com.gmail.vacrosdk.exception.LackOfBlocksException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {
    private String permission;
    private String noPermMessage = "§cYou do not have permission to do this";
    private int minLength = 0;
    private boolean isPlayerCommand;

    protected SubCommand(String permission, String noPermMessage) {
        this.permission = permission;
        this.noPermMessage = noPermMessage;
    }
    protected SubCommand(String permission) {
        this.permission = permission;
    }

    public SubCommand() {
    }

    public boolean hasPermission(CommandSender sender) {
        if(permission.isEmpty()) {
            return true;
        }
        return sender.hasPermission(permission);
    }


    public abstract void run(CommandSender sender, String[] args) throws LackOfBlocksException;

    public abstract void printHelp(CommandSender sender);

    public void setMinLength(int length) {
        this.minLength = length;
    }

    public void setPlayerCommand(boolean playerCommand) {
        isPlayerCommand = playerCommand;
    }

    protected void execute(CommandSender sender, String[] args) throws LackOfBlocksException {
        if (!permission.isEmpty() && !sender.hasPermission(permission)) {
            sender.sendMessage(noPermMessage);
            return;
        }
        if (args.length < minLength) {
            printHelp(sender);
            return;
        }
        if (isPlayerCommand && !(sender instanceof Player)) {
            return;
        }
        run(sender, args);
    }

}
