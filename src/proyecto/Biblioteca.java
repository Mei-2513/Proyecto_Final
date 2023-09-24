package proyecto;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    private AVLTree arbolLibros = new AVLTree();
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
        arbolLibros.insertar(libro);
        Sede sede = libro.getSede();
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

    public String listarLibros() {
        StringBuilder sb = new StringBuilder();
        sb.append("Listado de todos los libros en todas las sedes:\n");
        sb.append(arbolLibros.inOrderTraversal());
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
        Sede sede = sedes.get(nombreSede);
        if (sede != null) {
            StringBuilder resultado = new StringBuilder("Listado de libros en la sede " + nombreSede + ":\n");
            resultado.append(sede.listarLibros());
            return resultado.toString();
        } else {
            return "La sede especificada no existe.";
        }
    }
}

