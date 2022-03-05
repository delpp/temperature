package com.gihub.delpp.temperature.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TemperatureModel {
    //static List<TemperatureModel> listOfTemperatures = new ConcurrentSkipListSet<>();
    //static Set<String> setOfTowns = ConcurrentHashMap.newKeySet();

    public static Set<String> towns = new TreeSet<String>();
    public static List<TemperatureModel> listOfTemperatures = new ArrayList<TemperatureModel>();

    String townName;
    Integer temperature;
    LocalTime localTime;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        if (!towns.contains(townName)) towns.add(townName);
        this.townName = townName;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localDateTime) {
        this.localTime = localDateTime;
    }

    @Override
    public String toString() {
        return "TemperatureModel{" +
                "townName='" + townName + '\'' +
                ", temperature=" + temperature +
                ", localDateTime=" + localTime +
                '}' + "\n";
    }
}
