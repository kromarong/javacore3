package ru.kromarong.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Testing {

    public static Map<Float,Method> testMethod;
    public static Method before;
    public static Method after;


    public static void main(String[] args) {
        Class clazz = new TestCase().getClass();
        start(clazz);
    }


    public static void start(Class testClass){
        testMethod = new TreeMap<>();
        int countBefore = 0;
        int countAfter = 0;

        for (Method method : testClass.getDeclaredMethods()){
            Test test = method.getAnnotation(Test.class);
            BeforeSuit beforeSuit = method.getAnnotation(BeforeSuit.class);
            AfterSuite afterSuite = method.getAnnotation(AfterSuite.class);

            if (test!=null){
                testMethod.put(test.value(),method);
            }else if (beforeSuit!=null){
                before = method;
                countBefore++;
            }else if (afterSuite!=null){
                after = method;
                countAfter++;
            }
        }

        if ((countAfter != 1)||(countBefore != 1)){
            throw new RuntimeException();
        }

        try {
            before.invoke(testClass,0);
            for (Method method : testMethod.values()){
                method.invoke(testClass, 1);
            }
            after.invoke(testClass,0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
