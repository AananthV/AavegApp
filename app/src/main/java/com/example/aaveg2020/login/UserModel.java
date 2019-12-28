package com.example.aaveg2020.login;
public class UserModel implements IUser {
    String name;
    String passwd;

    public UserModel(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPasswd() {
        return passwd;
    }

    @Override
    public int checkUserValidity(String name, String passwd) {
        if (name == null || passwd == null || name.isEmpty()|| passwd.isEmpty()) {
            return -1;
        }
        return 0;
    }
}
