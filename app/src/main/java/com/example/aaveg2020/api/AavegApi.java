package com.example.aaveg2020.api;

import com.example.aaveg2020.Scoreboard.ScoreboardModel;
import com.example.aaveg2020.events.ClusterResponse;
import com.example.aaveg2020.events.Event;
import com.example.aaveg2020.events.ClusterResponse;
import com.example.aaveg2020.login.LoginBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AavegApi {

//    String base_url="https://www.aaveg.in/api/";
    String base_url="https://aaveg.in/api/";

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

    @GET("scoreboard")
    Call<ScoreboardModel> getScoreboard();

    @GET("events")
    Call<ClusterResponse> getAllClusters();


}
