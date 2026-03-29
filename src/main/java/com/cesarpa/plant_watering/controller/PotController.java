package com.cesarpa.plant_watering.controller;

import com.cesarpa.plant_watering.model.Pot;
import com.cesarpa.plant_watering.service.PotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pots")
public class PotController {

    @Autowired
    private PotService potService;

    @GetMapping
    public String listPots(Model model) {
        List<Pot> pots = potService.getAll();
        model.addAttribute("pots", pots);
        return "pot-list";
    }

    @PostMapping
    public String save(@ModelAttribute Pot pot, Model model) {
        Pot savedPot = potService.save(pot.getId(), pot);
        model.addAttribute("message", "Pot created successfully");
        return "redirect:/pots";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Pot pot = potService.getById(id);
        model.addAttribute("pot", pot);
        return "pot-form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Pot pot, Model model) {
        Pot updatedPot = potService.save(pot.getId(), pot);
        model.addAttribute("message", "Pot updated successfully");
        return "redirect:/pots";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        potService.delete(id);
        model.addAttribute("message", "Pot deleted successfully");
        return "redirect:/pots";
    }
}
