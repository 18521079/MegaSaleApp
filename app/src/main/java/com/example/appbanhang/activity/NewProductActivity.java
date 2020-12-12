package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;

import com.example.appbanhang.R;
import com.example.appbanhang.adapter.ProductAdapter;
import com.example.appbanhang.model.Product;

import java.util.ArrayList;

public class NewProductActivity extends AppCompatActivity {

    GridView gdvListProduct;
    ProductAdapter adapter;
    ArrayList<Product> ProductArrayList;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        anhXa();
        Init();
        actionToolBar();
        SetUp();

    }

    private void Init() {

        ProductArrayList= new ArrayList<>();
        ProductArrayList.add(new Product("Apple Macbook Air","40000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Apple Macbook Air","20000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Apple Macbook Air","40000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://bloganchoi.com/wp-content/uploads/2020/09/dell-xps-13-9300.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        ProductArrayList.add(new Product("Galaxy S","20000000Đ","https://cnet3.cbsistatic.com/img/KEM_0EsoAP-9kOds2Fbal9Ww540=/1200x675/2017/08/14/ec0fa893-faf2-46c3-8933-6898773804ba/apple-macbook-air-2017-05.jpg"));
        adapter = new ProductAdapter(this, 0,ProductArrayList);
    }

    private void SetUp() {
        gdvListProduct.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void anhXa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        gdvListProduct=findViewById(R.id.gdvListprouct);
    }
}