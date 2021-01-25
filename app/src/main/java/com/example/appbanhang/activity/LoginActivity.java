package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.appbanhang.adapter.ProductAdapter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public ArrayList<User> temp=new  ArrayList<>();
    IPAddress ipAddress = new IPAddress();
    String URL = ipAddress.ip+"/server/getUser.php";
    String URLFull = ipAddress.ip+"/server/getFullUser.php";
    Button   mButton;
    EditText mEditUser, mEditPass;
    String userName,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButton = findViewById(R.id.button5);
        mEditUser   = findViewById(R.id.editTextTextPersonName3);
        mEditPass   = findViewById(R.id.editTextTextPersonName4);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        userName = mEditUser.getText().toString();
                        password = mEditPass.getText().toString();
                        //Log.v("EditText", userName +"   "+password);
                        GetUser();

                    }
                });

    }
    public void GetUser()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonarray = jsonobject.getJSONArray("KhachHang");
                    if(jsonarray.length()>0)
                    {
                        MainActivity.userName= userName;
                        JSONObject jsonObject = jsonarray.getJSONObject(0);
                        MainActivity.maGH= jsonObject.getString("maGh");

                    }
                    else Toast.makeText(LoginActivity.this,"User is wrong" , Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Something went wrong",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String, String>();
                parameters.put("TenDangNhap", mEditUser.getText().toString());
                parameters.put("MatKhau", mEditPass.getText().toString());
                return parameters;
            }
        };

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URLFull, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonarray = jsonobject.getJSONArray("KhachHang");
                    if(jsonarray.length()>0)
                    {
                        MainActivity.userName= userName;
                        JSONObject jsonObject = jsonarray.getJSONObject(0);
                        MainActivity.name= jsonObject.getString("Hoten");
                        MainActivity.address= jsonObject.getString("diachi");
                        MainActivity.sdt= jsonObject.getString("sdt");
                        Toast.makeText(LoginActivity.this,"Hello "+MainActivity.userName , Toast.LENGTH_LONG).show();
                        Intent AccessoriesIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(AccessoriesIntent);
                    }
                    else Toast.makeText(LoginActivity.this,"User is wrong" , Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Something went wrong",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String, String>();
                parameters.put("TenDangNhap", mEditUser.getText().toString());
                parameters.put("MatKhau", mEditPass.getText().toString());
                return parameters;
            }
        };
        requestQueue.add(stringRequest);
        requestQueue.add(stringRequest1);
    }
}