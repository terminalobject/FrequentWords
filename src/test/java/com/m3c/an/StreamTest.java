package com.m3c.an;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StreamTest {

    private String line;

    @Before
    public void setupLine() {
        line = "This is an Example line!";
    }

//    @Test
//    public void wordsToMapTest() {
//        Stream stream = new Stream();
//        Map<String, Integer> map = new HashMap<>();
//        map.put("this", 1);
//        map.put("is", 1);
//        map.put("an", 1);
//        map.put("example", 1);
//        map.put("line", 1);
//        String[] words = new String[]{"this", "is", "an", "example", "line"};
//        stream.wordsToMap(words);
//        assertEquals( map, stream.getMap() );
//    }

}