package com.cesarpa.plant_watering.controller;

import com.cesarpa.plant_watering.model.Pot;
import com.cesarpa.plant_watering.service.ServicePotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pot")
public class PotController {

    @Autowired
    private ServicePotService servicePotService;

    @GetMapping
    public String listPots(Model model) {
        model.addAttribute("pots", servicePotService.getAll());
        return "pots-list";
    }

    @PostMapping
    public Pot save(@RequestBody Pot pot) {
        return servicePotService.save(pot.getId(), pot);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        servicePotService.delete(id);
    }
}
