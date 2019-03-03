package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        String[] massiv = getTokens("One, Two, Three, Four", ",");

        for(String s:massiv)
            System.out.println(s);

    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer sT = new StringTokenizer(query,delimiter);
        String[] result = new String[sT.countTokens()];

        for(int i = 0; sT.hasMoreElements(); i++)
            result[i] = sT.nextToken();
        return result;
    }
}
