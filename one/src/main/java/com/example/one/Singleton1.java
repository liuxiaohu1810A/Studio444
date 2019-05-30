package com.example.one;

public class Singleton1 {
      private static Singleton1 in=new Singleton1();
      private Singleton1(){};
      public static Singleton1 getSingletin1(){
           return in;
      }
}
