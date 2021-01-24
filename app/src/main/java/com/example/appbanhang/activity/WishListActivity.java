package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.CartAdapter;
import com.example.appbanhang.adapter.WishListAdapter;
import com.example.appbanhang.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WishListActivity extends AppCompatActivity {
    GridView gdvListProduct;
    WishListAdapter adapter;
    ArrayList<Product> ProductArrayList= new ArrayList<>();
    Toolbar toolbar;
    IPAddress ipAddress = new IPAddress();
    String URL = ipAddress.ip+"/server/getFavoriteList.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        anhXa();
        Init();
        actionToolBar();
        SetUp();
    }

    private void SetUp() {
        //gdvListProduct.setAdapter(adapter);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Init() {
        /*ProductArrayList= new ArrayList<>();
        ProductArrayList.add(new Product("Apple Macbook Air","20000000Đ","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlWfGxqnpcM7ereO7P25A_F2QXxpcxmsc-w&usqp=CAU"));
        ProductArrayList.add(new Product("Apple Macbook Air","20000000Đ","https://mobigo.vn/images/seoworld/vivo/part-6/image2.jpg"));
        ProductArrayList.add(new Product("Apple Macbook Air","40000000Đ","https://media.sohuutritue.net.vn/files/huongmi/2018/02/02/samsung-galaxy-s8-plus-1107.png"));
        ProductArrayList.add(new Product("Apple Macbook Air","20000000Đ","https://mobigo.vn/images/seoworld/vivo/part-6/image2.jpg"));

        adapter = new WishListAdapter(this, 0,ProductArrayList);*/
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>()
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
                        adapter = new WishListAdapter(WishListActivity.this, 0,ProductArrayList);
                        adapter.notifyDataSetChanged();
                        gdvListProduct.setAdapter(adapter);
                    }
                    else
                    {
                        Toast.makeText(WishListActivity.this, "You don't have any product in your cart",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WishListActivity.this, "Please login",Toast.LENGTH_LONG).show();
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
    }

    private void anhXa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        gdvListProduct=findViewById(R.id.gdvCartListprouct);
    }
}