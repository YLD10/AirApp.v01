package com.xaut.yld10.app.OnClick;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xaut.yld10.app.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by HJZ on 2017/6/11.
 */

public class IndoorClick {
    private static DrawerLayout drawerLayout;
    private Handler handler;
    private static TextView textViewTempMe, textViewHumiMe, textViewAirMe;
    private String TempData, HumiData, CoData;
    //private CountDownLatch countDownLatch;
    private int i = 0, threadNumber = 3;
    public IndoorClick(final AppCompatActivity act){
        //act.setContentView(R.layout.fragment_indoor);
        ImageView imageView = (ImageView) act.findViewById(R.id.lefttop);
        final TextView textView = (TextView)act.findViewById(R.id.Text_1);
        final TextView textView1 = (TextView)act.findViewById(R.id.connect);
        final TextView textView2 = (TextView)act.findViewById(R.id.textView);
        final TextView textView3 = (TextView)act.findViewById(R.id.hide1);
        final TextView textView4 = (TextView)act.findViewById(R.id.textView_Info_Temp1);
        final TextView textView5 = (TextView)act.findViewById(R.id.textView_Info_Humi1);
        final TextView textView6 = (TextView)act.findViewById(R.id.textView_Info_Air1);
        drawerLayout = (DrawerLayout) act.findViewById(R.id.drawer_layout);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(act,"OnClick!",Toast.LENGTH_LONG).show();
                if(v.getId() == R.id.lefttop)
                    getDrawerLayout().openDrawer(GravityCompat.START);
            }
        });
        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View x){
                View contentView = LayoutInflater.from(act).inflate(R.layout.fragment_indoor_first2,null);
                act.setContentView(R.layout.fragment_indoor_first2);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View y){
                RelativeLayout layout = (RelativeLayout)act.findViewById(R.id.mylayout);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);
                textView1.setVisibility(View.INVISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.VISIBLE);
                textView6.setVisibility(View.VISIBLE);
                layout.setBackgroundResource(R.drawable.shinei);
                        View contentView = LayoutInflater.from(act).inflate(R.layout.fragment_indoor,null);
                //act.setContentView(contentView);
                ImageView imageView = (ImageView) act.findViewById(R.id.lefttop);
                drawerLayout = (DrawerLayout) act.findViewById(R.id.drawer_layout);

                setTextViewTempMe((TextView) contentView.findViewById(R.id.textView_Info_Temp1));
                setTextViewHumiMe((TextView) contentView.findViewById(R.id.textView_Info_Humi1));
                setTextViewAirMe((TextView) contentView.findViewById(R.id.textView_Info_Air1));

                handlerAndThread();

                imageView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v){
                        getDrawerLayout().openDrawer(GravityCompat.START);
                    }
                });
            }
        });
    }
    public static DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }
    public static void setDrawerLayout(DrawerLayout drawerLayout) {
        IndoorClick.drawerLayout = drawerLayout;
    }
    public void PictureClick1(final AppCompatActivity act, View view) {
        //setCountDownLatch(new CountDownLatch(threadNumber));


    }
    public void handlerAndThread() {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    getTextViewTempMe().setText("温度："+getTempData()+"℃");
                }else if (msg.what == 2){
                    getTextViewHumiMe().setText("湿度："+getHumiData()+"%");
                }else {
                    getTextViewAirMe().setText("CO浓度："+getCoData()+"ppm");
                }
            }
        };

        Log.e("信息：","Start");
        new Thread() {
            @Override
            public void run() {
                getData("356403","408048",1);
                //getCountDownLatch().countDown();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                getData("356403","408047",2);
                //getCountDownLatch().countDown();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                getData("356403","408153",3);
                //getCountDownLatch().countDown();
            }
        }.start();
    }

    public void getData(String device, String sensor, int type) {
        try {
            URL url = new URL("http://api.yeelink.net/v1.1/device/"+device+"/sensor/"+sensor+"/datapoints");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("U-ApiKey","c1bc15b6d0adec562864af9829102870");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            int code = httpURLConnection.getResponseCode();

            if (code == 200) {
                Log.e("返回码：", ""+code);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String str = "";
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                br.close();

                Gson gson = new Gson();
                Map<String,String> map = gson.fromJson(sb.toString(),
                        new TypeToken<Map<String,String>>(){}.getType());

                switch (type) {
                    case 1:
                        setTempData(map.get("value"));
                        break;
                    case 2:
                        setHumiData(map.get("value"));
                        break;
                    case 3:
                        setCoData(map.get("value"));
                        break;
                }
                Message message = new Message();
                message.what = type;
                handler.sendMessage(message);
            }else {
                Log.e("返回码：",code+"！");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("信息：","链接地址有问题！");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("信息：","建立连接失败！");
        }
    }
    public static TextView getTextViewTempMe() {
        return textViewTempMe;
    }
    public static void setTextViewTempMe(TextView textViewTempMe) {
        IndoorClick.textViewTempMe = textViewTempMe;
    }
    public static TextView getTextViewHumiMe() {
        return textViewHumiMe;
    }

    public static void setTextViewHumiMe(TextView textViewHumiMe) {
        IndoorClick.textViewHumiMe = textViewHumiMe;
    }

    public static TextView getTextViewAirMe() {
        return textViewAirMe;
    }

    public static void setTextViewAirMe(TextView textViewAirMe) {
        IndoorClick.textViewAirMe = textViewAirMe;
    }
    public String getTempData() {
        return TempData;
    }
    public void setTempData(String tempData) {
        TempData = tempData;
    }
    public String getHumiData() {
        return HumiData;
    }

    public void setHumiData(String humiData) {
        HumiData = humiData;
    }

    public String getCoData() {
        return CoData;
    }

    public void setCoData(String coData) {
        CoData = coData;
    }
/*    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }*/
}
