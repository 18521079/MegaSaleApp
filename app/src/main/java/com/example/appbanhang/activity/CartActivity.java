package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.appbanhang.R;

import com.example.appbanhang.adapter.CartAdapter;


import com.example.appbanhang.adapter.ProductAdapter;
import com.example.appbanhang.model.Product;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    GridView gdvListProduct;
    CartAdapter adapter;
    ArrayList<Product> ProductArrayList;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        anhXa();
        Init();
        actionToolBar();
        SetUp();
        CatchOnItemGirdProduct();
        CatchOnItemGirdProduct2();
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
        gdvListProduct.setAdapter(adapter);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Init() {
        ProductArrayList= new ArrayList<>();
        ProductArrayList.add(new Product("Apple Macbook Air","20000000Đ","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlWfGxqnpcM7ereO7P25A_F2QXxpcxmsc-w&usqp=CAU"));
        ProductArrayList.add(new Product("Apple Macbook Air","20000000Đ","https://mobigo.vn/images/seoworld/vivo/part-6/image2.jpg"));
        ProductArrayList.add(new Product("Apple Macbook Air","40000000Đ","https://media.sohuutritue.net.vn/files/huongmi/2018/02/02/samsung-galaxy-s8-plus-1107.png"));
        ProductArrayList.add(new Product("Apple Macbook Air","20000000Đ","https://mobigo.vn/images/seoworld/vivo/part-6/image2.jpg"));

        adapter = new CartAdapter(this, 0,ProductArrayList);

    }

    private void anhXa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        gdvListProduct=findViewById(R.id.gdvCartListprouct);
    }
}