package com.testesautomatizados.demo.service;

import static com.testesautomatizados.demo.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.testesautomatizados.demo.domain.model.Planet;
import com.testesautomatizados.demo.repository.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
