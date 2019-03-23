package ru.kromarong.lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CyclicBarrier start = new CyclicBarrier(CARS_COUNT);
        CountDownLatch finish = new CountDownLatch(CARS_COUNT);
        CountDownLatch startMessage = new CountDownLatch(CARS_COUNT);
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        ExecutorService raceService = Executors.newFixedThreadPool(CARS_COUNT);

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), start, finish, startMessage);
            raceService.submit(cars[i]);
        }

        try{
            startMessage.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try{
            finish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        raceService.shutdown();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public static int getCarsCount() {
        return CARS_COUNT;
    }
}
