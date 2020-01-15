package com.example.aaveg2020.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.aaveg2020.R;

public class SplashActivity extends AppCompatActivity {

    ConstraintLayout splashConstraint;
    ImageView buildingsView;
    ConstraintSet constraintSetBuildings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashConstraint = (ConstraintLayout) findViewById(R.id.cl_splash_layout);
        constraintSetBuildings = new ConstraintSet();

        buildingsView = new ImageView(this);
        buildingsView.setImageResource(R.drawable.buildings_compressed);
        buildingsView.setScaleType(ImageView.ScaleType.FIT_XY);

        buildingsView.setId(View.generateViewId());

        ConstraintLayout.LayoutParams lp = new ConstraintLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        splashConstraint.addView(buildingsView,lp);

        constraintSetBuildings.clone(splashConstraint);
        constraintSetBuildings.connect(buildingsView.getId(),ConstraintSet.BOTTOM,splashConstraint.getId(),ConstraintSet.BOTTOM);
        constraintSetBuildings.connect(buildingsView.getId(),ConstraintSet.START,splashConstraint.getId(),ConstraintSet.START);
        constraintSetBuildings.connect(buildingsView.getId(),ConstraintSet.END,splashConstraint.getId(),ConstraintSet.END);

        constraintSetBuildings.applyTo(splashConstraint);
    }
}
