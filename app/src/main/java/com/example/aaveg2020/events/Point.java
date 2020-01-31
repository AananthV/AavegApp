package com.example.aaveg2020.events;

public class Point {
    private int rank;
    private int points;

    public Point() {
    }

    public Point(int rank,int points) {
        this.rank = rank;
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
