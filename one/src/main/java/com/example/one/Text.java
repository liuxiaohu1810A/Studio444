package com.example.one;

public class Text {
     public static void main(String[] args){
         sing();
     }

    private static void sing() {
        Singleton1 singletin1 = Singleton1.getSingletin1();
        Singleton2 singleton2 = Singleton2.getSingleton2();
    }
}
