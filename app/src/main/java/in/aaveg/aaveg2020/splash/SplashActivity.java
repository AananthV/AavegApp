package in.aaveg.aaveg2020.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import in.aaveg.aaveg2020.Home.HomeView;
import in.aaveg.aaveg2020.MainActivity;
import com.example.aaveg2020.R;
import in.aaveg.aaveg2020.Scoreboard.ScoreboardModel;
import in.aaveg.aaveg2020.Scoreboard.ScoreboardPresenter;
import in.aaveg.aaveg2020.Scoreboard.ScoreboardPresenterImpl;
import in.aaveg.aaveg2020.UserUtils;
import in.aaveg.aaveg2020.editableexplosionlibrarycode.ExplosionField;
import in.aaveg.aaveg2020.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

public class SplashActivity extends AppCompatActivity implements HomeView {

    ScoreboardModel scoreboardModel;
    ConstraintLayout splashConstraint;
    ImageView buildingsView;
    ImageView trophy;
    ImageView explosionView;
    int screenHeight, screenWidth;
    ExplosionField explosionField;
    FlashingView flashingView;
    RemoveFlashView removeFlashView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ScoreboardPresenter presenter;

    View dialog;
    AlertDialog loadingDialog;
    Handler handler;
    Runnable runnable;
    Snackbar snackbar;

    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashConstraint = findViewById(R.id.cl_splash_layout);
        splashConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity();
            }
        });
        splashConstraint.setClickable(false);
        handler = new Handler();
        presenter = new ScoreboardPresenterImpl(this);
        presenter.getTotal();

        dialog = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null);
        loadingDialog = new AlertDialog.Builder(this).setView(dialog).setCancelable(false).create();

        runnable = new Runnable() {
            @Override
            public void run() {
                removeSnackBarTimer();
                snackbar = Snackbar.make(findViewById(android.R.id.content), "Check your internet and try again.", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Retry", v -> {
                    presenter.getTotal();
                    loadingDialog.show();
                    getSnackBarAfterFixedTime();
                })
                        .show();
                loadingDialog.dismiss();
            }
        };

        pref = this.getSharedPreferences("Aaveg2020", MODE_PRIVATE);
        removeFlashView = () -> splashConstraint.removeView(flashingView);
        buildingsView = new ImageView(this);
        trophy = new ImageView(this);
        flashingView = new FlashingView(this, removeFlashView);
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
        explosionView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        explosionView.setId(View.generateViewId());

        ConstraintLayout.LayoutParams lp = new ConstraintLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight * 0.7));

        splashConstraint.addView(explosionView, lp);

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
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight * 0.4));

        splashConstraint.addView(buildingsView, lp);

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
                -1 * screenHeight * 0.77f);

        explosionAnimation.setDuration(4000);
        explosionAnimation.setFillAfter(true);
        explosionAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startNextActivity();
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

    private void startNextActivity() {
        Intent intent;
        UserUtils.APIToken = pref.getString("APIToken", null);
        UserUtils.hostel = pref.getString("hostel", null);
        System.out.println("value of api token is " + UserUtils.APIToken);
        System.out.println("value of hostel is " + UserUtils.hostel);
        if (UserUtils.hostel == null || UserUtils.APIToken == null) {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
            loadingDialog.dismiss();
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
            removeSnackBarTimer();
        } else {
            if (UserUtils.APIToken != null && UserUtils.hostel != null && scoreboardModel != null) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
                loadingDialog.dismiss();
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            } else if (UserUtils.APIToken == null || UserUtils.hostel == null) {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
                loadingDialog.dismiss();
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            } else {
                splashConstraint.setClickable(false);
                loadingDialog.show();
                getSnackBarAfterFixedTime();
            }
        }
    }

    @Override
    public void onGetScoreboardSuccess(ScoreboardModel scoreboardModel) {
        splashConstraint.setClickable(true);
        this.scoreboardModel = scoreboardModel;
        startNextActivityAndResetCallback();
    }

    private void startNextActivityAndResetCallback() {
        if (flag == 1) {
            Intent intent;
            UserUtils.APIToken = pref.getString("APIToken", null);
            UserUtils.hostel = pref.getString("hostel", null);
            System.out.println("value of api token is " + UserUtils.APIToken);
            System.out.println("value of hostel is " + UserUtils.hostel);

            if (UserUtils.APIToken != null && UserUtils.hostel != null && scoreboardModel != null) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
                loadingDialog.dismiss();
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                removeSnackBarTimer();
            } else if (UserUtils.APIToken == null || UserUtils.hostel == null) {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
                loadingDialog.dismiss();
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                removeSnackBarTimer();
            } else {
                splashConstraint.setClickable(false);
                loadingDialog.show();
                getSnackBarAfterFixedTime();
            }
        }
        flag = 1;
    }

    private void getSnackBarAfterFixedTime() {
        handler.postDelayed(runnable, 8000);
    }

    private void removeSnackBarTimer() {
        handler.removeCallbacks(runnable);
    }
}