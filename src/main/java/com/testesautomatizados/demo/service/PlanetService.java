package com.testesautomatizados.demo.service;

import com.testesautomatizados.demo.domain.model.Planet;
import com.testesautomatizados.demo.domain.model.QueryBuilder;
import com.testesautomatizados.demo.repository.PlanetRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Planet> getById(Long id) {
        return planetRepository.findById(id);
    }

    public Optional<Planet> getByName(String name) {
       return planetRepository.findPlanetByName(name);
    }

    public List<Planet> planetList(String terrain, String climate) {
        Example<Planet> query = QueryBuilder.makeQuery(new Planet(terrain, climate));
        return planetRepository.findAll(query);
    }

    public void delete(Long id) {
        planetRepository.deleteById(id);
    }
}
