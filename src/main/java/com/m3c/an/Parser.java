package com.m3c.an;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Parser {

    private Map<String, Integer> map = new HashMap();
    private static char[] onlyLettersToLowercase = new char[65535];
    static {
        onlyLettersToLowercase[' '] = ' ';
        for (char c = 'a'; c <= 'z'; c++) {
            onlyLettersToLowercase[c] = c;
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            onlyLettersToLowercase[c] = Character.toLowerCase(c);
        }
    }

    private Consumer<String> countWords = word -> map.computeIfAbsent(word, (w) -> new Integer(1));

    public void getLines(String filename) {
        long a, b;
        a = System.currentTimeMillis();
        try (BufferedReader fileReader = new BufferedReader(new FileReader("resources/" + filename))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                lineToWordsToMap(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        b = System.currentTimeMillis();

        System.out.println((b - a)/1000);

    }

    private void lineToWordsToMap(String line) {
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
            this.countWords.accept(sb.toString());
        }
    }

    public Map<String, Integer> getMap() {
        return this.map;
    }

    public static List<Map.Entry<String, Integer>> sortedEntries(
            Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
    public void printMostFrequent(List<Map.Entry<String, Integer>> orderedList) {
        for (int i = 0; i < 10; i++) {
            System.out.println(orderedList.get(i).getKey() + " : "
                    + orderedList.get(i).getValue());
        }
    }
}

