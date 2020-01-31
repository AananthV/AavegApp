package com.example.aaveg2020.events;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Event implements Parcelable {

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("startDateTime")
    private String startDateTime;
    @SerializedName("venue")
    private String venue;
    @SerializedName("description")
    private String description;
    @SerializedName("cluster")
    private String cluster;
    @SerializedName("rules")
    private String rules;
    @SerializedName("cup")
    private String cup;
    @SerializedName("points")
    private List<Integer> points;

    public Event() {
    }

    protected Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        date = in.readString();
        startDateTime = in.readString();
        startTime = in.readString();
        venue = in.readString();
        description = in.readString();
        cluster = in.readString();
        cup = in.readString();
        rules = in.readString();

        int size = in.readInt();
        if (size == 0) {
            points = null;
        } else {
            Class<?> type = (Class<?>) in.readSerializable();
            points = new ArrayList<>(size);
            in.readList(points, type.getClassLoader());
        }

//        parcel.writeInt(getId());
//        parcel.writeString(getName());
//        parcel.writeString(getStartDateTime());
//        parcel.writeString(getStartTime());
//        parcel.writeString(getVenue());
//        parcel.writeString(getDescription());
//        parcel.writeString(getCluster());
//        parcel.writeString(getCup());
//        parcel.writeString(getDate());
//        parcel.writeString(getRules());
//        parcel.writeList(getPoints());
    }

    public Event(String id, String name, String date, String startTime, String startDateTime, String venue, String description, String cluster, String rules, String cup, List<Integer> points) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.startDateTime = startDateTime;
        this.venue = venue;
        this.description = description;
        this.cluster = cluster;
        this.rules = rules;
        this.cup = cup;
        this.points = points;
    }

    public static Creator<Event> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public void setPoints(List<Integer> points) {
        this.points = points;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getId());
        parcel.writeString(getName());
        parcel.writeString(getDate());
        parcel.writeString(getStartDateTime());
        parcel.writeString(getStartTime());
        parcel.writeString(getVenue());
        parcel.writeString(getDescription());
        parcel.writeString(getCluster());
        parcel.writeString(getCup());
        parcel.writeString(getRules());

        parcel.writeInt(getPoints().size());
        final Class<?> objectsType = getPoints().get(0).getClass();
        parcel.writeSerializable(objectsType);
        parcel.writeList(getPoints());
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", startDateTime='" + startDateTime + '\'' +
                ", venue='" + venue + '\'' +
                ", description='" + description + '\'' +
                ", cluster='" + cluster + '\'' +
                ", rules='" + rules + '\'' +
                ", cup='" + cup + '\'' +
                ", points=" + points +
                '}';
    }
}
