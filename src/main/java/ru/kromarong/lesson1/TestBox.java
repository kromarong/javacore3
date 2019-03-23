package ru.kromarong.lesson1;


public class TestBox {
    public static void main(String[] args) {

        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 5; i++) {
            orangeBox.add(new Orange(1.5f));
        }

        Box<Orange> bigOrangeBox = new Box<>();
        for (int i = 0; i < 10; i++) {
           bigOrangeBox.add(new Orange(1.5f));
        }

        Box<Apple> appleBox = new Box<>();
        for (int i = 0; i < 5; i++) {
            appleBox.add(new Apple(1.0f));
        }

        Box<Apple> bigAppleBox = new Box<>();
        for (int i = 0; i < 15; i++) {
            bigAppleBox.add(new Apple(1.0f));
        }

        System.out.println(bigAppleBox.compaire(appleBox));
        System.out.println(bigAppleBox.compaire(orangeBox));
        System.out.println(bigAppleBox.compaire(bigOrangeBox));

        bigOrangeBox.moveToBox(orangeBox);

        System.out.println(bigAppleBox.compaire(bigOrangeBox));
    }
}
