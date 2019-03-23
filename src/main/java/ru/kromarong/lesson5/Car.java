package ru.kromarong.lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cb;
    private CountDownLatch cdl;
    private CountDownLatch start;

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdl, CountDownLatch start) {
        this.race = race;
        this.speed = speed;
        this.cb = cb;
        this.cdl = cdl;
        this.start = start;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            start.countDown();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (cdl.getCount() == MainClass.getCarsCount()){
            System.out.println("Победил " + this.name);
        }
        cdl.countDown();
    }
}

