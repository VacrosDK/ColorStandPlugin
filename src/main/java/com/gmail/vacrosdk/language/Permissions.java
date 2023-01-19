package com.gmail.vacrosdk.language;

import org.bukkit.entity.Player;

import static com.gmail.vacrosdk.utils.ConsoleCommandUtils.sendConsoleCommand;

public enum Permissions {
    GETBROADCAST("colorStand.getBroadcasts"),
    GETTITLES("colorStand.getTitles"),
    KICK("colorStand.kick"),
    LEAVE("colorStand.leave"),
    PLAYERLIST("colorStand.playerlist"),
    START("colorStand.start"),
    RESET("colorStand.reset"),
    RELOAD("colorStand.reload"),
    CLOSE("colorStand.close"),
    OPEN("colorStand.open"),
    JOIN("colorStand.join");

    private final String permissionString;

    Permissions(String permissionString) {
        this.permissionString = permissionString;
    }

    public String getPermissionString() {
        return permissionString;
    }

    public static void setDefaultPermissions(Player p, boolean state) {
        String method;
        method = setMethod(state);
        sendConsoleCommand("lp user " + p.getName() + " perm " + method + " " +  Permissions.GETBROADCAST.getPermissionString());
        sendConsoleCommand("lp user " + p.getName() + " perm " + method + " " + Permissions.GETTITLES.getPermissionString());
    }

    private static String setMethod(boolean state) {
        String method;
        if(state) {
            method = "set";
        }else{
            method = "unset";
        }
        return method;
    }
}
