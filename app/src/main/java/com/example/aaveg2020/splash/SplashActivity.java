package com.example.aaveg2020.splash;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.aaveg2020.R;

public class SplashActivity extends AppCompatActivity {

    ConstraintLayout splashConstraint;
    ImageView buildingsView;
    ImageView trophy;
    int screenHeight, screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashConstraint = findViewById(R.id.cl_splash_layout);
        buildingsView = new ImageView(this);
        trophy = new ImageView(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        setupTrophy();
        setupBuildings();   // adds building to the view in correct location.
        animateTrophy();
    }

    private void setupBuildings() {

        ConstraintSet constraintSetBuildings = new ConstraintSet();

        buildingsView.setImageResource(R.drawable.buildings_compressed);
        buildingsView.setScaleType(ImageView.ScaleType.FIT_XY);

        buildingsView.setId(View.generateViewId());

        ConstraintLayout.LayoutParams lp = new ConstraintLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        splashConstraint.addView(buildingsView,lp);

        constraintSetBuildings.clone(splashConstraint);

        constraintSetBuildings.connect(
                buildingsView.getId(),
                ConstraintSet.BOTTOM,
                splashConstraint.getId(),
                ConstraintSet.BOTTOM
        );

        constraintSetBuildings.connect(
                buildingsView.getId(),
                ConstraintSet.START,
                splashConstraint.getId(),
                ConstraintSet.START
        );

        constraintSetBuildings.connect(
                buildingsView.getId(),
                ConstraintSet.END,
                splashConstraint.getId(),
                ConstraintSet.END
        );

        constraintSetBuildings.applyTo(splashConstraint);
    }

    private void setupTrophy() {

        ConstraintSet constraintSet = new ConstraintSet();

        trophy.setImageResource(R.drawable.cup_compressed);
        //    trophy.setScaleType(ImageView.ScaleType.FIT_XY);

        trophy.setId(View.generateViewId());

        ConstraintLayout.LayoutParams lp = new ConstraintLayout
                .LayoutParams(300, 300);

        splashConstraint.addView(trophy, lp);
        constraintSet.clone(splashConstraint);

        constraintSet.connect(
                trophy.getId(),
                ConstraintSet.BOTTOM,
                trophy.getId(),
                ConstraintSet.BOTTOM
        );

        constraintSet.connect(
                trophy.getId(),
                ConstraintSet.START,
                splashConstraint.getId(),
                ConstraintSet.START
        );

        constraintSet.connect(
                trophy.getId(),
                ConstraintSet.END,
                splashConstraint.getId(),
                ConstraintSet.END
        );

        constraintSet.applyTo(splashConstraint);
    }

    private void animateTrophy() {

        TranslateAnimation trophyAnimation = new TranslateAnimation(
                0,
                0,
                screenHeight,
                screenHeight * 0.4f);

        trophyAnimation.setDuration(1700);
        trophyAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("printing");
                trophyBoom();
                flashScreen();
                changeBackground();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        trophyAnimation.setFillAfter(true);
        trophy.startAnimation(trophyAnimation);
    }

    private void changeBackground() {
        // remove background view and replace with other views.
    }

    private void flashScreen() {
        // flash the screen
    }

    private void trophyBoom() {
        // call blasting thing
    }

}