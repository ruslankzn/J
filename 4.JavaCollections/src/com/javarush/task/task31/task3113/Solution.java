package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/* 
Что внутри папки?
*/
public class Solution {
    static long totalFolders = 0;
    static long totalFiles = 0;
    static long totalSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader conReader = new BufferedReader(new InputStreamReader(System.in));
        String pathString = conReader.readLine();
        conReader.close();

        Path path = Paths.get(pathString);

        if(!Files.isDirectory(path)){
            System.out.printf(path.toAbsolutePath().toString() + " - не папка");
            return;
        }

        Files.walkFileTree(path, new Visitor());

        System.out.println("Всего папок - " + (totalFolders-1));
        System.out.println("Всего файлов - " + totalFiles);
        System.out.println("Общий размер - " + totalSize);
    }

    private static class  Visitor extends SimpleFileVisitor<Path>{

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            totalFolders +=1;
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            totalFiles +=1;
            totalSize = totalSize + attrs.size();
            return CONTINUE;
        }
    }
}
