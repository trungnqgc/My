package com.example.merrychistmasnguyenquangtrung2016.mymp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.merrychistmasnguyenquangtrung2016.mymp3.controller.SongAdapter;
import com.example.merrychistmasnguyenquangtrung2016.mymp3.model.SongModel;

import java.util.ArrayList;


public class List_Song_Current extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private  int Current_position=-1;
    ListView listView;
    SongAdapter adapter;
 public  static ArrayList<SongModel> list;
    SongModel songModel=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_list__song__current,container,false);
        listView=(ListView)view.findViewById(R.id.list_current_baihat);
       gtesong();
        adapter=new SongAdapter(getContext(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        registerForContextMenu(listView);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = getActivity().getIntent();

        intent.putExtra("i",i);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
         switch (item.getItemId()){
             case R.id.xoa_bai_hat:{
                 break;
             }
             case R.id.like_song:{
                   songModel=list.get(Current_position);
                   songModel.setFavotite(true);
                   list.set(Current_position,songModel);
                   List_Song_favotite.listModelFavotite.add(songModel);
                   Toast.makeText(getContext(),list.get(Current_position).isFavotite()+"",Toast.LENGTH_LONG).show();

                 //Toast.makeText(getContext(),"Bạn vừa thêm bài hát này vaod danh sách bài hát yêu thich",Toast.LENGTH_LONG).show();
                 break;
             }
             default:
                 return super.onContextItemSelected(item);
         }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("vitri",i+"vi tri thu");
       Current_position=i;
        return false;
    }
    public static ArrayList<SongModel> gtesong(){
        list=new ArrayList<>();
        list.add(new SongModel("chua bao gio","Trung Quân", R.raw.chuabaogio,false));
        list.add(new SongModel("Lam vo anh nhe","Chi Dân",R.raw.lamvoanhnhe,false));
        list.add(new SongModel("Niệm khúc cuối","Chi Dân",R.raw.niemkhuccuoi,false));
        list.add(new SongModel("Merry Christmas ","ABC",R.raw.wewishyouamerrychristmas,true));
        return list;

    }
}
