package com.xaut.yld10.app.OnClick;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xaut.yld10.app.Fragment.FriendsFragment;
import com.xaut.yld10.app.Fragment.IndoorFragment;
import com.xaut.yld10.app.Fragment.SuggestFragment;
import com.xaut.yld10.app.R;

/**
 * MainClick.java
 * @Author : YLD10
 * @EditTime : 2017/5/20 17:49
 **/
public class MainClick {
    private static TextView tabIndoor, tabFriends, tabSuggest;
    private static android.support.v4.app.Fragment fragmentIndoor, fragmentFriends, fragmentSuggest;
    private static FragmentManager manager;
    private static FragmentTransaction transaction;

    public MainClick(Bundle savedInstanceState, final AppCompatActivity act) {
        setTabIndoor((TextView) act.findViewById(R.id.tab_indoor));
        setTabFriends((TextView) act.findViewById(R.id.tab_friends));
        setTabSuggest((TextView) act.findViewById(R.id.tab_suggest));
        setFragmentIndoor(new IndoorFragment());
        setFragmentFriends(new FriendsFragment());
        setFragmentSuggest(new SuggestFragment());

        if (savedInstanceState == null) {
            setManager(act.getSupportFragmentManager());
            setTransaction(getManager().beginTransaction());
            getTransaction().add(R.id.tab_frame, getFragmentSuggest())
                            .add(R.id.tab_frame, getFragmentFriends())
                            .add(R.id.tab_frame, getFragmentIndoor())
                            .commit();

            init(act);
            clickListener(act);
        }
    }

    public void init(final AppCompatActivity act) {
        getTabIndoor().setSelected(true);
        getTabFriends().setSelected(false);
        getTabSuggest().setSelected(false);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    new IndoorClick(act);
                    hideOtherFrame(0);
                }
            }
        };
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void clickListener(final AppCompatActivity act) {
        getTabIndoor().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSelected(0);
                /*if (getFragmentIndoor() == null) {
                    setFragmentIndoor(new IndoorFragment());
                    getManager().beginTransaction()
                                .add(R.id.tab_frame, getFragmentIndoor()).commit();
                }*/
                new IndoorClick(act);
                hideOtherFrame(0);
            }
        });

        getTabFriends().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSelected(1);
                /*if (getFragmentFriends() == null) {
                    setFragmentFriends(new FriendsFragment());
                    getManager().beginTransaction()
                                .add(R.id.tab_frame,getFragmentFriends()).commit();
                }*/
                new FriendsClick(act);
                hideOtherFrame(1);
            }
        });

        getTabSuggest().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSelected(2);
                /*if (getFragmentSuggest() == null) {
                    setFragmentSuggest(new SuggestFragment());
                    getManager().beginTransaction()
                                .add(R.id.tab_frame, getFragmentSuggest()).commit();
                }*/
                new SuggestClick(act);
                hideOtherFrame(2);
            }
        });
    }

    public void  hideOtherFrame(int i) {
        setTransaction(getManager().beginTransaction());
        switch (i) {
            case 0:
                getTransaction().hide(getFragmentFriends())
                                .hide(getFragmentSuggest())
                                .show(getFragmentIndoor())
                                .commit();
                break;
            case 1:
                getTransaction().hide(getFragmentIndoor())
                                .hide(getFragmentSuggest())
                                .show(getFragmentFriends())
                                .commit();
                break;
            case 2:
                getTransaction().hide(getFragmentIndoor())
                                .hide(getFragmentFriends())
                                .show(getFragmentSuggest())
                                .commit();
                break;
        }
    }

    public void initSelected(int i) {
        switch (i) {
            case 0:
                getTabIndoor().setSelected(true);
                getTabFriends().setSelected(false);
                getTabSuggest().setSelected(false);
                break;
            case 1:
                getTabIndoor().setSelected(false);
                getTabFriends().setSelected(true);
                getTabSuggest().setSelected(false);
                break;
            case 2:
                getTabIndoor().setSelected(false);
                getTabFriends().setSelected(false);
                getTabSuggest().setSelected(true);
                break;
        }
    }

    public static TextView getTabIndoor() {
        return tabIndoor;
    }

    public static void setTabIndoor(TextView tabIndoor) {
        MainClick.tabIndoor = tabIndoor;
    }

    public static TextView getTabFriends() {
        return tabFriends;
    }

    public static void setTabFriends(TextView tabFriends) {
        MainClick.tabFriends = tabFriends;
    }

    public static TextView getTabSuggest() {
        return tabSuggest;
    }

    public static void setTabSuggest(TextView tabSuggest) {
        MainClick.tabSuggest = tabSuggest;
    }

    public static Fragment getFragmentIndoor() {
        return fragmentIndoor;
    }

    public static void setFragmentIndoor(Fragment fragmentIndoor) {
        MainClick.fragmentIndoor = fragmentIndoor;
    }

    public static Fragment getFragmentFriends() {
        return fragmentFriends;
    }

    public static void setFragmentFriends(Fragment fragmentFriends) {
        MainClick.fragmentFriends = fragmentFriends;
    }

    public static Fragment getFragmentSuggest() {
        return fragmentSuggest;
    }

    public static void setFragmentSuggest(Fragment fragmentSuggest) {
        MainClick.fragmentSuggest = fragmentSuggest;
    }

    public static FragmentManager getManager() {
        return manager;
    }

    public static void setManager(FragmentManager manager) {
        MainClick.manager = manager;
    }

    public static FragmentTransaction getTransaction() {
        return transaction;
    }

    public static void setTransaction(FragmentTransaction transaction) {
        MainClick.transaction = transaction;
    }

}
