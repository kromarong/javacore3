package ru.kromarong.lesson7;

public class TestCase {

    public static int count;


    @BeforeSuit
    public static void prepare(int num){
        count = num;
        System.out.println("Start test");
    }

    @Test(value = 2)
    public static void test02(int num){
        num++;
        count++;
        System.out.println(count + "-й тест пройден 2");
    }

    @Test(value = 3)
    public static void test03(int num){
        num++;
        count++;
        System.out.println(count + "-й тест пройден 3");
    }

    @Test(value = 1)
    public static void test01(int num){
        num++;
        count++;
        System.out.println(count + "-й тест пройден 1");
    }

    @Test(value = 5)
    public static void test05(int num){
        num++;
        count++;
        System.out.println(count + "-й тест пройден 5");
    }

    @Test(value = 4)
    public static void test04(int num){
        num++;
        count++;
        System.out.println(count + "-й тест пройден 4");
    }

    @AfterSuite
    public static void endingTest(int num){
        count = num;
        System.out.println("End test");
    }
}
