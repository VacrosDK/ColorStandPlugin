package com.gmail.vacrosdk.utils;

import org.bukkit.Bukkit;

import static org.bukkit.Bukkit.getServer;

public class ConsoleCommandUtils {

    public static void sendConsoleCommand(String command) {
        Bukkit.getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }

}
