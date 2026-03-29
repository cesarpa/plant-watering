package com.cesarpa.plant_watering.controller;

import com.cesarpa.plant_watering.model.EnergySource;
import com.cesarpa.plant_watering.service.ServiceEnergySourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/energy-sources")
public class EnergySourceController {

    @Autowired
    private ServiceEnergySourceService serviceEnergySourceService;

    @GetMapping
    public String listEnergySources(Model model) {
        List<EnergySource> energySources = serviceEnergySourceService.getAll();
        model.addAttribute("energySources", energySources);
        return "energysource-list";
    }

    @PostMapping
    public String save(@ModelAttribute EnergySource energySource, Model model) {
        EnergySource savedEnergySource = serviceEnergySourceService.save(energySource.getId(), energySource);
        model.addAttribute("message", "Energy Source created successfully");
        return "redirect:/energy-sources";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        EnergySource energySource = serviceEnergySourceService.getById(id);
        model.addAttribute("energySource", energySource);
        return "energysource-form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute EnergySource energySource, Model model) {
        EnergySource updatedEnergySource = serviceEnergySourceService.save(energySource.getId(), energySource);
        model.addAttribute("message", "Energy Source updated successfully");
        return "redirect:/energy-sources";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        serviceEnergySourceService.delete(id);
        model.addAttribute("message", "Energy Source deleted successfully");
        return "redirect:/energy-sources";
    }
}
