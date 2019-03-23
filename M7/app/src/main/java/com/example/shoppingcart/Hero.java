package com.example.shoppingcart;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Hero extends AppCompatActivity implements Serializable {
    String ProductId,Category,MainCategory,SupplierName,WeightUnit,Description;
    double WeightMeasure,Price,Width,Depth,Height;
    int Quantity;
    String Name,ProductPicUrl,UoM,CurrencyCode,DimUnit,Status;

//    ArrayList<CartProduct>  cart;
    int c=0;
    int r = 0;
    ListView listView1;

    public  Hero() {
//        cart = new ArrayList<CartProduct>();
//        Log.i("Cart created", cart + " ");
    }


    public Hero(String ProductId, String Category, String MainCategory, String SupplierName,String WeightUnit, String Description,
                String Name, String Status, int Quantity ,String UoM, String CurrencyCode, String DimUnit, String ProductPicUrl,double WeightMeasure,double Price,double Width
            ,double Depth,double Height) {
        this.ProductId = ProductId;
        this.Category = Category;
        this.MainCategory = MainCategory;
        this.SupplierName = SupplierName;
        this.WeightUnit = WeightUnit;
        this.Quantity = Quantity;
        this.Description = Description;
        this.Name = Name;
        this.Status = Status;
        this.UoM = UoM;
        this.CurrencyCode = CurrencyCode;
        this.DimUnit = DimUnit;
        this.ProductPicUrl = ProductPicUrl;
        this.WeightMeasure = WeightMeasure;
        this.Price = Price;
        this.Width = Width;
        this.Depth = Depth;
        this.Height = Height;
    }

    public double getPrice() {
        return Price;
    }

    public double getDepth() {
        return Depth;
    }

    public double getHeight() {
        return Height;
    }

    public double getWeightMeasure() {
        return WeightMeasure;
    }

    public double getWidth() {
        return Width;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getCategory() {
        return Category;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public String getDescription() {
        return Description;
    }

    public String getMainCategory() {
        return MainCategory;
    }

    public String getName() {
        return Name;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getProductPicUrl() {
        return ProductPicUrl;
    }
    //
    public String getSupplierName() {
        return SupplierName;
    }

    public String getDimUnit() {
        return DimUnit;
    }

    public String getStatus() {
        return Status;
    }

    public String getUoM() {
        return UoM;
    }
    //
    public String getWeightUnit() {
        return WeightUnit;
    }
    public  String toString() {
        return  this.Name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_data);
        Intent intent = getIntent();
        final Hero productData = (Hero) intent.getSerializableExtra("Product");

        intent.putExtra("data", productData);
        System.out.println(productData);


        TextView pid = (TextView) findViewById(R.id.pid);
        pid.setText(productData.getProductId());

        TextView category = (TextView) findViewById(R.id.category);
        category.setText(productData.getCategory());

        TextView maincategory = (TextView) findViewById(R.id.mcategory);
        maincategory.setText(productData.getMainCategory());


        TextView name = (TextView) findViewById(R.id.name);
        name.setText(productData.getName());

        TextView sname = (TextView) findViewById(R.id.sname);
        sname.setText(productData.getSupplierName());

/*
        TextView wmeasure = (TextView) findViewById(R.id.wmeasure);
        wmeasure.setText(productData.getWeightMeasure() + " ");
*/

        TextView wunit = (TextView) findViewById(R.id.wunit);
        wunit.setText(productData.getWeightUnit());

        TextView des = (TextView) findViewById(R.id.descrition);
        des.setText(productData.getDescription());

        ImageView pic = (ImageView) findViewById(R.id.productpic);
        String current = "http://msitmp.herokuapp.com" + productData.getProductPicUrl();
        new DownloadImageTask(pic).execute(current);


        TextView status = (TextView) findViewById(R.id.status);
        status.setText(productData.getStatus());

        TextView qty = (TextView) findViewById(R.id.quantity);
        qty.setText(productData.getQuantity() + " ");

        TextView uom = (TextView) findViewById(R.id.uom);
        uom.setText(productData.getUoM());

        TextView currencymode = (TextView) findViewById(R.id.currencycode);
        currencymode.setText(productData.getCurrencyCode());

        TextView price = (TextView) findViewById(R.id.price);
        price.setText(productData.getPrice() + " ");

        TextView width = (TextView) findViewById(R.id.width);
        width.setText(productData.getWidth() + " ");

        TextView height = (TextView) findViewById(R.id.height);
        height.setText(productData.getHeight() + " ");

        TextView depth = (TextView) findViewById(R.id.depth);
        depth.setText(productData.getDepth() + " ");

        TextView dim = (TextView) findViewById(R.id.dimUnit);
        dim.setText(productData.getDimUnit() + " ");

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
//        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Hero.this, "Added  " + productData.getQuantity() , Toast.LENGTH_SHORT).show();

                if (c < productData.getQuantity()) {
                    c++;
                    Toast.makeText(Hero.this, "Added  " + productData.getName() + "\n count" + c, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < Global.cart.size(); i++) {
                        if (productData.getName().equals(Global.cart.get(i).pName)) {

                            Global.cart.get(i).setPrice(c * productData.getPrice());
                            Global.s1 += c * productData.getPrice();
                        }
                    }
                    Global.cart.add(new CartProduct(productData.getName(), c, c * productData.getPrice()));
                    Global.s1 += c * productData.getPrice();
                }
                if (c == productData.getQuantity()) {
                    Toast.makeText(Hero.this, "Exceeded count  " + productData.getName() + "\n count" + c, Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(Hero.this, "CART "+ cart, Toast.LENGTH_SHORT).show();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.cart.size() >= 1) {
                    Global.cart.remove(productData);
                    r++;
                    c--;

                    Toast.makeText(Hero.this, "removed from cart " + productData.getName() + "\n removed" + r, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(Hero.this, "no item to remove ", Toast.LENGTH_SHORT).show();

                }
            }
        });
//        System.out.println(cart);

    }

//
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Toast.makeText(Hero.this, "items in cart you have selected "+ productData.getName() , Toast.LENGTH_SHORT).show();
//
//                Intent it = new Intent(Hero.this, CartProduct.class);
//                System.out.println(cart);
//                it.putExtra("Cart", (Serializable) cart);
//                startActivity(it);
//            }
//        });
//
//        }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmp;
    }
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}