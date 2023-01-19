package com.gmail.vacrosdk.language.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager extends YamlConfiguration {
    private final JavaPlugin plugin;
    private final String configName;

    public ConfigManager(JavaPlugin plugin, String configName) {
        this.plugin = plugin;
        this.configName = configName;
        createConfigIfNotExists();
    }

    public void createConfigIfNotExists() {
        File pluginFolder = new File("plugins" + System.getProperty("file.separator") + plugin.getName());
        if (!pluginFolder.exists()) {
            pluginFolder.mkdir();
        }

        File configFile = new File(getDataFolder() + configName);
        if (!configFile.exists()) {
            plugin.getLogger().info("No config file found! Creating new one...");
            saveDefaultConfig();
        }
        try {
            plugin.getLogger().info("Loading " + configName + "...");
            reloadCustomConfig();

        } catch (Exception e) {
            plugin.getLogger().severe(
                    "Could not load " + configName + "! You need to regenerate the config! Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void reloadCustomConfig() {
        File configFile = new File(getDataFolder(), configName);
        try {
            Path path = configFile.toPath();
            load(new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8));
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        File file = new File(getDataFolder(), configName);
        try {
            String data = saveToString();
            Writer writer = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8);
            try {
                writer.write(data);
            } finally {
                writer.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDefaultConfig() {
        plugin.saveResource(configName, false);
    }

    private String getDataFolder() {
        return "plugins" + System.getProperty("file.separator") + plugin.getName() + System.getProperty("file.separator");
    }

}
