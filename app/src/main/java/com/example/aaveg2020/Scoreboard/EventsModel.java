package com.example.aaveg2020.Scoreboard;

import com.google.gson.annotations.SerializedName;

public class EventsModel {
    @SerializedName("event_name")
    String event;
    @SerializedName("position")
    String position;

    public String getEvent() {
        return event;
    }

    public String getPosition() {
        return position;
    }
}