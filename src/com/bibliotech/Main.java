package com.bibliotech;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.Alumno;
import com.bibliotech.model.Libro;
import com.bibliotech.model.SocioDTO;
import com.bibliotech.repository.RecursoRepository;
import com.bibliotech.repository.SocioRepository;
import com.bibliotech.service.PrestamoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RecursoRepository recursoRepo = new RecursoRepository();
        SocioRepository socioRepo = new SocioRepository();
        PrestamoService service = new PrestamoService(recursoRepo, socioRepo);
        Scanner scanner = new Scanner(System.in);

        // Dummy Data
        recursoRepo.guardar(new Libro("1", "El Quijote", "Cervantes", 1605, "Clasico"));
        socioRepo.guardar(new Alumno(new SocioDTO("Juan", "Perez", "1", "juan@mail.com")));
        socioRepo.guardar(new Alumno(new SocioDTO("Tobias", "Tkaczek", "2", "tobias@mail.com")));

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== BiblioTech ===");
            System.out.println("1. Realizar prestamo");
            System.out.println("2. Devolver libro");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("DNI del socio: ");
                    String dni = scanner.nextLine();
                    try {
                        service.realizarPrestamo(isbn, dni);
                        System.out.println("Prestamo realizado!");
                    } catch (BibliotecaException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("DNI del socio: ");
                    String dni = scanner.nextLine();
                    try {
                        service.devolverLibro(isbn, dni);
                        System.out.println("Devolucion registrada!");
                    } catch (BibliotecaException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 3 -> continuar = false;
                default -> System.out.println("Opcion invalida");
            }
        }
        scanner.close();
    }
}