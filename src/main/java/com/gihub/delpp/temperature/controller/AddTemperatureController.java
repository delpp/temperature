package com.gihub.delpp.temperature.controller;

import com.gihub.delpp.temperature.model.TemperatureModel;
import com.gihub.delpp.temperature.service.TemperatureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("addtemperature")
public class AddTemperatureController {

    private final TemperatureService service;

    public AddTemperatureController(TemperatureService service) {
        this.service = service;
    }

    @GetMapping()
    String showStartFields(Model model){
        model.addAttribute("temperature", new TemperatureModel());
        return "add_temperature";
    }

    @PostMapping()
    String addTemperature(@ModelAttribute("temperatura") TemperatureModel current, Model model){
        service.save(current);
        model.addAttribute("temperature", new TemperatureModel());
        model.addAttribute("message", "Dodano temperaturę");
        return "add_temperature";
    }
}
