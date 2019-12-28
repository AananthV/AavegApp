package com.example.aaveg2020.api;

import com.example.aaveg2020.login.LoginBody;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AavegApi {

    @FormUrlEncoded
    @POST("studentLogin")
    Call<LoginBody> loginUser(
            @Field("rollnumber") String rollnumber,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("user/hostel")
    Call<LoginBody> checkHostel(@Field("APIToken") String APIToken);

    @FormUrlEncoded
    @PUT("user/hostel")
    Call<LoginBody> setHostel(
            @Field("hostel") String hostel,
            @Field("APIToken") String APIToken


    );


}
