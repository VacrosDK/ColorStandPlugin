package com.gmail.vacrosdk.language;

import com.gmail.vacrosdk.language.config.ConfigManager;
import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomLanguage {
    private final static Pattern pattern = Pattern.compile("(__.)");


    public static void load(ConfigManager config, Class<? extends CustomLanguage> cLangClass) {
        config.reloadCustomConfig();
        loadConfigValues(config, cLangClass);
        config.save();
    }

    private static void loadConfigValues(ConfigManager config, Class<? extends CustomLanguage> cLangClass) {
        Set<String> previousUsedFieldNames = new HashSet<>();
        for (Field declaredField : cLangClass.getDeclaredFields()) {
            try {
                String fieldName = formatFieldName(declaredField);
                checkForDuplicates(previousUsedFieldNames, fieldName);

                if (!config.contains(fieldName)) {
                    updateConfigValue(config, declaredField, fieldName);
                }
                setFieldValue(config, declaredField, fieldName);
            } catch (LanguageFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateConfigValue(ConfigManager config, Field declaredField, String fieldName) {
        try {
            String fieldValue = (String) declaredField.get(null);
            config.set(fieldName, fieldValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void setFieldValue(ConfigManager config, Field declaredField, String fieldName) throws LanguageFormatException {
        try {
            boolean accessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            checkIfPathIsString(config, fieldName);
            String configValue = ChatColor.translateAlternateColorCodes('&', config.getString(fieldName));
            declaredField.set(null, configValue);
            declaredField.setAccessible(accessible);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void checkIfPathIsString(ConfigManager config, String fieldName) throws LanguageFormatException {
        Object o = config.get(fieldName);
        if (!(o instanceof String)) {
            throw new LanguageFormatException(fieldName + " path is not a string: " + fieldName);
        }
    }

    private static void checkForDuplicates(Set<String> previousUsedFieldNames, String fieldName) throws LanguageFormatException {
        for (String previousUsedFieldName : previousUsedFieldNames) {
            if (previousUsedFieldName.startsWith(fieldName) || fieldName.startsWith(previousUsedFieldName)) {
                throw new LanguageFormatException(fieldName + " already exists in the language file: " + previousUsedFieldName);
            }
        }
        previousUsedFieldNames.add(fieldName);
    }

    private static String formatFieldName(Field declaredField) {
        String fieldName = declaredField.getName().toLowerCase();
        Matcher matcher = pattern.matcher(fieldName);
        while (matcher.find()) {
            String group = matcher.group(1);
            fieldName = fieldName.replace(group, group.substring(2).toUpperCase());
        }
        fieldName = fieldName.replace("_", ".");
        return fieldName;
    }

}
