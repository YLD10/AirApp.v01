package com.xaut.yld10.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xaut.yld10.app.R;

/**
 * FriendsFragment.java
 * @Author : YLD10
 * @EditTime : 2017/5/20 15:05
 **/
public class FriendsFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends,container,false);
        return view;
    }
}
