package ru.kromarong.lesson1;

import java.util.ArrayList;
import java.util.List;

public class DataStorage<E extends Object> {
    private E[] data;
    private int leftIndex;
    private int rightIndex;

    public DataStorage(E[] data){
        this.data = data;
    }

    public void switchElement(int leftIndex, int rightIndex){
        E buf;
        buf = data[leftIndex];
        data[leftIndex] = data[rightIndex];
        data[rightIndex] = buf;
    }

    public List<E> turnToAL(){
        List<E> newAL = new ArrayList<E>();
        for (E value : data){
            newAL.add(value);
        }
        return newAL;
    }



    public E[] getData(){
        return (E[]) data;
    }

    public static void main(String[] args) {
        Integer[] testArr = {1, 2, 3, 4, 5};
        DataStorage<Integer> intArr = new DataStorage<>(testArr);
        intArr.switchElement(2,4);
        Integer arr1[] = intArr.getData();
        for (int i = 0; i< arr1.length; i++){
            System.out.println(arr1[i]);
        }

        String[] arr2 = {"1", "r", "65", "q", "9"};
        DataStorage<String> strArr = new DataStorage<>(arr2);
        strArr.switchElement(1,2);
        String[] arr3 = strArr.getData();
        for (int i = 0; i< arr3.length; i++){
            System.out.println(arr3[i]);
        }

        List<String> newAL = new ArrayList<>(strArr.turnToAL());
        System.out.println(newAL);

    }


}
