package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.exception.LackOfBlocksException;
import com.gmail.vacrosdk.language.config.ConfigManager;
import com.gmail.vacrosdk.types.DifficultyType;
import com.gmail.vacrosdk.utils.ConvertSetToListUtils;
import com.gmail.vacrosdk.utils.RandomElementPicker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaterialManager {
    private final JavaPlugin plugin;
    private final Set<String> easyMaterialSet = new HashSet<>();
    private final Set<String> mediumMaterialSet = new HashSet<>();
    private final Set<String> hardMaterialSet = new HashSet<>();
    private final List<String> currentMaterialSet = new ArrayList<>();
    private String targetMaterial;

    public MaterialManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void reset() {
        easyMaterialSet.clear();
        mediumMaterialSet.clear();
        hardMaterialSet.clear();
        currentMaterialSet.clear();
        targetMaterial = null;
    }

    public void load(ConfigManager configManager) throws LackOfBlocksException {
        loadEasyMaterials(configManager);
        loadMediumMaterials(configManager);
        loadHardMaterials(configManager);
        System.out.println("Easy: " + easyMaterialSet);
        System.out.println("Medium: " + mediumMaterialSet);
        System.out.println("Hard: " + hardMaterialSet);
    }

    public void createNewMaterialsForRound(DifficultyType type) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= 6; i++) {
            System.out.println("times: " + i);
            set.add(returnRandomMaterial(type));
        }
        targetMaterial = RandomElementPicker.randomMaterialFromSet(set);
        currentMaterialSet.addAll(set);
        System.out.println("----------------------------------------------------------- " + currentMaterialSet);
    }

    public String getTargetMaterial() {
        return targetMaterial;
    }

    public List<String> getMaterialSet() {
        return currentMaterialSet;
    }

    public String returnRandomMaterial(DifficultyType type) {
        if(type == DifficultyType.EASY) {
            return RandomElementPicker.randomMaterialFromSet(easyMaterialSet);
        }else if(type == DifficultyType.MEDIUM) {
            return RandomElementPicker.randomMaterialFromSet(mediumMaterialSet);
        }else {
            return RandomElementPicker.randomMaterialFromSet(hardMaterialSet);
        }
    }

    private void loadEasyMaterials(ConfigManager configManager) throws LackOfBlocksException {
        easyMaterialSet.addAll(loadMaterials(configManager, "easy"));
    }

    private void loadMediumMaterials(ConfigManager configManager) throws LackOfBlocksException {
        mediumMaterialSet.addAll(loadMaterials(configManager, "medium"));
    }

    private void loadHardMaterials(ConfigManager configManager) throws LackOfBlocksException {
        hardMaterialSet.addAll(loadMaterials(configManager, "hard"));
    }

    private Set<String> loadMaterials(ConfigManager configManager, String path) throws LackOfBlocksException {
        List<String> blockNameList = configManager.getStringList(path);
        if(blockNameList.size() <= 6) {
            throw new LackOfBlocksException("There needs to be 6 blocks in each category.");
        }
        return ConvertSetToListUtils.listToSet(blockNameList);
    }
}
