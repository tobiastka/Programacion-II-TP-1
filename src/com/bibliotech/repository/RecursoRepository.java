package com.bibliotech.repository;

import com.bibliotech.model.Recurso;

import java.util.*;

public class RecursoRepository implements Repository<Recurso, String> {

    private final Map<String, Recurso> recursos = new HashMap<>();

    @Override
    public void guardar(Recurso entidad) {
        this.recursos.put(entidad.isbn(), entidad);
    }

    @Override
    public Optional<Recurso> buscarPorId(String id) {
        return Optional.ofNullable(this.recursos.get(id));
    }

    @Override
    public List<Recurso> buscarTodos() {
        return new ArrayList<>(this.recursos.values());
    }
}
