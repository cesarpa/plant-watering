package com.cesarpa.plant_watering.repository;

import com.cesarpa.plant_watering.model.Pot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotRepository extends JpaRepository<Pot, Long> {
}
