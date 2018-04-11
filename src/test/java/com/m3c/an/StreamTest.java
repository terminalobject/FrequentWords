package com.m3c.an;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.accessibility.AccessibleRole;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class StreamTest {

    private Stream stream;
    private Map<String, AtomicInteger> map = new HashMap<>();
    @Before
    public void setup() {
        stream = new Stream();
        map.put("this", new AtomicInteger(1));
        map.put("is", new AtomicInteger(1));
        map.put("an", new AtomicInteger(1));
        map.put("just", new AtomicInteger(1));
        map.put("example", new AtomicInteger(1));
        map.put("file", new AtomicInteger(1));
        map.put("for", new AtomicInteger(1));
        map.get("this").incrementAndGet();
        map.put("project", new AtomicInteger(1));
    }

    @Test
    public void lineToWordsToMapTest() {
        stream.getLines("testSampleFile");
        Map<String, AtomicInteger> sortedmap = new TreeMap<>(stream.getMap());
        Map<String, AtomicInteger> treemap = new TreeMap<>(map);  // Maps must be sorted in order to pass test
        //Assert.assertTrue(treemap, sortedmap);
    }

    @Test
    public void sortedEntriesTest() {
        List<Map.Entry<String, AtomicInteger>> actualList = stream.sortedEntries(map);
        List<Map.Entry<String, AtomicInteger>> expectedList;

    }

}