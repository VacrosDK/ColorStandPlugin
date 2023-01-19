package com.gmail.vacrosdk.utils;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import java.util.List;

public class MakeItemUtils {

    public static ItemStack newDye(DyeColor dyeColor, String name) {
        Dye dye = new Dye();
        dye.setColor(dyeColor);
        return newItem(Material.INK_SACK, 1, dye.getData(), name);

    }

    public static ItemStack newItem(Material material, int amount, short durability, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount, durability);
        return newItem(itemStack, name, lore);
    }

    public static ItemStack newItem(ItemStack itemStack, String name, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack newItem(ItemStack itemStack, int amount, short durability, String name, List<String> lore) {
        return newItem(itemStack.getType(), amount, durability, name, lore);
    }

    public static ItemStack newItem(ItemStack itemStack, int amount, short durability) {
        return newItem(itemStack.getType(), amount, durability, getName(itemStack), getLores(itemStack));
    }


    public static ItemStack newItem(ItemStack itemStack, int amount, String name, List<String> lore) {
        return newItem(itemStack.getType(), amount, name, lore);
    }

    public static ItemStack newItem(ItemStack itemStack, int amount, String name) {
        return newItem(itemStack.getType(), amount, name);
    }

    public static ItemStack newItem(Material material, int amount, short durability, String name) {
        ItemStack itemStack = new ItemStack(material, amount, durability);
        return newItem(material, amount, durability, name, getLores(itemStack));
    }

    public static ItemStack newItem(Material material, int amount, short durability) {
        ItemStack itemStack = new ItemStack(material, amount, durability);
        return newItem(material, amount, durability, getName(itemStack), getLores(itemStack));
    }

    public static ItemStack newItem(Material material, int amount) {
        ItemStack itemStack = new ItemStack(material, amount);
        return newItem(material, amount, getDurability(itemStack), getName(itemStack), getLores(itemStack));
    }

    public static ItemStack newItem(Material material, int amount, String name) {
        ItemStack itemStack = new ItemStack(material, amount);
        return newItem(material, amount, getDurability(itemStack), name, getLores(itemStack));
    }
    public static ItemStack newItem(Material material, int amount, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        return newItem(material, amount, getDurability(itemStack), name, lore);
    }

    public static ItemStack newItem(Material material, int amount, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        return newItem(material, amount, getDurability(itemStack), getName(itemStack), lore);
    }

    public static String getName(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return itemMeta.getDisplayName();
    }
    public static List<String> getLores(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return itemMeta.getLore();
    }
    public static short getDurability(ItemStack itemStack) {
        return itemStack.getDurability();
    }

}
