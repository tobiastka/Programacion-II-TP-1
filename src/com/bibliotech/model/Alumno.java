package com.bibliotech.model;

public class Alumno extends Socio {


    public Alumno(SocioDTO socioDTO) {
        super(socioDTO);
    }

    @Override
    public int obtenerLimiteDePrestamo() {
        return 3;
    }
}

