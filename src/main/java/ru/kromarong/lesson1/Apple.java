package ru.kromarong.lesson1;

public class Apple extends Fruit implements Comparable<Fruit> {

    public Apple( float weight) {
        super(weight);
    }

    @Override
    public int compareTo(Fruit o) {
        return 0;
    }
}
