package com.example.appbanhang.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;

import com.example.appbanhang.adapter.CartAdapter;
//import com.example.appbanhang.Data.JavaMailAPI;
//import com.example.appbanhang.Data.Utils;

import com.example.appbanhang.adapter.ProductAdapter;
import com.example.appbanhang.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    GridView gdvListProduct;
    CartAdapter adapter;
    ArrayList<Product> ProductArrayList=new ArrayList<>();
    Toolbar toolbar;
    ImageButton btnDelete;
    IPAddress ipAddress = new IPAddress();
    String URLProduct = ipAddress.ip+"/server/getProductOfCart.php";
    String URLCart = ipAddress.ip+"/server/getCart.php";
    String URLDelProduct = ipAddress.ip+"/server/deleteProductOfCart.php";
    TextView price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        price= findViewById(R.id.Cart_price);
        anhXa();
        Init();
        actionToolBar();
        SetUp();
        findViewById(R.id.buttonOrder).setOnClickListener(
                new View.OnClickListener()
                {
                    @SuppressLint("ShowToast")
                    public void onClick(View view)
                    {
                        deleteProductInCart();
                    }
                });

    }


    private void deleteProductInCart()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDelProduct, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(CartActivity.this,"Your Order is send to Shop, thanks you so much",Toast.LENGTH_LONG).show();
                Intent main= new Intent(CartActivity.this, MainActivity.class);
                startActivity(main);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Please login",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<>();
                parameters.put("magh", MainActivity.maGH);
                return parameters;
            }
        };

        requestQueue.add(stringRequest);
    }
    private void CatchOnItemGirdProduct() {
        gdvListProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id)
            {
                //Intent intent = getIntent();
                //Toast.makeText(CartActivity.this,"press",Toast.LENGTH_LONG);
                //finish();

                //startActivity(intent);
            }
        });
    }

    private void SetUp() {

    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Init()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLProduct, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonarray = jsonobject.getJSONArray("CTGH");
                    if(jsonarray.length()>0)
                    {

                        for(int i=0 ; i < jsonarray.length(); i++)
                        {
                            JSONObject jsonObject = jsonarray.getJSONObject(i);
                            String Product_Name= jsonObject.getString("tensp");
                            String Product_Price= jsonObject.getString("price");
                            String Product_Image=jsonObject.getString("image");
                            MainActivity.maGH= jsonObject.getString("maGH");
                            String id= jsonObject.getString("masp");
                            Product t=new Product(id,Product_Name, Product_Price, Product_Image);
                            t.soluong=jsonObject.getString("soluong");
                            ProductArrayList.add(t);
                        }
                        adapter = new CartAdapter(CartActivity.this, 0,ProductArrayList);
                        adapter.notifyDataSetChanged();
                        gdvListProduct.setAdapter(adapter);
                        int total=0;
                        for(int i=0 ; i <ProductArrayList.size(); i++ )
                        {
                            total +=Integer.parseInt(ProductArrayList.get(i).price)  *Integer.parseInt(ProductArrayList.get(i).soluong) ;
                        }
                        price.setText(String.valueOf(total)+" $");
                       // Toast.makeText(CartActivity.this, "magh " +MainActivity.maGH,Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(CartActivity.this, "You don't have any product in your cart",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CartActivity.this, "Please login",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<>();
                parameters.put("TenDangNhap", MainActivity.userName);
                return parameters;
            }
        };
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URLCart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonarray = jsonobject.getJSONArray("GioHang");
                    if(jsonarray.length()>0)
                    {
                        JSONObject jsonObject = jsonarray.getJSONObject(0);
                        price.setText(jsonObject.getString("tongtien"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CartActivity.this, "Something went wrong",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<>();
                parameters.put("TenDangNhap", MainActivity.userName);
                return parameters;
            }
        };
        requestQueue.add(stringRequest);


        //requestQueue.add(stringRequest1);
    }

    private void anhXa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        gdvListProduct=findViewById(R.id.gdvCartListprouct);
    }
}