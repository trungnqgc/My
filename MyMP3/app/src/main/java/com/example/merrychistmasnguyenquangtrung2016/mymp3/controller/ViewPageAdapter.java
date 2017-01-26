package com.example.merrychistmasnguyenquangtrung2016.mymp3.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.merrychistmasnguyenquangtrung2016.mymp3.List_Song_Current;
import com.example.merrychistmasnguyenquangtrung2016.mymp3.List_Song_favotite;

import java.util.List;

/**
 * Created by dell on 1/24/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    List<Fragment> listFrament;
    private String framentTitle[]={"Danh sách bài hát ","Bài hát yêu thích"};
    public ViewPageAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.listFrament=list;
    }

    @Override
    public Fragment getItem(int position) {
       return listFrament.get(position);
    }

    @Override
    public int getCount() {
        return listFrament.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return framentTitle[position];
    }
}
