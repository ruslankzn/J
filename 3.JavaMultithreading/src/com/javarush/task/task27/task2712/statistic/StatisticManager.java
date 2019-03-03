package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private StatisticStorage statisticStorage = new StatisticStorage();
    private static StatisticManager ourInstance = new StatisticManager();

    private HashSet<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {    }

    public void register(Cook cook){
        cooks.add(cook);
    }

    public HashSet<Cook> getCooks() {
        return cooks;
    }

    public Map<Date, Double> getAdProfit(){
        Map<Date,Double> resultMap = new TreeMap<>(Collections.reverseOrder());
        for(EventDataRow eventDataRow: statisticStorage.get(EventType.SELECTED_VIDEOS)){
            Date date = dateToStringMidnight(eventDataRow.getDate());
            VideoSelectedEventDataRow eventDataRow1 = (VideoSelectedEventDataRow) eventDataRow;
            if(resultMap.containsKey(date)){
                resultMap.put(date, resultMap.get(date) + (0.01d * (double) eventDataRow1.getAmount()));
            }else{
                resultMap.put(date, (0.01d * (double) eventDataRow1.getAmount()));
            }
        }
        return resultMap;
    }

    private Date dateToStringMidnight(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Map<Date, Map<String, Integer>> getCookWorkload() {
        Map<Date, Map<String, Integer>> resultMap = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow event : statisticStorage.get(EventType.COOKED_ORDER)) {
            Date date = dateToStringMidnight(event.getDate());
            CookedOrderEventDataRow eventData = (CookedOrderEventDataRow) event;
            int time = eventData.getTime();
            if (time == 0) continue;
            if (time % 60 == 0) time = time / 60;
            else time = time / 60 + 1;
            if (resultMap.containsKey(date)) {
                Map<String, Integer> cookInfo = resultMap.get(date);
                if (cookInfo.containsKey(eventData.getCookName()))
                    cookInfo.put(eventData.getCookName(), cookInfo.get(eventData.getCookName()) + time);
                else cookInfo.put(eventData.getCookName(), time);
            } else {
                TreeMap<String, Integer> cookInfo = new TreeMap<>();
                cookInfo.put(eventData.getCookName(), time);
                resultMap.put(date, cookInfo);
            }
        }
        return resultMap;
    }

    private class StatisticStorage
    {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for(EventType eventType: EventType.values()){
                storage.put(eventType,new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> get(EventType eventType)
        {
            return storage.get(eventType);
        }

    }





    public void register(EventDataRow data){
        if (data == null) return;
        statisticStorage.put(data);
    }
}
