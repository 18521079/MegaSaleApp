package com.example.appbanhang.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.CategoryAdapter;
import com.example.appbanhang.adapter.ItemNavigationAdapter;
import com.example.appbanhang.adapter.ProductAdapter;
import com.example.appbanhang.model.Category;
import com.example.appbanhang.model.ItemNavigation;
import com.example.appbanhang.model.Product;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ArrayList<Category> mangloaisp;
    CategoryAdapter loaispAdapter;
    GridView gdvListProduct;
    ProductAdapter adapter;
    ArrayList<Product> ProductArrayList;

    ArrayList<ItemNavigation> arrayList;
    ItemNavigationAdapter naAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        actionToolBar();
        actionViewLipper();
        actionMenu();
        getDuLieuLoaisp();
        Init();
        SetUp();
        SetClick();

    }

    private void actionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemNavigation( "Trang chủ" , R.drawable.icon_test));
        arrayList.add(new ItemNavigation( "Cài đặt" , R.drawable.icon_test));
        arrayList.add(new ItemNavigation( "aaaaaa" , R.drawable.icon_test));
        arrayList.add(new ItemNavigation( "aaaaaa" , R.drawable.icon_test));
        arrayList.add(new ItemNavigation( "aaaaaa" , R.drawable.icon_test));
        arrayList.add(new ItemNavigation( "aaaaaa" , R.drawable.icon_test));
        naAdapter= new ItemNavigationAdapter(this, R.layout.item_navigation, arrayList);
        listView.setAdapter(naAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private void Init()
    {
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
    private void SetUp()
    {
        gdvListProduct.setAdapter(adapter);
    }
    private void SetClick()
    {

    }
    private void getDuLieuLoaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
      //  JsonArrayRequest jsonArrayRequest = new JsonArrayRequest()
    }

    private void actionViewLipper() {
        ArrayList<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("https://images.vov.vn/Uploaded/ed7piNlL54cRb7FgmUmzw/2017_12_06/quang_ba_SGQG.jpg");
        mangQuangCao.add("https://www.chili.vn/blogs/wp-content/uploads/2018/01/tao-cau-chuyen-thuong-hieu-2-e1516173732533.jpg");
        mangQuangCao.add("https://wikimarketing.vn/wp-content/uploads/2019/03/david-beckham.jpg");
        mangQuangCao.add("https://genk.mediacdn.vn/2016/hinh1-1476153949150-crop-1476153955413-1476174237771.jpg");
        mangQuangCao.add("https://genk.mediacdn.vn/2016/hinh1-1476153949150-crop-1476153955413-1476174237771.jpg");
        for (int i=0; i<mangQuangCao.size(); i++)
        {
            ImageView image= new ImageView(getApplicationContext());

            Picasso.get().load(mangQuangCao.get(i)).into(image);
            image.setScaleType(image.getScaleType().FIT_XY);
            viewFlipper.addView(image);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }

    private void anhXa() {
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        viewFlipper =(ViewFlipper)findViewById(R.id.viewlipper);
        listView= (ListView) findViewById(R.id.lv_item);
        navigationView= (NavigationView) findViewById(R.id.navigation);
        mangloaisp = new ArrayList<>();
        loaispAdapter= new CategoryAdapter(getApplicationContext(), mangloaisp);
        listView.setAdapter(loaispAdapter);
        gdvListProduct=findViewById(R.id.gdvListprouct);


    }

}