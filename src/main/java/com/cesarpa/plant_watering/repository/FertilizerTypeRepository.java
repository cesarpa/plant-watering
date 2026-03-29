package com.cesarpa.plant_watering.repository;

import com.cesarpa.plant_watering.model.FertilizerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FertilizerTypeRepository extends JpaRepository<FertilizerType, Long> {
}
