package com.gmail.vacrosdk.utils;

import java.util.*;

public class ConvertSetToListUtils {

    public static List<UUID> setToList(Set<UUID> set) {
        List<UUID> list = new ArrayList(set);
        return list;
    }


    public static Set<UUID> setToListUUID(List<UUID> uuids) {
        return new HashSet<>(uuids);
    }

    public static Set<String> listToSet(List<String> list) {
        return new HashSet<>(list);
    }

}
