package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);
        OurHashMapStorageStrategy strategy2 = new OurHashMapStorageStrategy();
        testStrategy(strategy2, 10000);
        FileStorageStrategy strategy3 = new FileStorageStrategy();
        testStrategy(strategy3, 500);
        OurHashBiMapStorageStrategy strategy4 = new OurHashBiMapStorageStrategy();
        testStrategy(strategy4, 10000);
        HashBiMapStorageStrategy strategy5 = new HashBiMapStorageStrategy();
        testStrategy(strategy5, 10000);
        DualHashBidiMapStorageStrategy strategy6 = new DualHashBidiMapStorageStrategy();
        testStrategy(strategy6, 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();
        for(String s: strings)
            result.add(shortener.getId(s));
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();
        for(Long id: keys)
            result.add(shortener.getString(id));
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        //Выводить имя класса стратегии. Имя не должно включать имя пакета
        System.out.println(strategy.getClass().getSimpleName());
        //Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber.
        Set<String> strings = new HashSet<>();
        Long[] elements = new Long[(int) elementsNumber];
        for(int i = 0; i < elementsNumber; i ++)
            strings.add(Helper.generateRandomString());
         //Создавать объект типа Shortener, используя переданную стратегию.
        Shortener shortener = new Shortener(strategy);
        //Замерять и выводить время необходимое для отработки метода getIds для заданной стратегии и заданного множества
        //элементов. Время вывести в миллисекундах. При замере времени работы метода можно пренебречь переключением
        //процессора на другие потоки, временем, которое тратится на сам вызов, возврат значений и вызов методов
        //получения времени (даты). Замер времени произведи с использованием объектов типа Date.
        Date startDate = new Date();
        Set<Long> ids = getIds(shortener, strings);
        Date finishDate = new Date();
        long deltaTime = finishDate.getTime() - startDate.getTime();
        Helper.printMessage(Long.toString(deltaTime));
        //Замерять и выводить время необходимое для отработки метода getStrings для заданной стратегии и полученного в
        //предыдущем пункте множества идентификаторов.
        startDate = new Date();
        Set<String> stringSet = getStrings(shortener, ids);
        finishDate = new Date();
        deltaTime = finishDate.getTime() - startDate.getTime();
        Helper.printMessage(Long.toString(deltaTime));
        //Сравнивать одинаковое ли содержимое множества строк, которое было сгенерировано и множества, которое было
        //возвращено методом getStrings. Если множества одинаковы, то выведи "Тест пройден.", иначе "Тест не пройден.".
        if(strings.equals(stringSet))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}
