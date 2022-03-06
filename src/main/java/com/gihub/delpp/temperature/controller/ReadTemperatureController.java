package com.gihub.delpp.temperature.controller;

import com.gihub.delpp.temperature.model.Average;
import com.gihub.delpp.temperature.model.TemperatureModel;
import com.gihub.delpp.temperature.service.TemperatureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("readtemperature")
public class ReadTemperatureController {

        private final TemperatureService service;

        public ReadTemperatureController(TemperatureService service) {
            this.service = service;
        }

        @GetMapping()
        String showTemperatures(Model model) {
            model.addAttribute("temperature", new TemperatureModel());
            return "read_temperatures";
        }

        @ModelAttribute("temperatures")
        List<Average> getAllTemperatures(){
            return service.readAll();
        }
}
