package com.example.merrychistmasnguyenquangtrung2016.mymp3.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.merrychistmasnguyenquangtrung2016.mymp3.R;
import com.example.merrychistmasnguyenquangtrung2016.mymp3.model.SongModel;

import java.util.ArrayList;

/**
 * Created by dell on 1/17/2017.
 */

public class SongAdapter extends BaseAdapter {
    Context context;
    ArrayList<SongModel> arr;

    public SongAdapter(Context context, ArrayList<SongModel> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView=view;
        if(rowView==null){
            LayoutInflater inflater =LayoutInflater.from(context);
            rowView=inflater.inflate(R.layout.item_song,viewGroup,false);
        }
        TextView lblname=(TextView)rowView.findViewById(R.id.tv_tenbaihat);
        TextView lblcasy=(TextView)rowView.findViewById(R.id.tv_tencasy);
        lblname.setText(arr.get(i).getTitle());
        lblcasy.setText(arr.get(i).getCasy());

        return rowView;
    }
}
