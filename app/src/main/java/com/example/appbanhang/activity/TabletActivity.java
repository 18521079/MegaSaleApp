package com.example.appbanhang.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;

import java.util.Objects;

public class TabletActivity extends AppCompatActivity {

    public String name, username, address, phone;
    Toolbar toolbar;

    TextView textName, textUserName, textAddress, textPhone;
    ImageView image;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet);
        name = MainActivity.name;
        username= MainActivity.userName;
        address= MainActivity.address;
        phone= MainActivity.sdt;
        textName= findViewById(R.id.TextName);
        textUserName= findViewById(R.id.TextUser);
        textAddress= findViewById(R.id.TextAddress);
        textPhone= findViewById(R.id.TextPhone);
        image= findViewById(R.id.userImage);
        textName.setText("Full Name:    "+name);
        textUserName.setText("User Name:    "+username);
        textAddress.setText("Address:    "+address);
        textPhone.setText("Phone:    "+phone);
        actionToolBar();
        anhxa();
        Glide.with(this).load("https://image.freepik.com/free-vector/draw-cute-cat-with-sunglasses_43119-283.jpg").into(image);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void anhxa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);

    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);

    }
}