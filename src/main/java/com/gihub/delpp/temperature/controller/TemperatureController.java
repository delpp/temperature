package com.gihub.delpp.temperature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("temperature")
public class TemperatureController {

    @GetMapping
    String showTemperature(){
        return "temperature";
    }

}
