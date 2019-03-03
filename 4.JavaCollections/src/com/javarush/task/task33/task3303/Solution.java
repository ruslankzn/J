package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader;
        T result = null;
        result = mapper.readValue(new FileReader(new File(fileName)), clazz);
        return result;
    }

    public static void main(String[] args) {

    }
}
