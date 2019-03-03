package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        String letters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "1234567890";
        StringBuffer password = new StringBuffer();

        for(int i = 0; i < 4; i ++) {
            password.append(letters.charAt((int) (Math.random() * 26)));
        }
        for(int i = 0; i < 2; i ++) {
            password.append(digits.charAt((int)( Math.random() * 10)));
        }
        for(int i = 0; i < 2; i ++) {
            password.append(letters.toUpperCase().charAt((int) (Math.random() * 26)));
        }

        try{
            outputStream.write(password.toString().getBytes());
        } catch (Exception e)
        {

        }
        return outputStream;
    }
}