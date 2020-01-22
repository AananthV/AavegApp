package com.example.aaveg2020.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.aaveg2020.MainActivity;
import com.example.aaveg2020.R;
import com.example.aaveg2020.UserUtils;
import com.example.aaveg2020.editableexplosionlibrarycode.ExplosionField;
import com.example.aaveg2020.login.ChooseHostel;
import com.example.aaveg2020.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    ConstraintLayout splashConstraint;
    ImageView buildingsView;
    ImageView trophy;
    ImageView explosionView;
    int screenHeight, screenWidth;
    ExplosionField explosionField;
    FlashingView flashingView;
    RemoveFlashView removeFlashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        removeFlashView = () -> splashConstraint.removeView(flashingView);
        splashConstraint = findViewById(R.id.cl_splash_layout);
        buildingsView = new ImageView(this);
        trophy = new ImageView(this);
        flashingView = new FlashingView(this,removeFlashView);
        explosionView = new ImageView(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        setupTrophy();
        setupBuildings();   // adds building to the view in correct location.
        setupExplosionView();
        animateTrophy();
    }

    private void setupExplosionView() {

        ConstraintSet constraintSetExplosion = new ConstraintSet();

        explosionView.setImageResource(R.drawable.explosion_compressed);
        explosionView.setScaleType(ImageView.ScaleType.FIT_XY);

        explosionView.setId(View.generateViewId());

        ConstraintLayout.LayoutParams lp = new ConstraintLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)(screenHeight*0.7));

        splashConstraint.addView(explosionView,lp);

        constraintSetExplosion.clone(splashConstraint);

        constraintSetExplosion.connect(
                explosionView.getId(),
                ConstraintSet.TOP,
                splashConstraint.getId(),
                ConstraintSet.BOTTOM
        );

        constraintSetExplosion.connect(
                explosionView.getId(),
                ConstraintSet.START,
                splashConstraint.getId(),
                ConstraintSet.START
        );

        constraintSetExplosion.connect(
                buildingsView.getId(),
                ConstraintSet.END,
                splashConstraint.getId(),
                ConstraintSet.END
        );

        constraintSetExplosion.applyTo(splashConstraint);
    }

    private void setupBuildings() {

        ConstraintSet constraintSetBuildings = new ConstraintSet();

        buildingsView.setImageResource(R.drawable.buildings_compressed);
        buildingsView.setScaleType(ImageView.ScaleType.FIT_XY);

        buildingsView.setId(View.generateViewId());

        ConstraintLayout.LayoutParams lp = new ConstraintLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)(screenHeight*0.4));

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
                .LayoutParams(350, 400);

        splashConstraint.addView(trophy, lp);
        constraintSet.clone(splashConstraint);

        constraintSet.connect(
                trophy.getId(),
                ConstraintSet.BOTTOM,
                splashConstraint.getId(),
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

        constraintSet.connect(
                trophy.getId(),
                ConstraintSet.TOP,
                splashConstraint.getId(),
                ConstraintSet.TOP
        );

        constraintSet.applyTo(splashConstraint);
    }

    private void animateTrophy() {

        TranslateAnimation trophyAnimation = new TranslateAnimation(
                0,
                0,
                screenHeight * 0.5f,
                0);

        trophyAnimation.setDuration(2000);
        trophyAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                trophyBoom();
                trophy.setVisibility(View.GONE);
                flashScreen();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        trophyAnimation.setFillAfter(true);
        trophy.startAnimation(trophyAnimation);

        final Handler handler = new Handler();
        handler.postDelayed(this::changeBackground, 2000);

    }

    private void changeBackground() {
        // remove background view and replace with other views.
        splashConstraint.removeView(buildingsView);

        TranslateAnimation explosionAnimation = new TranslateAnimation(
                0,
                0,
                0,
                -1*screenHeight*0.7f);

        explosionAnimation.setDuration(5000);
        explosionAnimation.setFillAfter(true);
        explosionAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent;

                System.out.println("value of api token is "+UserUtils.APIToken);
                System.out.println("value of hostel is "+UserUtils.hostel);

                if(UserUtils.APIToken!=null && UserUtils.hostel!=null)
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                else
                    intent = new Intent(SplashActivity.this, LoginActivity.class);

                startActivity(intent);
                overridePendingTransition(0,0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        explosionView.startAnimation(explosionAnimation);

        splashConstraint.addView(buildingsView);
    }

    private void flashScreen() {
        splashConstraint.addView(flashingView);
    }

    private void trophyBoom() {
        // call blasting thing
        explosionField = ExplosionField.attach2Window(this);
        explosionField.explode(trophy);
    }
}