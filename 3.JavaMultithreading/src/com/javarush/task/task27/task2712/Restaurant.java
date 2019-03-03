package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final static int ORDER_CREATING_INTERVAL = 100;
   private final static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Cook cook = new Cook("Amigo");
        cook.setQueue(orderQueue);
        Cook cook1 = new Cook("Amigo1");
        cook1.setQueue(orderQueue);

        Waiter waiter = new Waiter();

        cook.addObserver(waiter);
        cook1.addObserver(waiter);

        StatisticManager.getInstance().register(cook);
        StatisticManager.getInstance().register(cook1);


        List<Tablet> tabletsList = new ArrayList<>();
        for(int i = 0; i < 5; i ++){
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tabletsList.add(tablet);
        }

        Thread randomGenerationOrderTask = new Thread(new RandomOrderGeneratorTask(tabletsList, ORDER_CREATING_INTERVAL));
        Thread thread = new Thread(randomGenerationOrderTask);
        Thread cookThread1 = new Thread(cook);
        Thread cookThread2 = new Thread(cook1);
        thread.start();
        cookThread1.start();
        cookThread2.start();

        randomGenerationOrderTask.start();
        Thread.sleep(1000);
        thread.interrupt();
        cookThread1.interrupt();
        cookThread2.interrupt();
        randomGenerationOrderTask.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
