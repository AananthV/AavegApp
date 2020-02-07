package in.aaveg.aaveg2020.login;

public interface IUser {
        String getName();

        String getPasswd();

        int checkUserValidity(String name, String passwd);
}

