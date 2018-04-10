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

    private Consumer<String> countWords = word -> map.computeIfAbsent(word, (w) -> new AtomicInteger(0)).incrementAndGet();

    public void getLines() {
        long a, b;
        a = System.currentTimeMillis();
        try (BufferedReader fileReader = new BufferedReader(new FileReader("resources/aSampleFile"))) {
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

        printMostFrequent(sortedEntries(map));
        System.out.println((b - a)/1000);

    }

    public void lineToWordsToMap(String line) {
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

    private static List<Map.Entry<String, AtomicInteger>> sortedEntries(
            Map<String, AtomicInteger> map) {
        return map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().get()))
                .collect(Collectors.toList());
    }
    public void printMostFrequent(List<Map.Entry<String, AtomicInteger>> orderedList) {
        for (int i = orderedList.size() - 1; i > orderedList.size() - 4; i--) {
            System.out.println(orderedList.get(i).getKey() + " : "
                    + orderedList.get(i).getValue());
        }
    }
}

