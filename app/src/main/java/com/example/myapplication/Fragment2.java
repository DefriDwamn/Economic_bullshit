package com.example.myapplication;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myapplication.R;

public class Fragment2 extends Fragment {
    Button buttonShop, buttonShopUpgrade, buttonBackfromShopUpgrade;
    Animation scaleUp, scaleDown;
    FrameLayout frameMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);

        buttonShop = getActivity().findViewById(R.id.buttonShop);
        buttonShopUpgrade = getActivity().findViewById(R.id.buttonShopUpgrade);
        buttonBackfromShopUpgrade = v.findViewById(R.id.buttonBackfromShopUpgrade);
        scaleUp = AnimationUtils.loadAnimation(getActivity(), R.anim.button_scale_up);
        scaleDown = AnimationUtils.loadAnimation(getActivity(), R.anim.button_scale_down);
        frameMain = getActivity().findViewById(R.id.frameMain);

        buttonBackfromShopUpgrade.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonBackfromShopUpgrade.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    buttonBackfromShopUpgrade.startAnimation(scaleDown);
                    frameMain.setForeground(new ColorDrawable(getResources().getColor(R.color.pusto)));
                    getActivity().getSupportFragmentManager().beginTransaction().remove(Fragment2.this).commit();
                    buttonShopUpgrade.setEnabled(true);
                    buttonShop.setEnabled(true);
                }
                return true;
            }
        });


        return v;
    }
}