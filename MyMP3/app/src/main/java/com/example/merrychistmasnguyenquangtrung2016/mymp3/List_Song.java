package com.example.merrychistmasnguyenquangtrung2016.mymp3;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.merrychistmasnguyenquangtrung2016.mymp3.controller.SongAdapter;
import com.example.merrychistmasnguyenquangtrung2016.mymp3.controller.ViewPageAdapter;
import com.example.merrychistmasnguyenquangtrung2016.mymp3.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class List_Song extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

   ViewPager viewPager;
   ViewPageAdapter adapter;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__song);
        viewPager=(ViewPager)findViewById(R.id.viewPage);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        adapter=new ViewPageAdapter(getSupportFragmentManager(),getListFrament());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);

    }
//add tat ca cac Frament vao list
    public List<Fragment> getListFrament(){
        List<Fragment> list=new ArrayList<>();

        list.add(new List_Song_Current());
        list.add(new List_Song_favotite());
        return list;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
}
