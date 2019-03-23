package ru.kromarong.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box<E extends Fruit & Comparable<? super E>> implements GenericStorage<E> {
    private ArrayList<E> data;

    public Box(ArrayList<E> data) {
        this.data = data;
    }

    public Box(){
        this.data = new ArrayList<>();
    }

    @Override
    public void add(E value) {
        data.add(value);

    }

    @Override
    public void remove(E value, int index) {
        remove(value, index);

    }

    @Override
    public E get(int index) {
        return data.get(index);
    }

    public List<E> getAll(){
        return data;
    }

    public float getWeight(){
        float weight = 0.0f;
        for (E value : data){
            weight += value.getWeight();
        }
        return weight;
    }

    public boolean compaire(Box<?> another){
        return Math.abs(this.getWeight() - another.getWeight()) < 0.0001;
    }

    public void moveToBox(Box<E> boxFrom){
        for (E value: boxFrom.data) {
            data.add(value);
        }
        boxFrom.data.clear();
    }
}
