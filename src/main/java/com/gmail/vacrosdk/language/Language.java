package com.gmail.vacrosdk.language;

import com.gmail.vacrosdk.language.config.ConfigManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Language {
    private static ConfigManager configManager;

    public static void setConfigManager(ConfigManager configManager) {
        Language.configManager = configManager;
    }

    public static void sendMessage(CommandSender p, String message) {
        p.sendMessage(message);
    }

    public static void sendMessage(CommandSender p, List<String> messages) {
        messages.forEach(message ->{
            sendMessage(p, message);
        });
    }

    public static void sendClickableMessage(Player p, String command, String message) {
        TextComponent textComponent = new TextComponent(message);
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        p.spigot().sendMessage(textComponent);
    }


    public static void sendBroadcast(String message) {
        Bukkit.broadcast(message, Permissions.GETBROADCAST.getPermissionString());
    }

    public static String getPath(String languagePath) {
        return configManager.getString(languagePath);
    }
}
