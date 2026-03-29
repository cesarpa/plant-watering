package com.cesarpa.plant_watering.controller;

import com.cesarpa.plant_watering.model.EnergySource;
import com.cesarpa.plant_watering.repository.EnergySourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergySourceService {

    @Autowired
    private EnergySourceRepository energySourceRepository;

    public List<EnergySource> getAll() {
        return energySourceRepository.findAll();
    }

    public EnergySource save(Long id, EnergySource energySource) {
        if (id != null) {
            energySource.setId(id);
        }
        return energySourceRepository.save(energySource);
    }

    public EnergySource getById(Long id) {
        return energySourceRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        energySourceRepository.deleteById(id);
    }

    public EnergySource update(EnergySource energySource) {
        return energySourceRepository.save(energySource);
    }
}
