package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class CartProduct extends AppCompatActivity implements Serializable {

    String str1 = "";
    String pName;

    int quantity;

    double price;

    public CartProduct() {

    }

    public CartProduct(String pName, int quantity, double price) {
        this.pName = pName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getpName() {
        return pName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return this.pName + " - - " + this.price + " - - " + this.quantity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        Intent it = getIntent();
        ArrayList<CartProduct> cart = (ArrayList<CartProduct>) it.getSerializableExtra("Cart");
        TextView tx = (TextView) findViewById(R.id.textView);

        for(int k= 0;k<cart.size();k++) {
            tx.setText(" Total: " + Global.getS1() + " /-");
            TextView tx1 = (TextView) findViewById(R.id.textView1);
//            str1+=cart.get(k).pName + " \n";
//            tx1.setText(str1);
            tx1.setText(cart.get(k).pName + " ");
        System.out.println(cart + "------------");
        Log.i("Hello", cart + " ");

        }
//        tx.setText(" Total: " + cart.get(0).price + " /-");

//        TextView tx1 = (TextView) findViewById(R.id.textView1);
//        tx1.setText(cart.get(0).pName + " ");
//        System.out.println(cart + "------------");
//        Log.i("Hello", carts + " ");
    }


}
