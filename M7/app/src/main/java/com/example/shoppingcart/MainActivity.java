package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://msitmp.herokuapp.com/getproducts/20186039";

    //listview object
    ListView listView;

    int sum = 0;
    //the hero list where we will store all the hero objects after parsing json
    List<Hero> heroList;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing listview and hero list
        listView = (ListView) findViewById(R.id.listView);
        heroList = new ArrayList<>();
//        Toast.makeText(MainActivity.this, "Welcome " , Toast.LENGTH_SHORT).show();
        //this method will fetch and parse the data
//        b = (Button) findViewById(R.id.button);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(MainActivity.this, "Hai " , Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, Hero.class);
//                startActivity(intent);
////                System.out.println("number " + no + "Name" + nm + "Welcome");
//            }
//        });
        loadHeroList();
        ListView lv = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "Hai " , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Hero.class);

                intent.putExtra("Product", (Serializable) heroList.get(i));
                startActivity(intent);

            }
            });



        Button button3 = findViewById(R.id.button3);


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(Hero.this, "items in cart you have selected "+ productData.getName() , Toast.LENGTH_SHORT).show();

                Intent it = new Intent(MainActivity.this, CartProduct.class);
//                System.out.println(cart);

                Toast.makeText(MainActivity.this, "items price in cart you have selected "+ Global.s1 , Toast.LENGTH_SHORT).show();

                it.putExtra("Cart", (Serializable) Global.cart);
                startActivity(it);
            }
        });


    }



    private void loadHeroList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("ProductCollection");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Hero hero = new Hero(heroObject.getString("ProductId"), heroObject.getString("Category"), heroObject.getString("MainCategory")
                                        , heroObject.getString("SupplierName"), heroObject.getString("WeightUnit"),heroObject.getString("Description")
                                        , heroObject.getString("Name"), heroObject.getString("Status"), Integer.parseInt(heroObject.getString("Quantity")),heroObject.getString("UoM")
                                        , heroObject.getString("CurrencyCode"),heroObject.getString("DimUnit")
                                        ,heroObject.getString("ProductPicUrl")
                                        ,Double.parseDouble(heroObject.getString("WeightMeasure")),Double.parseDouble(heroObject.getString("Price"))
                                        ,Double.parseDouble(heroObject.getString("Width")),Double.parseDouble(heroObject.getString("Depth"))
                                        ,Double.parseDouble(heroObject.getString("Height")));
                                //adding the hero to herolist
                                heroList.add(hero);
                            }

                            //creating custom adapter object
                            ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

}
