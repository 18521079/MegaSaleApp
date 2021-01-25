package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.CartAdapter;
import com.example.appbanhang.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btLike, btAddToCart;
    IPAddress ipAddress = new IPAddress();
    String URL = ipAddress.ip+"/server/insertProduct.php";
    String URLFavo = ipAddress.ip+"/server/insertFavorite.php";
    String name,id,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product2);
        anhXa();
        actionToolBar();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id= intent.getStringExtra("masp");
        String price = intent.getStringExtra("price");
        price= price+" $";
        String image = intent.getStringExtra("image");
        TextView nameText = findViewById(R.id.nameOfProduct);
        TextView priceText = findViewById(R.id.priceOfProduct);
        ImageView imgProduct = findViewById(R.id.imageOfProduct);
        nameText.setText(name);
        priceText.setText(price);
        EditText t= findViewById(R.id.textNumber);
        number=t.getText().toString();
        Glide.with(this).load(image).into(imgProduct);
        btAddToCart= findViewById(R.id.btAddCart);
        btAddToCart.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        addToCart();
                    }
                });

        btLike= findViewById(R.id.btFavorite);
        btLike.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        addToFavorite();
                    }
                });
        findViewById(R.id.btIncrease).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText t= findViewById(R.id.textNumber);
                        int num=Integer.decode(t.getText().toString()) +1;
                        t.setText(String.valueOf(num));
                        number=String.valueOf(num);

                    }
                });
        findViewById(R.id.btDecrease).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText t= findViewById(R.id.textNumber);
                        int num=Integer.decode(t.getText().toString());
                        if(num >=1)
                            num--;
                        t.setText(String.valueOf(num));
                        number=String.valueOf(num);

                    }
                });


    }
    private  void addToCart()
    {
        if(MainActivity.maGH!=null &&MainActivity.userName!=null  )
        {
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    Toast.makeText(DetailProductActivity.this,name + " have add to your cart", Toast.LENGTH_LONG).show();
                    Intent cartIntent = new Intent(DetailProductActivity.this, CartActivity.class);
                    startActivity( cartIntent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DetailProductActivity.this, "Please login",Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parameters = new HashMap<>();
                    parameters.put("MaSP", id);
                    parameters.put("MaGH", MainActivity.maGH);
                    parameters.put("SoLuong", number);
                    return parameters;
                }
            };

            requestQueue.add(stringRequest);
        }
        else
        {
            Toast.makeText(DetailProductActivity.this,"Please Login", Toast.LENGTH_LONG).show();
            Intent loginIntent = new Intent(DetailProductActivity.this, LoginActivity.class);
            startActivity( loginIntent);
        }

    }
    private  void addToFavorite()
    {
        if(MainActivity.userName!=null  )
        {
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLFavo, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    Toast.makeText(DetailProductActivity.this,name + " have add to your favorite list", Toast.LENGTH_LONG).show();
                    //Intent cartIntent = new Intent(DetailProductActivity.this, WishListActivity.class);
                    //startActivity( cartIntent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DetailProductActivity.this, "Please login",Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parameters = new HashMap<>();
                    parameters.put("username", MainActivity.userName);
                    parameters.put("masp", id);
                    return parameters;
                }
            };

            requestQueue.add(stringRequest);
        }
        else
        {
            Toast.makeText(DetailProductActivity.this,"Please Login", Toast.LENGTH_LONG).show();
            Intent loginIntent = new Intent(DetailProductActivity.this, LoginActivity.class);
            startActivity( loginIntent);
        }

    }
    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void anhXa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
    }

}