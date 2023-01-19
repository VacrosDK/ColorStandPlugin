package com.gmail.vacrosdk.utils;

import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class TitleUtils {

    public static void sendTitle(Player player, String title, String subtitle,  int fadeInTime, int showTime, int fadeOutTime) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PacketPlayOutTitle packetSubtitle;
        if (title != null) {
            packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, CraftChatMessage.fromString(title)[0], fadeInTime, showTime, fadeOutTime);
            craftPlayer.getHandle().playerConnection.sendPacket(packetSubtitle);
        }
        if (subtitle != null) {
            packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, CraftChatMessage.fromString(subtitle)[0], fadeInTime, showTime, fadeOutTime);
            craftPlayer.getHandle().playerConnection.sendPacket(packetSubtitle);
        }
    }

    public static void sendTitle(Player player, String title, String subtitle) {
        sendTitle(player, title, subtitle, 20, 4*20 , 20);
    }

    public static void broadcastTitle(String title, String subtitle,  int fadeInTime, int showTime, int fadeOutTime) {
        Bukkit.getOnlinePlayers().forEach(player -> sendTitle(player, title, subtitle, fadeInTime, showTime, fadeOutTime));
    }

    public static void broadcastTitle(String title) {
        broadcastTitle(title, null, 20, 4*20, 20);
    }

    public static void sendTitle(Set<UUID> players, String title, String subtitle) {
        players.forEach(playerUUID -> {
            Player player = Bukkit.getPlayer(playerUUID);
            if(player != null) {
                sendTitle(player, title, subtitle);
            }
        });
    }



}
