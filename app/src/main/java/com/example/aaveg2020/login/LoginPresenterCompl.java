package com.example.aaveg2020.login;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.example.aaveg2020.UserUtils;
import com.example.aaveg2020.api.AavegApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class LoginPresenterCompl implements ILoginPresenter {
    ILoginView iLoginView;
    IUser user;
    Handler handler;
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    public LoginPresenterCompl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        context=iLoginView.getContext();
        handler = new Handler(Looper.getMainLooper());
        pref= context.getSharedPreferences("Aaveg2020", MODE_PRIVATE);
        editor = pref.edit();

    }
    @Override
    public void doLogin(final String userId, final String password) {
        AavegApi api;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AavegApi.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(AavegApi.class);

        Call<LoginBody> call = api.loginUser(userId,password);

        call.enqueue(new Callback<LoginBody>() {
            @Override
            public void onResponse(Call<LoginBody> call, Response<LoginBody> response) {
                user=new UserModel(userId,password);
                final int code = user.checkUserValidity(userId, password);
                if (code != 0) {
                    iLoginView.onLoginResult(401, "Please enter both rollnumber and password");
                }
                else if(response.code()==200){
                    editor.putString("user_id", response.body().getUser_id());
                    editor.putString("APIToken",response.body().getAPIToken());
                    editor.apply();
                    UserUtils.userId=pref.getString("user_id",null);
                    UserUtils.APIToken=pref.getString("APIToken",null);
                    iLoginView.onLoginResult(response.code(),"Login Success");
                }
                else {
                    iLoginView.onLoginResult(response.code(), "user not found");
                }
            }

            @Override
            public void onFailure(Call<LoginBody> call, Throwable t) {
                iLoginView.onLoginResult(500,"There is some error");

            }
        });
    }

    @Override
    public void hasHostel(String APIToken) {
        AavegApi api;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AavegApi.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(AavegApi.class);

        Call<LoginBody> call = api.checkHostel(pref.getString("APIToken",null));

        call.enqueue(new Callback<LoginBody>() {
            @Override
            public void onResponse(Call<LoginBody> call, Response<LoginBody> response) {
                if (response.body() != null) {
                    if(!response.body().getHostel_chosen()){
                        iLoginView.setHostel();
                    }
                    else{
                        editor.putString("hostel",response.body().getHostel());
                        editor.apply();
                        UserUtils.hostel=response.body().getHostel();
                        iLoginView.goToMainScreen();


                    }
                }
                else{
                    Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginBody> call, Throwable t) {
                Toast.makeText(context,"Error in checking user hostel",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }


}
