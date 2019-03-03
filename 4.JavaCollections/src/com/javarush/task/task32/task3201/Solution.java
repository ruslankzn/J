package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long number = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        number = number > file.length() ? file.length() : number;
        file.seek(number);
        file.write(text.getBytes());
        file.close();
    }
}
