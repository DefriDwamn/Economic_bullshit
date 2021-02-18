package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Button buttonShop, buttonShopUpgrade;
    Animation scaleUp, scaleDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fTrans = fragmentManager.beginTransaction();

        buttonShop = findViewById(R.id.buttonShop);
        buttonShopUpgrade = findViewById(R.id.buttonShopUpgrade);

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.button_scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.button_scale_down);
        //
        //код для анимаций баттонов взят с гитхаба :3
        buttonShop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonShop.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    buttonShop.startAnimation(scaleDown);
                    Fragment1 frag1 = new Fragment1();
                    fragmentManager.findFragmentByTag("TAG_1");
                    fTrans.replace(R.id.frgmCont, frag1, "TAG_1").commit();
                }
                return true;
            }
        });

        buttonShopUpgrade.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonShopUpgrade.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    buttonShopUpgrade.startAnimation(scaleDown);
                    Fragment2 frag2 = new Fragment2();
                    fragmentManager.findFragmentByTag("TAG_2");
                        fTrans.replace(R.id.frgmCont, frag2, "TAG_2").commit();
                }
                return true;
            }
        });

    }
    //
    //Полноэкранный режим - https://developer.android.com/training/system-ui/immersive#java
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
