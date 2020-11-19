package com.example.appbanhang.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.model.ItemNavigation;
import com.example.appbanhang.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ItemNavigationAdapter extends BaseAdapter  {

    private Context context;
    private int layout;
    private List<ItemNavigation> list ;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHonder
    {
        TextView tv;
        ImageView img;

    }

    public ItemNavigationAdapter(Context context, int layout, List<ItemNavigation> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHonder viewHonder;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout, null);
            viewHonder = new ViewHonder();
            viewHonder.tv=(TextView)convertView.findViewById(R.id.txtFeature);
            viewHonder.img=(ImageView) convertView.findViewById(R.id.imgIcon);
            convertView.setTag(viewHonder);

        }
        else
            viewHonder=(ViewHonder)convertView.getTag();

        viewHonder.tv.setText(list.get(position).iconName);
        viewHonder.img.setImageResource(list.get(position).icon);
        return convertView;
    }

}

