package com.m3c.an;

/**
 * Author: Alessandro Noiato
 * Assignment: find the 3 most common words in a 2+ GB text file
 * Comments: The initial implementation parsed the words into a map using regExs to filter out punctuation
 *           and make each word lowercase. This process turned out to slow the application significantly, so
 *           I switched to an array lookup-based parsing (which is O(1)) and to using data structures that were
 *           more suitable for the use, a consumer to construct each word and atomicIntegers instead of integers,
 *           all of which contributed to bringing the execution speed down to ~ 43 seconds from over 220.
 */
public class App 
{
    public static void main( String[] args )

    {
        Stream stream = new Stream();
            stream.getLines("aLargeFile");
            stream.printMostFrequent(stream.sortedEntries(stream.getMap()));
    }

}
