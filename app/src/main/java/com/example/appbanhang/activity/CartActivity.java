package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    TextView price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btnDelete=(ImageButton)findViewById(R.id.buttonRemove);
        price= findViewById(R.id.Cart_price);
        anhXa();
        Init();
        actionToolBar();
        SetUp();
        CatchOnItemGirdProduct();
        CatchOnItemGirdProduct2();

        /*btnDelete.setOnClickListener(  new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        //Toast.makeText(CartActivity.this,"button click", Toast.LENGTH_LONG).show();
                    }
                });*/
    }

    private void CatchOnItemGirdProduct2() {
        gdvListProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent click = new Intent(getApplicationContext(), DetailProductActivity.class);
                startActivity(click);
            }
        });
    }

    private void CatchOnItemGirdProduct() {
        gdvListProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent phoneIntent = new Intent(getApplicationContext(), DetailProductActivity.class);
                startActivity(phoneIntent);
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
                            ProductArrayList.add(new Product(Product_Name, Product_Price, Product_Image));
                        }
                        adapter = new CartAdapter(CartActivity.this, 0,ProductArrayList);
                        adapter.notifyDataSetChanged();
                        gdvListProduct.setAdapter(adapter);
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
        requestQueue.add(stringRequest1);
    }

    private void anhXa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        gdvListProduct=findViewById(R.id.gdvCartListprouct);
    }
}