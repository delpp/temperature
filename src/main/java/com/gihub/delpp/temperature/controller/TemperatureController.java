package com.gihub.delpp.temperature.controller;

import com.gihub.delpp.temperature.model.TemperatureModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("temperature")
public class TemperatureController {

    @GetMapping
    String showTemperature(Model model){
        TemperatureModel temperatureModel = new TemperatureModel();
        temperatureModel.setTownName(new Town("Warszawa"));
        temperatureModel.setTemperature(25);
        model.addAttribute("temperature", temperatureModel);
        return "temperature";
    }

}
