package com.gihub.delpp.temperature.service;

import com.gihub.delpp.temperature.model.Average;
import com.gihub.delpp.temperature.model.TemperatureModel;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    public synchronized void save(TemperatureModel toSave){
        toSave.setLocalTime(LocalTime.now());
        TemperatureModel.listOfTemperatures.add(toSave);
    }

    public synchronized List<Average> readAll(){
        List<Average> lista = new ArrayList<>();
        List<Double> averagesFromAllTownsRoundTheClockAverage = new ArrayList<Double>();
        List<Double> averagesFromAllTownsNightAverage = new ArrayList<Double>();
        List<Double> averagesFromAllTownsDailyAverage = new ArrayList<Double>();
        TemperatureModel.towns
                .forEach(y -> {
                    List<TemperatureModel> filteredList;
                    double average;
                    String dailyAverage;
                    String roundTheClockAverage;
                    String nightAverage;

                    filteredList =  TemperatureModel.listOfTemperatures.stream()
                            .filter(x -> x.getTownName().equals(y))
                            .collect(Collectors.toList());
                    if (!filteredList.isEmpty()) {
                        average = filteredList.stream()
                                .map(TemperatureModel::getTemperature)
                                .mapToInt(i -> i)
                                .average().getAsDouble();
                        averagesFromAllTownsRoundTheClockAverage.add(average);
                        roundTheClockAverage = String.valueOf((int) average);
                    }
                    else{
                        roundTheClockAverage = "brak danych";
                    }

                    filteredList =  TemperatureModel.listOfTemperatures.stream()
                            .filter(x -> x.getTownName().equals(y))
                            .filter(z -> z.getLocalTime().isAfter(LocalTime.of(5, 0, 0)))
                            .filter(z -> z.getLocalTime().isBefore(LocalTime.of(20, 0, 0)))
                            .collect(Collectors.toList());
                    if (!filteredList.isEmpty()) {
                        average = filteredList.stream()
                                .map(TemperatureModel::getTemperature)
                                .mapToInt(i -> i)
                                .average().getAsDouble();
                        averagesFromAllTownsDailyAverage.add(average);
                        dailyAverage = String.valueOf((int) average);
                    }
                    else {
                        dailyAverage = "brak danych";
                    }

                    filteredList =  TemperatureModel.listOfTemperatures.stream()
                            .filter(x -> x.getTownName().equals(y))
                            .filter(z -> (z.getLocalTime().isAfter(LocalTime.of(0, 0, 0)))
                                    && (z.getLocalTime().isBefore(LocalTime.of(5, 0, 0)))
                                    ||
                                    (z.getLocalTime().isAfter(LocalTime.of(20, 0, 0)))
                                            && (z.getLocalTime().isBefore(LocalTime.of(23, 59, 59))))
                            .collect(Collectors.toList());

                    if (!filteredList.isEmpty()) {
                        average = filteredList.stream()
                                .map(TemperatureModel::getTemperature)
                                .mapToInt(i -> i)
                                .average().getAsDouble();
                        averagesFromAllTownsNightAverage.add(average);
                        nightAverage = String.valueOf((int) average);
                    }
                    else {
                        nightAverage = "brak danych";
                    }

                    lista.add(new Average(y, roundTheClockAverage, nightAverage , dailyAverage));
                });

        String totalRoundTheClockAverage = "brak danych";
        String totalNightAverage = "brak danych";
        String totalDailyAverage = "brak danych";

        if (!averagesFromAllTownsRoundTheClockAverage.isEmpty())
             totalRoundTheClockAverage = String.valueOf((int)
                     averagesFromAllTownsRoundTheClockAverage.stream()
                     .mapToDouble(i -> i)
                     .average()
                     .getAsDouble());

        if (!averagesFromAllTownsNightAverage.isEmpty())
            totalNightAverage = String.valueOf((int)
                    averagesFromAllTownsNightAverage.stream()
                    .mapToDouble(i -> i)
                    .average()
                    .getAsDouble());


        if (!averagesFromAllTownsDailyAverage.isEmpty())
            totalDailyAverage = String.valueOf((int)
                    averagesFromAllTownsDailyAverage.stream()
                    .mapToDouble(i -> i)
                    .average()
                    .getAsDouble());

        lista.add(new Average("cała Polska", totalRoundTheClockAverage,totalNightAverage,totalDailyAverage));
        return lista;
    }

//    public synchronized Map<String, Integer> readAll(){
//        Map <String, Integer> lista = new TreeMap<>();
//        TemperatureModel.towns.stream()
//                .forEach(y -> {
//                   double average =  TemperatureModel.listOfTemperatures.stream()
//                            .filter(x -> x.getTownName().equals(y))
//                            .map(TemperatureModel::getTemperature)
//                            .mapToInt(i -> i.intValue())
//                            .average().getAsDouble();
//                    System.out.println("miasto: " + y + " średnia dobowa: " + average);
//                   lista.put(y, (int) average);
//        });
//        return lista;
//    }
}
