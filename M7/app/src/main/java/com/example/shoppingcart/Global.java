package com.example.shoppingcart;

import java.util.ArrayList;

public class Global{

    static ArrayList<CartProduct>  cart=new ArrayList<>() ;
    static double s1=0;

    public static  ArrayList<CartProduct> getCart() {
        return cart;
    }

//    public static void setCart(ArrayList<CartProduct> cart) {
//
//    }

    public static double getS1() {
        return s1;
    }
//
//    public void setS1(double s1) {
////        this.s1 = s1;
//    }
}
