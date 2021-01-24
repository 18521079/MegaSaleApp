package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    Button mButton;
    IPAddress ipAddress = new IPAddress();
    String URL = ipAddress.ip+"/server/test1.php";
    EditText mEditUser, mEditPass, mEditName, mEditAddress, mEditSDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mButton = findViewById(R.id.button4);
        mEditUser   = findViewById(R.id.editTextUser);
        mEditPass   = findViewById(R.id.editTextPass);
        mEditName   = findViewById(R.id.editTextName);
        //mEditAddress   = findViewById(R.id.editTextTextPersonName4);
        //mEditSDT   = findViewById(R.id.editTextTextPersonName4);
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        SignUp();
                    }
                });
    }

    public void SignUp()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse)
                    {

                        Toast.makeText(SignUpActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                        Intent AccessoriesIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(AccessoriesIntent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(SignUpActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("username", mEditUser.getText().toString());
                params.put("password", mEditPass.getText().toString());
                params.put("name", mEditName.getText().toString());
                params.put("diachi", "a");
                params.put("sdt", "a");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
        requestQueue.add(stringRequest);
    }
}