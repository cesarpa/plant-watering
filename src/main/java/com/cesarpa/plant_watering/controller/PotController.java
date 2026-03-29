package com.cesarpa.plant_watering.controller;

import com.cesarpa.plant_watering.model.Pot;
import com.cesarpa.plant_watering.service.ServicePotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pots")
public class PotController {

    @Autowired
    private ServicePotService servicePotService;

    @GetMapping
    public String listPots(Model model) {
        List<Pot> pots = servicePotService.getAll();
        model.addAttribute("pots", pots);
        return "pot-list";
    }

    @PostMapping
    public String save(@ModelAttribute Pot pot, Model model) {
        Pot savedPot = servicePotService.save(pot.getId(), pot);
        model.addAttribute("message", "Pot created successfully");
        return "redirect:/pots";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Pot pot = servicePotService.getById(id);
        model.addAttribute("pot", pot);
        return "pot-form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Pot pot, Model model) {
        Pot updatedPot = servicePotService.save(pot.getId(), pot);
        model.addAttribute("message", "Pot updated successfully");
        return "redirect:/pots";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        servicePotService.delete(id);
        model.addAttribute("message", "Pot deleted successfully");
        return "redirect:/pots";
    }
}
