package com.m3c.an;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.Comparator;

public class Stream {

    private Map<String, AtomicInteger> map = new HashMap();
    private static char[] onlyLettersToLowercase = new char[65535];
    static {
        onlyLettersToLowercase[' '] = ' ';
        for (char c = 'a'; c <= 'z'; c++) {
            onlyLettersToLowercase[c] = c;
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            onlyLettersToLowercase[c] = c;
        }
    }

    Consumer<String> countWords = word -> map.computeIfAbsent(word, (w) -> new AtomicInteger(0)).incrementAndGet();

    public void getLines() {

        try (BufferedReader fileReader = new BufferedReader(new FileReader("resources/aSampleFile"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                lineToWordsToMap(line, countWords);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sorting");
        Object[] a = map.entrySet().toArray();
        Arrays.sort(a, Comparator.comparingInt((Object entry) -> entry.getValue.get()));//(Comparator) (o1, o2) -> ((Map.Entry<String, AtomicInteger>) o2).getValue().get() - (((Map.Entry<String, AtomicInteger>) o2).getValue().get()));


//        Arrays.sort(a, new Comparator() {
//            public int compare(Object o1, Object o2) {
//                return ((Map.Entry<String, AtomicInteger>) o2).getValue()
//                        .compareTo(((Map.Entry<String, AtomicInteger>) o1).getValue());
//            }
//        })
//
        for (int i = 0; i < 3; i++) {
            System.out.println(((Map.Entry<String, AtomicInteger>) a[i]).getKey() + " : "
                    + ((Map.Entry<String, AtomicInteger>) a[i]).getValue());
        }
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
    public void lineToWordsToMap(String line, Consumer<String> countWords) {
        char[] characters = line.toCharArray();
        StringBuilder sb = new StringBuilder(16);
        for (int index = 0; index < characters.length; index++) {
            char ch = characters[index];
            char replacementCh = onlyLettersToLowercase[ch];
            // If we encounter a space
            if (replacementCh == ' ') {
                // And there is a word in string builder
                if (sb.length() > 0) {
                    // Send this word to the consumer
                    countWords.accept(sb.toString());
                    // Reset the string builder
                    sb.setLength(0);
                }
            } else if (replacementCh != 0) {
                sb.append(replacementCh);
            }
        }
        // Send the last word to the consumer
        if (sb.length() > 0) {
            countWords.accept(sb.toString());
        }
    }
    public Map<String, AtomicInteger> getMap() {
        return this.map;
    }
}

