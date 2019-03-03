package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static<T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList<T> resultList = new ArrayList<>();
        for(T el: elements)
            resultList.add(el);
        return resultList;
    }

    public static<T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> resultSet = new HashSet<>();
        for(T el: elements)
            resultSet.add(el);
        return resultSet;
    }

    public static<K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        if(keys.size() != values.size())
            throw new IllegalArgumentException();
        HashMap<K,V> resultMap = new HashMap<>();
        for(int i =0; i < keys.size(); i++)
            resultMap.put(keys.get(i), values.get(i));
        return resultMap;
    }
}
