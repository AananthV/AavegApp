package com.example.aaveg2020.login;

import android.content.Context;

public interface ILoginView {
    void onClearText();

    void onLoginResult(int result, String message);

    void onSetProgressBarVisibility(int visibility);

    void setHostel();

    Context getContext();

    void goToMainScreen();
}
