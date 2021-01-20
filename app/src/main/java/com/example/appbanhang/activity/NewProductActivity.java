package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.ProductAdapter;
import com.example.appbanhang.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewProductActivity extends AppCompatActivity {

    GridView gdvListProduct;
    ProductAdapter adapter;
    ArrayList<Product> ProductArrayList;
    Toolbar toolbar;
    ArrayList<Product> temp= new ArrayList<>();
    String URL = "http://192.168.1.7:8080/server/getProduct.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        anhXa();
        Init();
        actionToolBar();
        SetUp();

    }

    private void Init() {

        /*ProductArrayList= new ArrayList<>();
        ProductArrayList.add(new Product("Apple Macbook Air","40000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Apple Macbook Air","20000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Apple Macbook Air","40000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        adapter = new ProductAdapter(this, 0,ProductArrayList);*/
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONArray jsonArray= response.getJSONArray("SP");
                            Log.e("JSON", "ARAAY have : " + String.valueOf(jsonArray.length()));
                            for(int i=0 ; i < jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String Product_Name= jsonObject.getString("tensp");
                                String Product_Price= jsonObject.getString("price");
                                String Product_Image=jsonObject.getString("image");
                                temp.add(new Product(Product_Name, Product_Price, Product_Image));
                            }
                            adapter = new ProductAdapter(NewProductActivity.this, 0,temp);
                            adapter.notifyDataSetChanged();
                            gdvListProduct.setAdapter(adapter);
                            Log.e("JSON", "TEMP have : " + String.valueOf(temp.size()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("JSON", "cannot sparse JSONOBJECT");
                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("JSON ERROR", error.toString());

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void SetUp() {
        gdvListProduct.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void anhXa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        gdvListProduct=findViewById(R.id.gdvListprouct);
    }
}