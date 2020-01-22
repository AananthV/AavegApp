package com.example.aaveg2020.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.example.aaveg2020.MainActivity;
import com.example.aaveg2020.R;
import com.example.aaveg2020.UserUtils;
import com.example.aaveg2020.api.AavegApi;

import tyrantgit.explosionfield.ExplosionField;

import static com.example.aaveg2020.UserUtils.APIToken;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {
    View child,trans;
    FrameLayout item;
    private EditText editUser;
    private EditText editPass;
    private Button btnLogin;
    private ILoginPresenter loginPresenter;
    private ProgressBar progressBar;
    ImageView hostelLogo,aaveglogo;
    ImageView ground;
    ExplosionField explosionField;
    CardView loginBanner;
    Animation moveRight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        item = findViewById(R.id.hostel_chooser);
       child = getLayoutInflater()
                .inflate(R.layout.loginview, item, false);
        trans = getLayoutInflater()
                .inflate(R.layout.login_transition, item, false);
        item.addView(child);
        moveRight = AnimationUtils.loadAnimation(this, R.anim.falldown);

        editUser = this.findViewById(R.id.et_login_username);
        editPass = this.findViewById(R.id.et_login_password);
        btnLogin = this.findViewById(R.id.btn_login_login);
        progressBar = this.findViewById(R.id.progress_login);
        btnLogin.setOnClickListener(this);
        loginPresenter = new LoginPresenterCompl(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        loginBanner=findViewById(R.id.loginBanner);
        loginBanner.setBackgroundResource(R.drawable.cardbanner);
        explosionField=ExplosionField.attach2Window(this);




    }

    private void initiateAnimation(String hostel) {
        final Context context=this;
       int color=R.color.darkBackground;
        hostelLogo=this.findViewById(R.id.hostelLogo);
        ground=this.findViewById(R.id.g);
        hostelLogo.setVisibility(View.INVISIBLE);
        switch (hostel){
            case "Agate": hostelLogo.setImageResource(R.drawable.agate);color=R.color.agate;break;
            case "Azurite": hostelLogo.setImageResource(R.drawable.azurite);color=R.color.azurite;break;
            case "Bloodstone": hostelLogo.setImageResource(R.drawable.bloodstone);color=R.color.bloodstone;break;
            case "Cobalt": hostelLogo.setImageResource(R.drawable.cobalt);color=R.color.cobalt;break;
            case "Opal": hostelLogo.setImageResource(R.drawable.opal);color=R.color.opal;break;

        }


        Animation falldown = AnimationUtils.loadAnimation(this, R.anim.falldown);
        Animation goup = AnimationUtils.loadAnimation(this, R.anim.goup);
        int finalColor = color;
        falldown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                hostelLogo.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LinearLayout linearLayout=findViewById(R.id.LinearLogin);
                linearLayout.setBackgroundColor(getResources().getColor(finalColor));
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(500);
                }
                ground.setImageResource(R.drawable.groundcrack1);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ground.setImageResource(R.drawable.groundcrack2);

                    }
                }, 200);

                //Go to main screen

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                }, 1200);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        goup.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hostelLogo.startAnimation(falldown);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ground.startAnimation(goup);




        /*DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float q=displaymetrics.heightPixels-(15*hostelLogo.getTop());
        float x=hostelLogo.getTop();
        ObjectAnimator animator=ObjectAnimator.ofFloat(hostelLogo,"y",x,q);
        animator.setDuration(2000);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(animator);
        animatorSet.start();*/


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_login_login:
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                btnLogin.setEnabled(false);
                loginPresenter.doLogin(editUser.getText().toString(), editPass.getText().toString());
                break;
        }
    }

    @Override
    public void onClearText() {
        editUser.setText("");
        editPass.setText("");
    }

    @Override
    public void onLoginResult(int code, String message) {
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        btnLogin.setEnabled(true);
        if (code == 200) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            loginPresenter.hasHostel(APIToken);


        } else{
            Toast.makeText(this, "Login Fail, code = " + code + " " + message, Toast.LENGTH_SHORT).show();
            setHostel();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setHostel() {
        item.removeView(child);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.hostel_chooser, new ChooseHostel());
        ft.commit();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void goToMainScreen() {
       /* explosionField.explode(editPass);
        explosionField.explode(editUser);
        explosionField.explode(btnLogin);
        explosionField.explode(aaveglogo);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                item.removeView(child);
                item.addView(trans);
                initiateAnimation(UserUtils.hostel);

            }
        }, 1500);*/

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }
}
