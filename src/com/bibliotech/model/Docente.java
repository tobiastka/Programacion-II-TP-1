package com.bibliotech.model;

public class Docente extends Socio {

    public Docente(SocioDTO socioDTO) {
        super(socioDTO);
    }

    @Override
    public int obtenerLimiteDePrestamo() {
        return 5;
    }
}

