package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();
    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        currencyCode = currencyCode.toUpperCase();
        for (Map.Entry<String, CurrencyManipulator> pair: map.entrySet()){
            if(pair.getKey().equals(currencyCode))
                return pair.getValue();
        }
        CurrencyManipulator newCM = new CurrencyManipulator(currencyCode);
        map.put(currencyCode,newCM);
        return newCM;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }

}
