package com.gihub.delpp.temperature.model;

public class Average {
    String town;
    String roundTheClockAverage;
    String nightAverage;
    String dailyAverage;

    public Average(String town, String roundTheClockAverage, String nightAverage, String dailyAverage) {
        this.town = town;
        this.roundTheClockAverage = roundTheClockAverage;
        this.nightAverage = nightAverage;
        this.dailyAverage = dailyAverage;
    }

    public String getTown() {
        return town;
    }

    public String getRoundTheClockAverage() {
        return roundTheClockAverage;
    }

    public String getNightAverage() {
        return nightAverage;
    }

    public String getDailyAverage() {
        return dailyAverage;
    }
}
