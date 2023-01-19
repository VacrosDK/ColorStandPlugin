package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.info.PlayerInfo;
import com.gmail.vacrosdk.types.JoinType;
import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class PlayerManager {
    private final Map<UUID, Float> playerXpMap = new HashMap<>();
    private final Set<UUID> playerSet = new HashSet<>();
    private final StatusManager statusManager;

    public PlayerManager(StatusManager statusManager) {
        this.statusManager = statusManager;
    }

    public void add(UUID uuid) {
        List<String> list = Utils.toList(LanguagePath.JOIN_INFO__MESSAGE);
        sendMessage(Bukkit.getPlayer(uuid), list);
        Player player = Bukkit.getPlayer(uuid);
        float exp = player.getExp();
        playerXpMap.put(uuid, exp);
        playerSet.add(uuid);
    }

    public boolean isTwoPlayersLeft() {
        return playerSet.size() == 2;
    }

    public void remove(UUID uuid) {
        playerSet.remove(uuid);
        Player player = Bukkit.getPlayer(uuid);
        player.setExp(playerXpMap.get(uuid));
        playerXpMap.remove(uuid);
    }

    public Set<UUID> getPlayerSet() {
        return playerSet;
    }

    public boolean isMoreThan1Player() {
        return playerSet.size() > 1;
    }


    public boolean contains(UUID uuid) {
        return playerSet.contains(uuid);
    }

    public JoinType canJoin(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        if (p == null) {
            return JoinType.OFFLINE_PLAYER;
        }
        if (contains(p.getUniqueId())) {
            sendMessage(p, LanguagePath.JOIN_REASONS_ALREADY__JOINED.replace("%player%", "Du"));
            return JoinType.ALREADY_JOINED;
        }
        if (!statusManager.isOpen()) {
            sendMessage(p, LanguagePath.JOIN_REASONS_GAME__CLOSED);
            return JoinType.GAME_CLOSED;
        }
        if (statusManager.isStarted()) {
            sendMessage(p, LanguagePath.JOIN_REASONS_GAME__STARTED);
            return JoinType.GAME_STARTED;
        }
        return JoinType.CAN_JOIN;
    }

    public void reset() {
        playerSet.clear();
    }
}
