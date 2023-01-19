package com.gmail.vacrosdk.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocationUtils {
    private static final Pattern pattern = Pattern.compile("([^,]*),\\s*(-?\\d*\\.?\\d*),\\s*(-?\\d*\\.?\\d*),\\s*(-?\\d*\\.?\\d*),?\\s*(-?\\d*\\.?\\d*),?\\s*(-?\\d*\\.?\\d*)");

    public static Location stringToLocation(String stringLocation) {
        String stringLocationWithoutSpaces = stringLocation.replace(" ", "");
        Matcher matcher = pattern.matcher(stringLocationWithoutSpaces);
        if (matcher.matches()) {
            String stringWorld = matcher.group(1);
            World world = Bukkit.getWorld(stringWorld);
            if (world == null) {
                Bukkit.getLogger().warning("World is null: " + stringWorld + " - " + stringLocation);
            }
            float yaw = 0;
            float pitch = 0;
            double x = (Double.parseDouble(matcher.group(2)));
            double y = (Double.parseDouble(matcher.group(3)));
            double z = (Double.parseDouble(matcher.group(4)));
            if (!(matcher.group(5)).isEmpty()) {
                yaw = Float.parseFloat(matcher.group(5));
            }
            if (!(matcher.group(6)).isEmpty()) {
                pitch = Float.parseFloat(matcher.group(6));
            }
            return new Location(world, x, y, z, yaw, pitch);
        }
        System.out.println("location does not match without spaces: " + stringLocation + " - " + stringLocationWithoutSpaces);
        return null;
    }
}


