package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

      //  String s = "http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo";

        String[] sTemp = s.split("\\?");
        String[] sParams = sTemp[1].split("\\&");

        String[] string= null;

        for(String s1: sParams){
            String[] stringTemp = s1.split("=");
            if("obj".equals(stringTemp[0])){
                string = stringTemp;
            }
            System.out.print(stringTemp[0]+" ");
        }
        System.out.println();
        if(string!=null){
            try {
                alert(Double.parseDouble(string[1]));
            } catch (NumberFormatException e) {
                alert(string[1]);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
