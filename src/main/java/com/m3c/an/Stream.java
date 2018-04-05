package com.m3c.an;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Stream {

    Map<String, Integer> map = new HashMap();

    public void getLines() {

        try (BufferedReader fileReader = new BufferedReader(new FileReader("resources/aLargeFile"))) {
            String line = fileReader.readLine();
            while (line != null) {
                String[] words = line.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    if (map.get(words[i]) == null) {
                        map.put(words[i], 1);
                    }
                    else {
                        int newValue = Integer.valueOf(String.valueOf(map.get(words[i])));
                        newValue++;
                        map.put(words[i], newValue);
                    }
                }
                line = fileReader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[] a = map.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        for (int i = 0; i < 3; i++) {
            System.out.println(((Map.Entry<String, Integer>) a[i]).getKey() + " : "
                    + ((Map.Entry<String, Integer>) a[i]).getValue());
        }



        //List<Map.Entry<String, String>> result = map.
//        int highest = highestFrequency(map);
//        String[] mostCommonWords = new String[highest];
//        for (String key : map.keySet()) {
//            if (map.get(key) > 10) {
//                mostCommonWords[map.get(key) - 1] = "";
//                mostCommonWords[map.get(key) - 1] += key;
//            }
//        }
//
//
//        int printCounter = 0;
//        for (int i=highest - 1; i > 10; i--) {
//            if (mostCommonWords[i] != null && printCounter < 3) {
//                    printCounter++;
//                    System.out.println(mostCommonWords[i] + " Count " + (i)); }
//        }

//        Map<String, Integer> sortedWords = new TreeMap<>(map);
//        for (Object key : sortedWords.keySet()) {
//            System.out.println("Word: " + key + "\tCounts: " + map.get(key));
//        }
    }

    public Map<String, Integer> sortByValue(Map<String, Integer> map) {
            List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

                public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
                    return (m2.getValue()).compareTo(m1.getValue());
                }
            });

            Map<String, Integer> result = new LinkedHashMap<String, Integer>();
            for (Map.Entry<String, Integer> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }
            return result;
        }

    public int highestFrequency(Map<String, Integer> map) {
        int highestFrequency = 0;
        for (String key: map.keySet()) {
            if (map.get(key) > highestFrequency) {
                highestFrequency = map.get(key);
            }
        }
        return highestFrequency;
    }

}

