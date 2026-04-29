package com.bibliotech.service;

import com.bibliotech.exception.LibroNoDisponibleException;
import com.bibliotech.exception.LimitePrestamosExcedidoException;
import com.bibliotech.exception.SocioNoEncontradoException;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.Repository;

import java.util.*;

public class PrestamoService {

    private final Repository<Recurso, String> recursoRepo;
    private final Repository<Socio, String> socioRepo;
    private final Map<String, List<String>> prestamosActivos = new HashMap<>();

    public PrestamoService(Repository<Recurso, String> recursoRepo,
                           Repository<Socio, String> socioRepo) {
        this.recursoRepo = recursoRepo;
        this.socioRepo = socioRepo;
    }

    public void realizarPrestamo(String isbn, String dni) throws
            LibroNoDisponibleException, SocioNoEncontradoException, LimitePrestamosExcedidoException {

        Optional<Recurso> recurso = this.recursoRepo.buscarPorId(isbn);

        if (recurso.isEmpty()) {
            throw new LibroNoDisponibleException("No se encuentra el libro con isbn " + isbn);
        }

        Optional<Socio> socioEncontrado = this.socioRepo.buscarPorId(dni);
        if (socioEncontrado.isEmpty()) {
            throw new SocioNoEncontradoException("Socio con dni: " + dni + " no encontrado");
        }

        Socio socio = socioEncontrado.get();
        if (prestamosActivos.getOrDefault(dni, List.of()).size() >= socio.obtenerLimiteDePrestamo()) {
            throw new LimitePrestamosExcedidoException("El socio alcanzó su límite de préstamos");
        }

        List<String> prestamosDelSocio = prestamosActivos.getOrDefault(dni, new ArrayList<>());
        prestamosDelSocio.add(isbn);
        this.prestamosActivos.put(dni, prestamosDelSocio);

    }

    public void devolverLibro(String isbn, String dni) throws LibroNoDisponibleException, SocioNoEncontradoException {
        Optional<Socio> socioEncontrado = this.socioRepo.buscarPorId(dni);
        if (socioEncontrado.isEmpty()) {
            throw new SocioNoEncontradoException("Socio con dni: " + dni + " no encontrado");
        }

        List<String> prestamosDelSocio = prestamosActivos.getOrDefault(dni, new ArrayList<>());
        if (!prestamosDelSocio.contains(isbn)) {
            throw new LibroNoDisponibleException("El socio no tiene prestado el libro con isbn: " + isbn);
        }

        prestamosDelSocio.remove(isbn);
        prestamosActivos.put(dni, prestamosDelSocio);
    }

}