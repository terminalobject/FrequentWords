package com.m3c.an;

/**
 * Author: Alessandro Noiato
 * Assignment:
 */
public class App 
{
    public static void main( String[] args )

    {
        Stream stream = new Stream();
            stream.getLines();
            stream.printMostFrequent(stream.sortedEntries(stream.getMap()));
    }

}
