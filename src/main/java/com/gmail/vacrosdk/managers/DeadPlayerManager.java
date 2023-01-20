package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.language.LanguagePath;
import com.gmail.vacrosdk.types.JoinType;
import com.gmail.vacrosdk.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

import static com.gmail.vacrosdk.language.Language.sendMessage;

public class DeadPlayerManager {
    private final Set<UUID> deadPlayerSet = new HashSet<>();
    private final StatusManager statusManager;

    public DeadPlayerManager(StatusManager statusManager) {
        this.statusManager = statusManager;
    }

    public void add(UUID uuid) {
        List<String> list = Utils.toList(LanguagePath.JOIN_INFO__MESSAGE);
        sendMessage(Bukkit.getPlayer(uuid), list);
        deadPlayerSet.add(uuid);
    }

    public void remove(UUID uuid) {
        deadPlayerSet.remove(uuid);
    }

    public Set<UUID> getDeadPlayerSet() {
        return deadPlayerSet;
    }

    public boolean contains(UUID uuid) {
        return deadPlayerSet.contains(uuid);
    }

    public void reset() {
        deadPlayerSet.clear();
    }
}
