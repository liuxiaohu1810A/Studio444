package com.example.one;

public class Singleton2 {
    private static Singleton2 sing;
    private Singleton2 singleton2;
    private Singleton2(){};
    public static synchronized Singleton2 getSingleton2(){
        if (sing==null){
           sing = new Singleton2();
        }
        return sing;
    }

}
