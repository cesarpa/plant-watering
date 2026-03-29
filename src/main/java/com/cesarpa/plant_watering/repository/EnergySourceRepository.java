package com.cesarpa.plant_watering.repository;

import com.cesarpa.plant_watering.model.EnergySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergySourceRepository extends JpaRepository<EnergySource, Long> {
}
