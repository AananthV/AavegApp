package com.example.aaveg2020.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.aaveg2020.MainActivity;
import com.example.aaveg2020.R;

import static com.example.aaveg2020.UserUtils.APIToken;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {
    View child;
    FrameLayout item;
    private EditText editUser;
    private EditText editPass;
    private Button btnLogin;
    private Button btnClear;
    private ILoginPresenter loginPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        item = findViewById(R.id.hostel_chooser);
        child = getLayoutInflater()
                .inflate(R.layout.loginview, item, false);
        item.addView(child);
        editUser = this.findViewById(R.id.et_login_username);
        editPass = this.findViewById(R.id.et_login_password);
        btnLogin = this.findViewById(R.id.btn_login_login);
        btnClear = this.findViewById(R.id.btn_login_clear);
        progressBar = this.findViewById(R.id.progress_login);
        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        loginPresenter = new LoginPresenterCompl(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_clear:
                loginPresenter.clear();
                break;
            case R.id.btn_login_login:
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                btnLogin.setEnabled(false);
                btnClear.setEnabled(false);
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
        btnClear.setEnabled(true);
        if (code == 200) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            loginPresenter.hasHostel(APIToken);
        } else
            Toast.makeText(this, "Login Fail, code = " + code + " " + message, Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
