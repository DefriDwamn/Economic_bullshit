package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Timer;


public class MainActivity extends AppCompatActivity {

    Button buttonShop, buttonShopUpgrade;
    Animation scaleUp, scaleDown;
    FrameLayout frameMain;
    TextView money, zavodi, diamond;

    Handler handler = new Handler();
    Runnable score_hTask;
    DecimalFormat df = new DecimalFormat("##.##");
    private float score;
    private float kof;
    public static final String APP_PREFERENCES = "Settings";
    public static final String APP_PREFERENCES_SCORE = "score";
    public static final String APP_PREFERENCES_KOF = "kof";
    private SharedPreferences Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        df.setRoundingMode(RoundingMode.DOWN);

        money = findViewById(R.id.money);
        zavodi = findViewById(R.id.zavodi);
        diamond = findViewById(R.id.diamond);
        frameMain = findViewById(R.id.frameMain);
        buttonShop = findViewById(R.id.buttonShop);
        buttonShopUpgrade = findViewById(R.id.buttonShopUpgrade);

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.button_scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.button_scale_down);
        kof = 1;
        score_hTask = new Runnable()
        {
            @Override
            public void run() {
                if(score<Float.MAX_VALUE) {
                    score = kof+score;
                    money.setText(df.format(score)+"");
                }
                else {
                    handler.removeCallbacks(score_hTask);
                }
                handler.postDelayed(score_hTask, 1000);
            }
        };
        score_hTask.run();

        buttonShop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fTrans = fragmentManager.beginTransaction();

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonShop.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    buttonShop.startAnimation(scaleDown);
                    frameMain.setForeground(new ColorDrawable(getResources().getColor(R.color.colorM)));
                    Fragment1 frag1 = new Fragment1();
                    fTrans.replace(R.id.frgmCont, frag1, "TAG_1").setReorderingAllowed(true).commit();
                    fragmentManager.findFragmentByTag("TAG_1");
                    buttonShop.setEnabled(false);
                    buttonShopUpgrade.setEnabled(false);
                }
                return true;
            }
        });
        buttonShopUpgrade.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fTrans = fragmentManager.beginTransaction();

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonShopUpgrade.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    buttonShopUpgrade.startAnimation(scaleDown);
                    frameMain.setForeground(new ColorDrawable(getResources().getColor(R.color.colorM)));
                    Fragment2 frag2 = new Fragment2();
                    fTrans.replace(R.id.frgmCont, frag2, "TAG_2").setReorderingAllowed(true).commit();
                    fragmentManager.findFragmentByTag("TAG_2");
                    buttonShopUpgrade.setEnabled(false);
                    buttonShop.setEnabled(false);
                }
                return true;
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = Settings.edit();
        editor.putFloat(APP_PREFERENCES_SCORE, score);
        editor.putFloat(APP_PREFERENCES_KOF, kof);
        editor.apply();
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onResume() {
        super.onResume();
        if (Settings.contains(APP_PREFERENCES_SCORE)) {
            score = Settings.getFloat(APP_PREFERENCES_SCORE, 0);
            money.setText(df.format(score)+"");
        }
        if (Settings.contains(APP_PREFERENCES_KOF)) {
            kof = Settings.getFloat(APP_PREFERENCES_KOF, 0);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }
}
