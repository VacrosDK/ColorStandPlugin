package com.gmail.vacrosdk.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class SkullManager {
    private final Map<UUID, ItemStack> playerSkullMap = new HashMap<>();
    private final JavaPlugin plugin;
    private Field field;

    public SkullManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private void setTexture(ItemStack item, SkullMeta meta, final UUID uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String texture;
            String signature;
            String uuidString = StringUtils.replace(uuid.toString(), "-", "");
            URL url = null;

            try {
                url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuidString +
                        "?unsigned=false");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return;
            }

            try {
                InputStreamReader reader = new InputStreamReader(url.openStream());
                JsonObject json = new JsonParser().parse(reader).getAsJsonObject().get("properties")
                        .getAsJsonArray().get(0).getAsJsonObject();
                texture = json.get("value").getAsString();
                signature = json.get("signature").getAsString();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", texture, signature));
            try {
                if (field == null) {
                    field = meta.getClass().getDeclaredField("profile");
                }
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                field.set(meta, profile);
                field.setAccessible(accessible);
                item.setItemMeta(meta);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Bukkit.getServer().getLogger().log(Level.SEVERE, "Please report this to the developer", e);
            }

        });
    }

    private ItemStack getPlayerHead(UUID uuid) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        setTexture(skull, meta, uuid);
        skull.setItemMeta(meta);
        return skull;
    }

    public void addSkull(UUID uuid) {
        if (playerSkullMap.containsKey(uuid)) {
            return;
        }
        ItemStack playerHead = getPlayerHead(uuid);
        playerSkullMap.put(uuid, playerHead);
    }

    public ItemStack getSkull(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        return getSkull(player.getUniqueId());
    }

    public ItemStack getSkull(UUID uuid) {
        if (!playerSkullMap.containsKey(uuid)) {
            addSkull(uuid);
        }
        System.out.println("Getting a skull: " + uuid);
        System.out.println(playerSkullMap.get(uuid));
        return playerSkullMap.get(uuid);
    }

    public void reset() {
        playerSkullMap.clear();
    }

}
