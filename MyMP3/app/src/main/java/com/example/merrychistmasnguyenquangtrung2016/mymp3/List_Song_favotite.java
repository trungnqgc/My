package com.example.merrychistmasnguyenquangtrung2016.mymp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.merrychistmasnguyenquangtrung2016.mymp3.controller.SongAdapter;
import com.example.merrychistmasnguyenquangtrung2016.mymp3.model.SongModel;

import java.util.ArrayList;
import java.util.List;


public class List_Song_favotite extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
     private ListView listView;
     public static ArrayList<SongModel> listModelFavotite =new ArrayList<>();
    SongAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list__song_favotite,container,false);
        listView=(ListView)view.findViewById(R.id.danhsach_baihatyeuthich);
        getbaihat();

        adapter=new SongAdapter(getContext(),listModelFavotite);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        registerForContextMenu(listView);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=getActivity().getIntent();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
    public ArrayList<SongModel> getbaihat(){

        for (SongModel sm : List_Song_Current.list){
            if(sm.isFavotite()==true){
                listModelFavotite.add(sm);
            }
        }
        return listModelFavotite;
    }
}
