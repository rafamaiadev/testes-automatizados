package com.testesautomatizados.demo.service;

import com.testesautomatizados.demo.domain.model.Planet;
import com.testesautomatizados.demo.repository.PlanetRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {
    PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet create(Planet planet) {
        planetRepository.save(planet);
        return planet;
    }
}
