package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    //1
    private static BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

    //2
    public static void writeMessage(String message){
        System.out.println(message);
    }
    //3
    public static String readString(){
        String message;
        while(true){
            try {
                message = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
        return message;
    }

    //4
    public static int readInt(){
        int i;
        while(true){
            try {
                i = Integer.parseInt(readString());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
        return i;
    }
}
