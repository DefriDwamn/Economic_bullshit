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
import java.util.Locale;
import java.util.Timer;


public class MainActivity extends AppCompatActivity {

    Button buttonShop, buttonShopUpgrade;
    Animation scaleUp, scaleDown;
    FrameLayout frameMain;
    TextView money, zavodi, diamond;

    private Locale locale;
    private File saveFile;
    private Data data = new Data();
    private int saveCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveFile = new File(getFilesDir(), "save");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }
        if (saveFile.exists()) {
            try {
                data = (Data) new ObjectInputStream(new FileInputStream(saveFile))
                        .readObject();
                addUpgradeRows(data.getOwnedUpgrades().size());
            } catch (IOException | ClassNotFoundException e) {
                addUpgrade();
            }
        } else {
            addUpgrade();
        }
        saveCounter = 0;

        ScoreUpdaterTimer scoreUpdaterTimer = new ScoreUpdaterTimer(this);
        new Timer().scheduleAtFixedRate(scoreUpdaterTimer, 0, 50);

        money = findViewById(R.id.money);
        zavodi = findViewById(R.id.zavodi);
        diamond = findViewById(R.id.diamond);
        frameMain = findViewById(R.id.frameMain);
        buttonShop = findViewById(R.id.buttonShop);
        buttonShopUpgrade = findViewById(R.id.buttonShopUpgrade);
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.button_scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.button_scale_down);

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

    public void incrementScore(View view) {
        data.addScore(1.0);
    }


    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();

    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onResume() {
        super.onResume();
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
