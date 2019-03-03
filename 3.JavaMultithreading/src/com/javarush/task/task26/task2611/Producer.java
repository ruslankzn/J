package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
public class Producer implements Runnable{
    private final AtomicInteger counter = new AtomicInteger();
    private ConcurrentHashMap<String, String> map;
    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public void run() {
        int i ;
        Thread currentThread = Thread.currentThread();
        try {
            while(!currentThread.isInterrupted()){
                i = counter.incrementAndGet();
                map.put(String.valueOf(i), String.format("Some text for %d", i));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(currentThread.getName() + " thread was terminated");
        }
    }
}
