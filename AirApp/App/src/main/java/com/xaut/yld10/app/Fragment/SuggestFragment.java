package com.xaut.yld10.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xaut.yld10.app.R;

/**
 * SuggestFragment.java
 * @Author : YLD10
 * @EditTime : 2017/5/20 15:06
 **/
public class SuggestFragment extends android.support.v4.app.Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggest,container,false);
        return view;
    }
}
