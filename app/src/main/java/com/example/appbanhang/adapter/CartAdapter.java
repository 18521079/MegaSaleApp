package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.activity.CartActivity;
import com.example.appbanhang.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CartAdapter extends ArrayAdapter<Product> {
    private Context ct;
    private ArrayList<Product> arr;
   // private Object LayoutInflater;

    public CartAdapter(@NonNull Context context, int resource,  @NonNull List<Product> objects) {
        super(context, resource,  objects);
        this.ct=context;
        this.arr= new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cart, null);
        }
        if(arr.size()>0)
        {
            Product product = this.arr.get(position);
            TextView name = convertView.findViewById(R.id.txtProductName);
            TextView price = convertView.findViewById(R.id.txtProductPrice);
            ImageView imgProduct = convertView.findViewById(R.id.imgProductImage);
            name.setText(product.getName());
            price.setText(product.getPrice());
            Glide.with(this.ct).load(product.getImageLink()).into(imgProduct);

        }
        return convertView;
    }

    /*@NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cart, null);
        }
        if(arr.size()>0)
        {
            Product product = this.arr.get(position);
            TextView name = convertView.findViewById(R.id.txtProductName);
            TextView price = convertView.findViewById(R.id.txtProductPrice);
            ImageView imgProduct = convertView.findViewById(R.id.imgProductImage);
            name.setText(product.getName());
            price.setText(product.getPrice());
            Glide.with(this.ct).load(product.getImageLink()).into(imgProduct);

        }
        return convertView;
    }
    */

}
