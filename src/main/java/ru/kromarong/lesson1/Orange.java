package ru.kromarong.lesson1;

public class Orange extends Fruit implements Comparable<Fruit> {

    public Orange(float weight) {
        super(weight);
    }



    @Override
    public int compareTo(Fruit o) {
        return 0;
    }
}
