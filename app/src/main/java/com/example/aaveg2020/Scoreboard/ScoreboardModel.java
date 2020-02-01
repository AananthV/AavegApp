package com.example.aaveg2020.Scoreboard;

import com.example.aaveg2020.Scoreboard.ui.Overall.OverallModel;
import com.google.gson.annotations.SerializedName;

public class ScoreboardModel {
    @SerializedName("total")
    OverallModel total;
    @SerializedName("standings")
    StandingsModel standings;
    @SerializedName("recents")
    RecentsModel recents;

    public OverallModel getTotal() {
        return total;
    }

    public StandingsModel getStandings() {
        return standings;
    }

    public RecentsModel getRecents() {
        return recents;
    }

    public ScoreboardModel(OverallModel total, StandingsModel standings, RecentsModel recents) {
        this.total = total;
        this.standings = standings;
        this.recents = recents;
    }
}
