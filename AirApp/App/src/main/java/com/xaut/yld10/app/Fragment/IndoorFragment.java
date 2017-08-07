package com.xaut.yld10.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xaut.yld10.app.R;

/**
 * IndoorFragment.java
 * @Author : YLD10
 * @EditTime : 2017/5/20 15:00
 **/

public class IndoorFragment extends android.support.v4.app.Fragment {
    private DrawerLayout mDrawerLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indoor,container,false);
        return view;
    }
}
