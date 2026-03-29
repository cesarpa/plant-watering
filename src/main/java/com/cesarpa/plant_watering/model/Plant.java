package com.cesarpa.plant_watering.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "energy_id", nullable = false)
    private EnergySource energySource;

    @ManyToOne
    @JoinColumn(name = "fertilizer_id", nullable = false)
    private FertilizerType fertilizerType;

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;

    @ManyToOne
    @JoinColumn(name = "pot_id", nullable = false)
    private Pot pot;

    @Column(nullable = false)
    private int wateringFrequency;

    @Column
    private LocalDate lastPruneDate;
}
