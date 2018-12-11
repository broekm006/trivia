package com.uva.triva;

public class Leaderboard {
    private String name, points;

    public Leaderboard(String name, String points){
        this.name = name;
        this.points = points;

    }

    public String getName(){
        return name;
    }

    public String getPoints(){
        return points;
    }

}
