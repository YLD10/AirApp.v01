package com.xaut.yld10.app.OnClick;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.xaut.yld10.app.R;

/**
 * Created by youker on 2017/6/8 0008.
 */

public class SuggestClick {
    private static ImageButton imageButton;
    private static DrawerLayout drawerLayout;
    //private static Context context;

    public SuggestClick(final AppCompatActivity act){
        setImageButton((ImageButton) act.findViewById(R.id.image_bt));
        setDrawerLayout((DrawerLayout)act.findViewById(R.id.drawer_layout));
        clickListener();
    }

    public void clickListener(){
        getImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.image_bt){
                    getDrawerLayout().openDrawer(GravityCompat.START);
                }
            }
        });
    }

    public static ImageButton getImageButton() {
        return imageButton;
    }

    public static void setImageButton(ImageButton imageButton) {
        SuggestClick.imageButton = imageButton;
    }

    public static DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public static void setDrawerLayout(DrawerLayout drawerLayout) {
        SuggestClick.drawerLayout = drawerLayout;
    }

}
