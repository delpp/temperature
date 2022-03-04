package com.gihub.delpp.temperature.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TemperatureModel {
    static List<TemperatureModel> listOfTemperatures = new ArrayList<TemperatureModel>();
    static List<String> listOfTowns = new ArrayList<String>();

    String townName;
    Integer temperature;
    LocalDateTime localDateTime;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        if (!listOfTowns.contains(townName)) listOfTowns.add(townName);
        this.townName = townName;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
