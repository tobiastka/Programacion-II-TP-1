package com.bibliotech.repository;

import com.bibliotech.model.Socio;

import java.util.*;

public class SocioRepository implements Repository<Socio, String> {

    private final Map<String, Socio> socios = new HashMap<>();

    @Override
    public void guardar(Socio entidad) {
        socios.put(entidad.getDni(), entidad);
    }

    @Override
    public Optional<Socio> buscarPorId(String id) {
        return Optional.ofNullable(socios.get(id));
    }

    @Override
    public List<Socio> buscarTodos() {
        return new ArrayList<>(socios.values());
    }
}