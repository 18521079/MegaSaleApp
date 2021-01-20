package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.ProductAdapter;
import com.example.appbanhang.model.Product;
import com.example.appbanhang.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public ArrayList<User> temp=new  ArrayList<>();
    String URLGetUser = "http://192.168.1.11:8080/server/getUser.php";
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
                        Log.v("EditText", userName +"   "+password);
                        GetUser();
                        if(userName == temp.get(0).UserName)
                        {
                            Intent AccessoriesIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(AccessoriesIntent);
                        }
                    }
                });

    }
    public void GetUser()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URLGetUser, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONArray jsonArray= response.getJSONArray("SP");
                            //Log.e("JSON", "ARRAY have : " + String.valueOf(jsonArray.length()));
                            for(int i=0 ; i < jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String User_username= jsonObject.getString("username");
                                String User_password= jsonObject.getString("password");
                                String User_name=jsonObject.getString("name");
                                String User_diachi=jsonObject.getString("diachi");
                                String User_sdt=jsonObject.getString("sdt");
                                temp.add(new User(User_username, User_password, User_name,User_diachi,User_sdt));

                            }
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
        Log.e("JSON", "TEMP have : " + String.valueOf(temp.size()));
    }
}