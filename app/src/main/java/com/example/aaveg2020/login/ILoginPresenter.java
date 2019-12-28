package com.example.aaveg2020.login;
public interface ILoginPresenter {
    void clear();
    void doLogin(String name, String passwd);
    void hasHostel(String APIToken);
    void setProgressBarVisiblity(int visiblity);
}