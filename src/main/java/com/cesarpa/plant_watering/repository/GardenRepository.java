package com.cesarpa.plant_watering.repository;

import com.cesarpa.plant_watering.model.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {
}
