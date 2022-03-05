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
                        nightAverage = String.valueOf((int) average);
                    }
                    else {
                        nightAverage = "brak danych";
                    }

                    lista.add(new Average(y, roundTheClockAverage, nightAverage , dailyAverage));
                });
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
