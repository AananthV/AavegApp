package com.example.aaveg2020.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.aaveg2020.Home.HomeView;
import com.example.aaveg2020.MainActivity;
import com.example.aaveg2020.R;
import com.example.aaveg2020.Scoreboard.ScoreboardModel;
import com.example.aaveg2020.Scoreboard.ScoreboardPresenter;
import com.example.aaveg2020.Scoreboard.ScoreboardPresenterImpl;
import com.google.android.material.snackbar.Snackbar;

import static com.example.aaveg2020.UserUtils.APIToken;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener, HomeView {
    View child, trans;
    FrameLayout item;
    private EditText editUser;
    private EditText editPass;
    private Button btnLogin;
    private ILoginPresenter loginPresenter;

    ImageView hostelLogo, aaveglogo;
    ImageView ground;

    TextView loginBanner;
    Animation moveRight;
    TextView madeWith;
    View dialog;
    AlertDialog loadingDialog;
    Handler handler;
    Runnable runnable;
    Snackbar snackbar;
    ScoreboardModel scoreboardModel;

    ScoreboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        System.out.println("!!!!!!!!!!login");
        snackbar = Snackbar.make(findViewById(android.R.id.content), "Check your internet and try again.", Snackbar.LENGTH_INDEFINITE);
        /*snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = findViewById(R.id.btn_login_login);
                LoginActivity.this.onClick(view);
                loadingDialog.show();
            }
        });*/

        presenter = new ScoreboardPresenterImpl(this);
        presenter.getTotal();

        handler = new Handler();
        item = findViewById(R.id.hostel_chooser);
        child = getLayoutInflater()
                .inflate(R.layout.loginview, item, false);
        item.addView(child);
        moveRight = AnimationUtils.loadAnimation(this, R.anim.move_right);

        editUser = this.findViewById(R.id.et_login_username);
        editPass = this.findViewById(R.id.et_login_password);
        btnLogin = this.findViewById(R.id.btn_login_login);

        btnLogin.setOnClickListener(this);
        loginPresenter = new LoginPresenterCompl(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        loginBanner = findViewById(R.id.loginBanner);
        loginBanner.setBackgroundResource(R.drawable.cardbanner);
        madeWith = findViewById(R.id.tv_made_with);
        dialog = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null);
        loadingDialog = new AlertDialog.Builder(this).setView(dialog).setCancelable(false).create();

        madeWith.setClickable(true);
        madeWith.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<p>Made with ♥ by <a href=\"https://delta.nitt.edu\" target=\"_blank\">DeltaForce</a> and Aaveg Design Team </p>";
        madeWith.setText(Html.fromHtml(text));
        loginBanner.startAnimation(moveRight);

        runnable = () -> {
            snackbar = Snackbar.make(findViewById(android.R.id.content), "Check your internet and try again.", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View view = findViewById(R.id.btn_login_login);
                            LoginActivity.this.onClick(view);
                        }
                    })
                    .show();
            presenter.getTotal();
            loadingDialog.dismiss();
        };
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_login_login:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editPass.getWindowToken(), 0);

                if (editUser.getText().toString().length() == 9) {
                    loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                    btnLogin.setEnabled(false);
                    loginPresenter.doLogin(editUser.getText().toString(), editPass.getText().toString());
                    loadingDialog.show();

                    getSnackBarAfterFixedTime();

                } else {
                    Toast.makeText(this, "Check your User ID.", Toast.LENGTH_SHORT).show();
                    break;
                }
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
        removeSnackBarTimer();
        btnLogin.setEnabled(true);
        if(scoreboardModel!=null) {
            if (code == 200) {
                loginPresenter.hasHostel(APIToken);
                loadingDialog.dismiss();
            }
            // TODO: Add more cases of code, like pass and user id worng
            else if (code == 401) {
                Toast.makeText(this, "Wrong credentials.", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        }
        else {
            getSnackBarAfterFixedTime();
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
    }

    @Override
    public void setHostel() {
        removeSnackBarTimer();
        item.removeView(child);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.hostel_chooser, new ChooseHostel());
        ft.commit();
        removeSnackBarTimer();
        if(snackbar!=null)
        snackbar.dismiss();
        loadingDialog.dismiss();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void goToMainScreen() {
        loadingDialog.dismiss();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    private void getSnackBarAfterFixedTime() {
        handler.postDelayed(runnable,8000);
    }

    private void removeSnackBarTimer() {
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onGetScoreboardSuccess(ScoreboardModel scoreboardModel) {
        this.scoreboardModel = scoreboardModel;
    }
}