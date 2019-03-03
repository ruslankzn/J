package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if(s == null || s.length() == 0)
            return 0;

        ArrayList<Integer> list = new ArrayList<>();

        for(int o = 0; o < s.length(); o++){
            String s1 = s.substring(o, s.length());

            StringBuffer noRepeatLettersString = new StringBuffer();
            noRepeatLettersString.append(s1.charAt(0));
            exit:
            for (int i = 1; i < s1.length(); i ++){
                for(int j = 0; j < noRepeatLettersString.length(); j ++){
                    if(noRepeatLettersString.charAt(j) == s1.charAt(i))
                        break exit;
                }
                noRepeatLettersString.append(s1.charAt(i));
            }
            list.add(noRepeatLettersString.toString().length());
        }
        return Collections.max(list);
    }
}
