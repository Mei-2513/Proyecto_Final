package proyecto;

import java.util.HashMap;
import java.util.Map;

public class Sede {
    private String nombre;
    private String campus;
    private Map<String, Libro> libros = new HashMap<>();

    public Sede(String nombre, String campus) {
        this.nombre = nombre;
        this.campus = campus;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCampus() {
        return campus;
    }

    public void agregarLibro(Libro libro) {
        libros.put(libro.getISBN(), libro);
    }

    public void eliminarLibro(Libro libro) {
        libros.remove(libro.getISBN());
    }

    public String listarLibros() {
        StringBuilder sb = new StringBuilder();
        for (Libro libro : libros.values()) {
            sb.append("Título: ").append(libro.getTitulo()).append(", ISBN: ").append(libro.getISBN()).append("\n");
        }
        return sb.toString();
    }
}



