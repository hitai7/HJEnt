package com.hyo.smart.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class HashMapUtil {
	
	private static Map<String, Object> map = new HashMap<>();
    
    public static void read(String text) {
        for (String word : text.split(" ")) {
            if (map.containsKey(word)) {
                Integer value = (Integer) map.get(word);
                map.put(word, ++value);
            }
        }
        System.out.println(map.toString());
    }
    
    public static void read_java8(String text) {
    	for (String word : text.split(" ")) {
//    		map.computeIfPresent(word, (String key, Integer value) -> ++value);
    		map.computeIfPresent(word, (String key, Object value) -> (int) value + 1);
    	}
    	System.out.println(map.toString());
	}
    
    public static void checkKeyValue() {
    	Set<Entry<String, Object>> entries = map.entrySet();
    	for (Entry<String, Object> entry : entries) {
    		System.out.println("KEY: " + entry.getKey());
    		System.out.println("VALUE: " + entry.getValue());
	    }    	
    	// Lambda expression
    	System.out.println("> Lambda expression");
    	map.forEach((key, value) -> {
    		System.out.println("KEY: " + key);
    		System.out.println("VALUE: " + value);
        });
    }
    
    // Convert List into Map.
    public static Map<Integer, List<String>> getMap(List<String> strings) {
        return strings.stream().collect(
          Collectors.groupingBy(String::length, Collectors.toCollection(ArrayList::new))
        );
    }
        
	public static void main(String[] args) throws UnsupportedEncodingException {		
	    map.put("Option_1", 0);
        map.put("Option_2", 0);
        map.put("Option_3", 0);
        
//        read("Option_1 Option_1 Option_2");
//        System.out.println("> read_java8");
        read_java8("Option_1 Option_1 Option_2");
        
//        checkKeyValue();
        
        List resList = new ArrayList<>();
        resList.add("test1");
        resList.add("test12");
        resList.add("test123");
        
        System.out.println(getMap(resList));
	}    
}
