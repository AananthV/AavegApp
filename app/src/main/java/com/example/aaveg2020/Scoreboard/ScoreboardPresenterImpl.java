package com.example.aaveg2020.Scoreboard;

import com.example.aaveg2020.Home.HomeView;
import com.example.aaveg2020.Scoreboard.ui.Cultural.CulturalsView;
import com.example.aaveg2020.Scoreboard.ui.Overall.IOverallView;
import com.example.aaveg2020.Scoreboard.ui.sports.SportsView;
import com.example.aaveg2020.api.AavegApi;
import com.example.aaveg2020.login.LoginBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScoreboardPresenterImpl implements ScoreboardPresenter {

    IOverallView overallView;
    CulturalsView culturalsView;
    SportsView sportsView;
    HomeView homeView;

    @Override
    public void getTotal() {
        AavegApi api;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AavegApi.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(AavegApi.class);

        Call<ScoreboardModel> call = api.getScoreboard();

        call.enqueue(new Callback<ScoreboardModel>() {
            @Override
            public void onResponse(Call<ScoreboardModel> call, Response<ScoreboardModel> response) {
                if(overallView!=null)
                    overallView.onGetScoreboardSuccess(response.body());
                if(culturalsView!=null)
                    culturalsView.onGetScoreboardSuccess(response.body());
                if(sportsView!=null)
                    sportsView.onGetScoreboardSuccess(response.body());
                if (homeView!=null)
                    homeView.onGetScoreboardSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ScoreboardModel> call, Throwable t) {

            }
        });
    }

    public ScoreboardPresenterImpl(IOverallView overallView) {
        this.overallView = overallView;
    }

    public ScoreboardPresenterImpl(CulturalsView culturalsView) {
        this.culturalsView = culturalsView;
    }

    public ScoreboardPresenterImpl(SportsView sportsView) {
        this.sportsView = sportsView;
    }

    public ScoreboardPresenterImpl(HomeView homeView) {
        this.homeView = homeView;
    }
}
