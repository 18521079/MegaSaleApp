package com.example.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.activity.CartActivity;
import com.example.appbanhang.activity.DetailProductActivity;
import com.example.appbanhang.activity.IPAddress;
import com.example.appbanhang.activity.LoginActivity;
import com.example.appbanhang.activity.MainActivity;
import com.example.appbanhang.activity.NewProductActivity;
import com.example.appbanhang.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends ArrayAdapter<Product> {
    IPAddress ipAddress = new IPAddress();
    String URL = ipAddress.ip+"/server/deleteProduct.php";
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
    public View getView(final int position, @Nullable  View convertView, @NonNull final ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cart, null);
        }
        if(arr.size()>0)
        {
            final Product product = this.arr.get(position);
            final TextView name = convertView.findViewById(R.id.txtNameProduct);
            TextView price = convertView.findViewById(R.id.txtPrice);
            ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
            TextView soluong= convertView.findViewById(R.id.numberOfProduct);
            name.setText(product.getName());
            price.setText(product.getPrice());
            soluong.setText(product.soluong);
            Glide.with(this.ct).load(product.getImageLink()).into(imgProduct);
            ImageButton butDel = convertView.findViewById(R.id.buttonRemove);
            butDel.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        MainActivity.maSP= product.masp;
                        if(deleteProduct(parent,position) ==1)
                        {
                            //Toast.makeText(parent.getContext(),"U press at id ", Toast.LENGTH_LONG).show();
                        };
                        //Toast.makeText(parent.getContext(),"U press at id "+String.valueOf(position)+ MainActivity.maGH+MainActivity.maSP, Toast.LENGTH_LONG).show();

                    }
                });

        }
        return convertView;
    }
    private int deleteProduct(final ViewGroup parent, final int pos)
    {
        final int[] result = {0};
        RequestQueue requestQueue= Volley.newRequestQueue(parent.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(parent.getContext(),MainActivity.maSP +"have deleted", Toast.LENGTH_LONG).show();
                result[0] = 1;
               // Intent cartIntent = new Intent(parent.getContext(), CartActivity.class);
               // parent.getContext().startActivity( cartIntent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(parent.getContext(), "Please login",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<>();
                parameters.put("magh", MainActivity.maGH);
                parameters.put("masp", MainActivity.maSP);
                return parameters;
            }
        };

        requestQueue.add(stringRequest);
        return result[0];
    }

}
