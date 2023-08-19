package com.testesautomatizados.demo.common;

import com.testesautomatizados.demo.domain.model.Planet;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("name", "climate", "terrain");
    public static final Planet INVALID_PLANET = new Planet("", "", "");
    public static final Long ID = 1l;
    public static final Planet PLANETID = new Planet(ID,"name", "climate", "terrain");
}
