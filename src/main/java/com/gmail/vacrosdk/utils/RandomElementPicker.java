package com.gmail.vacrosdk.utils;

import java.util.Random;
import java.util.Set;

public class RandomElementPicker {

    public static String randomMaterialFromSet(Set<String> set) {
        int size = set.size();
        System.out.println("1 Sizen er: " + size);
        int item = new Random().nextInt(size);
        System.out.println("2 Itemet er: " + item);
        int i = 0;
        for(String material : set)
        {
            if (i == item)
                return material;
            i++;
        }
        return null;
    }

    public static String randomStringFromSet(Set<String> set) {
        int size = set.size();
        System.out.println("3 Sizen er: " + size);
        int item = new Random().nextInt(size);
        System.out.println("4 Itemet er: " + item);
        int i = 0;
        for(String string: set)
        {
            if (i == item)
                return string;
            i++;
        }
        return null;
    }
}
