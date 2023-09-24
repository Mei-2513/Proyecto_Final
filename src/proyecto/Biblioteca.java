package proyecto;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    private Map<String, Sede> sedes = new HashMap<>();

    private static Biblioteca instancia = new Biblioteca();

    private Biblioteca() {
        sedes.put("Tunja", new Sede("Tunja", "Campus Facultad Medicina"));
        sedes.put("Duitama", new Sede("Duitama", "Centro Regional"));
    }

    public static Biblioteca getInstancia() {
        return instancia;
    }

    public Sede getSede(String nombreSede) {
        return sedes.get(nombreSede);
    }

    public void agregarSede(String nombre, String campus) {
        sedes.put(nombre, new Sede(nombre, campus));
    }

    public void agregarLibro(Libro libro) {
        String nombreSede = libro.getSede().getNombre();
        Sede sede = getSede(nombreSede);

        if (sede != null) {
            sede.agregarLibro(libro);
        } else {
            throw new IllegalArgumentException("La sede especificada no existe.");
        }
    }

    public void eliminarLibro(String ISBN, String nombreSede) {
        Sede sede = getSede(nombreSede);

        if (sede != null) {
            sede.eliminarLibro(ISBN);
        } else {
            throw new IllegalArgumentException("La sede especificada no existe.");
        }
    }

    public Libro buscarLibro(String nombre, String ISBN, String nombreSede) {
        Sede sede = getSede(nombreSede);

        if (sede != null) {
            return sede.buscarLibro(nombre, ISBN);
        } else {
            throw new IllegalArgumentException("La sede especificada no existe.");
        }
    }

    public String listarLibros() {
        StringBuilder sb = new StringBuilder();
        sb.append("Listado de todos los libros en todas las sedes:\n");
        for (Sede sede : sedes.values()) {
            sb.append(sede.listarLibros());
        }
        return sb.toString();
    }

    public String listarSedes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Listado de todas las sedes:\n");
        for (Sede sede : sedes.values()) {
            sb.append("Nombre: ").append(sede.getNombre()).append(", Campus: ").append(sede.getCampus()).append("\n");
        }
        return sb.toString();
    }

    public String listarLibrosEnSede(String nombreSede) {
        Sede sede = getSede(nombreSede);
        if (sede != null) {
            return sede.listarLibros();
        } else {
            throw new IllegalArgumentException("La sede especificada no existe.");
        }
    }
}

