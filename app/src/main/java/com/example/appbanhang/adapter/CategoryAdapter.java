package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    Context myContext;

    ArrayList<Category> arrCategory;

//giong contructor khoi tao doi tuong do nha

    public CategoryAdapter(Context Context, ArrayList<Category>category )
    {
        myContext= Context;

        arrCategory= category;
    }

    @Override
    public int getCount() {
        return arrCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return arrCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder
    {
        TextView txt_Choose;
        ImageView img_Icon;

        public ViewHolder(TextView txt_Choose, ImageView img_Icon) {
            this.txt_Choose = txt_Choose;
            this.img_Icon = img_Icon;
        }


        public ViewHolder() {

        }
    }
    @Override
    public View getView(int position, View View, ViewGroup parent) {
        ViewHolder viewHolder= null;
        if(View==null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View = inflater.inflate(R.layout.row_listview_category, null);
            // ánh xạ
            viewHolder.img_Icon = (ImageView) View.findViewById(R.id.img_Icon);
            viewHolder.txt_Choose = (TextView) View.findViewById(R.id.txtChoose);
            View.setTag(viewHolder);
        }
        else {

            viewHolder = (ViewHolder) View.getTag();
            Category cate= (Category) getItem(position);
            viewHolder.txt_Choose.setText(cate.getName());
            Picasso.get().load(cate.getImage()).placeholder(R.drawable.error).error(R.drawable.error).into(viewHolder.img_Icon);


        }
        return View;

    }
}
