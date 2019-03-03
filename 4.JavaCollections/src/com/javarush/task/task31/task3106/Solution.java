package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        int filePartCount = args.length - 1;
        String[] fileNamePart = new String[filePartCount];

        for(int i = 0; i < filePartCount; i ++){
            fileNamePart[i] = args[i + 1];
        }

        Arrays.sort(fileNamePart);

        List<FileInputStream> fileInputStreamList = new ArrayList<>();

        for(int i = 0; i < filePartCount; i ++){
            fileInputStreamList.add(new FileInputStream(fileNamePart[i]));
        }

        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(fileInputStreamList));
        ZipInputStream zipInputStream = new ZipInputStream(sequenceInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(resultFileName);
        byte[] buf = new byte[1024 * 1024];

        while(zipInputStream.getNextEntry() != null){
            int count;
            while((count = zipInputStream.read(buf)) != -1){
                fileOutputStream.write(buf, 0, count);
            }
        }
        sequenceInputStream.close();
        zipInputStream.close();
        fileOutputStream.close();

    }
}
