package cacheapp.phonepe.com.cacheapp;

import java.util.LinkedHashMap;

public class TestUtilities {

    public static void putElementsInCache(LinkedHashMap<Integer,Integer> linkedHashMap, int[] numbers) {
        for (int number: numbers
             ) {
            linkedHashMap.put(number,1);
        }
    }
}
