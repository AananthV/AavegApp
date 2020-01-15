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
    ConstraintSet constraintSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

/*        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.mainConstraint);
        ConstraintSet set = new ConstraintSet();

        ImageView view = new ImageView(this);
        view.setId(View.generateViewId());
        layout.addView(view,0);
        set.clone(layout);
        set.connect(view.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 60);
        set.applyTo(layout);*/

        splashConstraint = (ConstraintLayout) findViewById(R.id.cl_splash_layout);
        buildingsView = new ImageView(this);
        buildingsView.setImageResource(R.drawable.buildings_compressed);
        buildingsView.setScaleType(ImageView.ScaleType.FIT_XY);

        buildingsView.setId(View.generateViewId());
        splashConstraint.addView(buildingsView);
        constraintSet.clone(splashConstraint);
        constraintSet.connect(buildingsView.getId(),ConstraintSet.TOP, splashConstraint.getId(), ConstraintSet.TOP);
        constraintSet.applyTo(splashConstraint);
    }
}
