package proyecto;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    private AVLTree arbolLibros = new AVLTree();
    private Map<String, Sede> sedes = new HashMap<>();

    public Biblioteca() {
        sedes.put("Tunja", new Sede("Tunja", "Campus Facultad Medicina"));
        sedes.put("Duitama", new Sede("Duitama", "Centro Regional"));
    }
    public Sede getSede(String nombreSede) {
        return sedes.get(nombreSede);
    }

    public void agregarSede(String nombre, String campus) {
        sedes.put(campus, new Sede(nombre, campus));
    }

    public void agregarLibro(Libro libro) {
        arbolLibros.insertar(libro);
        Sede sede = libro.sede;
        sede.agregarLibro(libro);
    }

    public void eliminarLibro(String ISBN, String nombreSede) {
        Sede sede = sedes.get(nombreSede);

        if (sede != null) {
            Libro libro = arbolLibros.buscar("", ISBN);
            if (libro != null) {
                arbolLibros.eliminar(ISBN);
                sede.eliminarLibro(libro);
            } else {
                System.out.println("El libro con ISBN " + ISBN + " no existe.");
            }
        } else {
            System.out.println("La sede especificada no existe.");
        }
    }

    public Libro buscarLibro(String nombre, String ISBN, String nombreSede) {
        Sede sede = sedes.get(nombreSede);

        if (sede != null) {
            return arbolLibros.buscar(nombre, ISBN);
        } else {
            System.out.println("La sede especificada no existe.");
            return null;
        }
    }

    public void listarLibros() {
        arbolLibros.inOrderTraversal();
    }

    public void listarLibrosEnSede(String nombreSede) {
        Sede sede = sedes.get(nombreSede);

        if (sede != null) {
            sede.listarLibros();
        } else {
            System.out.println("La sede especificada no existe.");
        }
    }
}