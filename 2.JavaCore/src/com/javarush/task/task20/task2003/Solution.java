 package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

 /*
 Знакомство с properties
 */
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
     public static Properties prop = new Properties();
     public static String filName;

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        BufferedReader conReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            filName = conReader.readLine();
            conReader.close();
            FileInputStream fileInputStream = new FileInputStream (Solution.filName);
            load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        prop.clear();
        for(Map.Entry<String, String> pair: properties.entrySet())
            prop.put(pair.getKey(),pair.getValue());
        prop.store(outputStream,"");

    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        prop.load(inputStream);
        for(Map.Entry<Object,Object> pair: prop.entrySet())
            properties.put((String) pair.getKey(), (String) pair.getValue());
    }

    public static void main(String[] args) {

    }
}
