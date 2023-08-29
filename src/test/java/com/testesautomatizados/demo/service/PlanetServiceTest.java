package com.testesautomatizados.demo.service;

import static com.testesautomatizados.demo.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.testesautomatizados.demo.domain.model.Planet;
import com.testesautomatizados.demo.domain.model.QueryBuilder;
import com.testesautomatizados.demo.repository.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;


    @Mock
    private PlanetRepository planetRepository;

    //operação_estado_retorno
    @Test
    public void createPlanet_WithValidData_ReturnPlanet() {
        //Arrange
        when(planetRepository.save(PLANET)).thenReturn(PLANET);
        //system under test
        //Act
        Planet sut =  planetService.create(PLANET);
        //Aseert
        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    public void createPlanet_WithInvalidData_ThrowsException() {
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_ByExistingId_ReturnPlanet() {
        when(planetRepository.findById(ID)).thenReturn(Optional.of(PLANETID));

        Optional<Planet> sut = planetService.getById(ID);

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANETID);
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnEmpty() {
        when(planetRepository.findById(ID)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.getById(ID);

        assertThat(sut).isEmpty();
    }

    @Test
    public void getPlanet_ByExistingName_ReturnPlanet() {
        when(planetRepository.findPlanetByName(NAME)).thenReturn(Optional.of(PLANET));

        Optional<Planet> sut = planetService.getByName(NAME);

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getPlanet_ByUnexistingName_ReturnEmpty() {
        when(planetRepository.findPlanetByName(NAME)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.getByName(NAME);

        assertThat(sut).isEmpty();
    }

    @Test
    public void ListPlanets_ReturnsAllPlanets() {
        List<Planet> planets = new ArrayList<>() {{add(PLANET);}};

        Example<Planet> query = QueryBuilder.makeQuery(new Planet(PLANET.getClimate(), PLANET.getTerrain()));

        when(planetRepository.findAll(query)).thenReturn(planets);

        List<Planet> sut = planetService.planetList(PLANET.getTerrain(), PLANET.getClimate());

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(PLANET);
    }

    @Test
    public void ListPlanets_ReturnsNoPlanets() {
        when(planetRepository.findAll((Example<Planet>) any())).thenReturn(Collections.emptyList());

        List<Planet> sut = planetService.planetList(PLANET.getTerrain(), PLANET.getClimate());

        assertThat(sut).isEmpty();
    }

    @Test
    public void deletePlanet_WithExistingId_DoesNotThrowAnyException() {
        assertThatCode(() -> planetService.delete(ID)).doesNotThrowAnyException();
    }

    @Test
    public void deletePlanet_WithUnexistingId_ThrowsAnyException() {
        doThrow(new RuntimeException()).when(planetRepository).deleteById(99L);

        assertThatThrownBy(() -> planetService.delete(99L)).isInstanceOf(RuntimeException.class);
    }
}
