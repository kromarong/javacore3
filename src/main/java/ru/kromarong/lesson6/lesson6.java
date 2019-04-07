package ru.kromarong.lesson6;

import java.util.Arrays;

public class lesson6 {

    public int[] find4(int[] arr){

        for (int i = arr.length - 1; i >= 0; i--){
            if (arr[i] == 4){
                return Arrays.copyOfRange(arr,i + 1, arr.length);
                }
            }
        throw new RuntimeException();
    }

    public boolean check (int[] arr){
        boolean result = true;
        int tmp = arr[0];
        boolean diff = false;
        for(int i = 0; i < arr.length; i++){
            if (tmp != arr[i]){
                diff = true;
            }
            if (arr[i] != 4 && arr[i] != 1){
                result = false;
                break;
            }
        }
        return result && diff;
    }






}
