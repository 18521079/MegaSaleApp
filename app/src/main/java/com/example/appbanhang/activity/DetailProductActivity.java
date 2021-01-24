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

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;

public class DetailProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btLike, btAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product2);
        anhXa();
        actionToolBar();
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        price= price+" $";
        String image = intent.getStringExtra("image");
        TextView nameText = findViewById(R.id.nameOfProduct);
        TextView priceText = findViewById(R.id.priceOfProduct);
        ImageView imgProduct = findViewById(R.id.imageOfProduct);
        nameText.setText(name);
        priceText.setText(price);
        Glide.with(this).load(image).into(imgProduct);
        btAddToCart= findViewById(R.id.btAddCart);
        btAddToCart.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Toast.makeText(DetailProductActivity.this,name + " have add to your cart", Toast.LENGTH_LONG).show();

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

                    }
                });
        //Toast.makeText(DetailProductActivity.this,"THis is"+message, Toast.LENGTH_LONG).show();

    }
    private  void addToCart()
    {

    }
    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void anhXa() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
    }

}