package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;

public class TabletActivity extends AppCompatActivity {

    public String name, username, address, phone;
    TextView textName, textUserName, textAddress, textPhone;
    ImageView image;
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
        Glide.with(this).load("https://image.freepik.com/free-vector/draw-cute-cat-with-sunglasses_43119-283.jpg").into(image);
    }
}