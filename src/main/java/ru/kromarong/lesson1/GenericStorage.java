package ru.kromarong.lesson1;

public interface GenericStorage<E extends Comparable> {

    void add(E value);

    void remove(E value, int index);

    E get(int index);

    //boolean compaire(E value);

}
