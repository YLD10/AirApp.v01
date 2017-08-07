package com.xaut.yld10.app.OnClick;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.xaut.yld10.app.CustomControls.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.xaut.yld10.app.ContextUtil;
import com.xaut.yld10.app.OnClick.SecondOnclick.PictureClick;
import com.xaut.yld10.app.R;

/**
 * FriendsClick.java
 * @Author : YLD10
 * @EditTime : 2017/5/20 17:45
 **/

public class FriendsClick {

    private static Context context;
    private static ImageView imageView_Me, imageView_Fri;

    public FriendsClick (final AppCompatActivity act) {
        setContext(ContextUtil.getInstance());
        setImageView_Me((ImageView) act.findViewById(R.id.imageView_Me));
        setImageView_Fri((ImageView) act.findViewById(R.id.imageView_Fri));

        //绘制圆形图片
        Glide.with(act).load(R.drawable.background_image).transform(
                new GlideCircleTransform(getContext())).into(getImageView_Me());
        Glide.with(act).load(R.drawable.tokyo).transform(
                new GlideCircleTransform(getContext())).into(getImageView_Fri());

        clickListener(act);
    }

    public void clickListener(final AppCompatActivity act) {
        getImageView_Me().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PictureClick(act,view);
            }
        });
        getImageView_Fri().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PictureClick(act,view);
            }
        });
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        FriendsClick.context = context;
    }

    public static ImageView getImageView_Me() {
        return imageView_Me;
    }

    public static void setImageView_Me(ImageView imageView_Me) {
        FriendsClick.imageView_Me = imageView_Me;
    }

    public static ImageView getImageView_Fri() {
        return imageView_Fri;
    }

    public static void setImageView_Fri(ImageView imageView_Fri) {
        FriendsClick.imageView_Fri = imageView_Fri;
    }
}
