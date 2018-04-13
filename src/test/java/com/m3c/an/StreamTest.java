package com.m3c.an;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamTest {

    private Stream stream;
    private Map<String, AtomicInteger> expectedMap = new HashMap<>();
    @Before
    public void setup() {
        stream = new Stream();
        expectedMap.put("this", new AtomicInteger(1));
        expectedMap.get("this").incrementAndGet();
        expectedMap.get("this").incrementAndGet();
        expectedMap.put("example", new AtomicInteger(1));
        expectedMap.get("example").incrementAndGet();
        expectedMap.put("project", new AtomicInteger(1));
    }

    @Test
    public void lineToWordsToMapTest() {
        stream.getLines("testSampleFile");
        Map<String, AtomicInteger> actualMap = stream.getMap();
        Assert.assertTrue(expectedMap.size() == actualMap.size() &&
                expectedMap.entrySet().stream().allMatch(e -> actualMap.containsKey(e.getKey()) &&
                    actualMap.get(e.getKey()).get() == e.getValue().get()));
    }

    @Test
    public void sortedEntriesTest() {
        stream.getLines("testSampleFile");
        List<Map.Entry<String, AtomicInteger>> actualList = stream.sortedEntries(stream.getMap());
        //List<Map.Entry<String, AtomicInteger>> expectedList = expectedMap.entrySet().stream().sorted(Comparator.comparingInt(e -> e.getValue().get())).collect(Collectors.toList());
        Assert.assertEquals(1, actualList.get(0).getValue().get());
        Assert.assertEquals("project", actualList.get(0).getKey());
        Assert.assertEquals(2, actualList.get(1).getValue().get());
        Assert.assertEquals("example", actualList.get(1).getKey());
        Assert.assertEquals(3, actualList.get(2).getValue().get());
        Assert.assertEquals("this", actualList.get(2).getKey());
    }

    @Test
    public void printMostFrequentTest() {
        stream.printMostFrequent(stream.sortedEntries(expectedMap));
    }


}