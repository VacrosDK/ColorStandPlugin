package com.gmail.vacrosdk.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static List<String> toList(String string) {
        return Arrays.stream(string.split("\n")).collect(Collectors.toList());
    }

    public static String formatDouble(double db) {
        DecimalFormat df = new DecimalFormat("0.##");

        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(db);
    }

    public static ItemStack setLore(ItemStack itemStack, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void executeCommands(List<String> stringList, Player p) {
        stringList.forEach(string -> {
            try {
                String cmd = string.replace("%player%", p.getName());
                System.out.println("Executing command: " + cmd);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                System.out.println("Finished executing cmd");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


}
