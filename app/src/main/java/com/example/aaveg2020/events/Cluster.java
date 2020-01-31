package com.example.aaveg2020.events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cluster {
    @SerializedName("_id")
    private String id;
    @SerializedName("events")
    private List<Event> events;

    public Cluster(String id, List<Event> events) {
        this.id = id;
        this.events = events;
    }

    public Cluster() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "id='" + id + '\'' +
                ", events=" + events +
                '}';
    }
}
