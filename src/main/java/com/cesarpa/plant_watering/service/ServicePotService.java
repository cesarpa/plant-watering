package com.cesarpa.plant_watering.service;

import com.cesarpa.plant_watering.model.Pot;
import com.cesarpa.plant_watering.repository.PotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePotService {

    @Autowired
    private PotRepository potRepository;

    public List<Pot> getAll() {
        return potRepository.findAll();
    }

    public Pot save(Long id, Pot pot) {
        pot.setId(id);
        return potRepository.save(pot);
    }

    public void delete(Long id) {
        potRepository.deleteById(id);
    }
}
