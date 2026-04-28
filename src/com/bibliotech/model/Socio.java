package com.bibliotech.model;

public abstract class Socio {

    private final String nombre;
    private final String apellido;
    private final String email;
    private final String dni;

    public Socio(SocioDTO socioDTO) {
        this.nombre = socioDTO.nombre();
        this.apellido = socioDTO.apellido();
        this.email = socioDTO.email();
        this.dni = socioDTO.dni();
    }

    public abstract int obtenerLimiteDePrestamo();

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

}
