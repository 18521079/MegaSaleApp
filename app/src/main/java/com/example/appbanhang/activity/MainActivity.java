package com.example.appbanhang.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
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
    GridView gdvTrendProduct;
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
        CatchOnItemListView();
        CatchOnItemGirdProduct();


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

    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
            switch (i)
            {
                case 0:
                    Intent phoneIntent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity( phoneIntent);
                    break;
                case 1:
                    Intent NewProductIntent = new Intent(MainActivity.this, NewProductActivity.class);
                    startActivity(NewProductIntent);
                    break;
                case 2:
                    Intent TabletIntent = new Intent(MainActivity.this, TabletActivity.class);
                    startActivity(TabletIntent);
                    break;
                case 3:
                    Intent AccessoriesIntent = new Intent(MainActivity.this, AccessoriesActivity.class);
                    startActivity(AccessoriesIntent);
                    break;
                case 4:
                    Intent WatchIntent = new Intent(MainActivity.this, WatchActivity.class);
                    startActivity(WatchIntent);
                    break;


            }
            }
        });
    }

    private void actionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemNavigation( "Home" , R.drawable.icon_home));
        arrayList.add(new ItemNavigation( "New Product" , R.drawable.icon_new_product));
        arrayList.add(new ItemNavigation( "My Account" , R.drawable.icon_account));
        arrayList.add(new ItemNavigation( "Wish List" , R.drawable.icon_heart));
        arrayList.add(new ItemNavigation( "Category" , R.drawable.icon_category));
        naAdapter= new ItemNavigationAdapter(this, R.layout.item_navigation, arrayList);
        listView.setAdapter(naAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem= menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.SortProduct(s);
                adapter.getFilter().filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.app_cart)
        {
            Intent AccessoriesIntent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(AccessoriesIntent);
        }
        else if(id==R.id.app_Login)
        {
            Intent login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(login);
        }
        else
        {
            Intent signup = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(signup);
        }

        return super.onOptionsItemSelected(item);
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
        gdvTrendProduct.setAdapter(adapter);
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
        mangQuangCao.add("https://cdn.tgdd.vn/Files/2017/08/13/1012657/j7_pro_isaac_800x450.jpg");
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
        gdvListProduct=findViewById(R.id.gdvListproduct);
        gdvTrendProduct=findViewById(R.id.gdvTrendproduct);


    }

}