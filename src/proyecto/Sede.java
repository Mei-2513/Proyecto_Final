package proyecto;

import java.util.HashMap;
import java.util.Map;


public class Sede {
 private String nombre;
 private String campus;
 private Map<String, Libro> libros = new HashMap<>();
 private AVLTree arbolLibrosSede = new AVLTree(); 
 

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

    

    public String listarLibros() {
        StringBuilder sb = new StringBuilder();
        for (Libro libro : libros.values()) {
            sb.append("Sede: ").append(nombre).append(", Título: ").append(libro.getTitulo()).append(", ISBN: ").append(libro.getISBN()).append("\n");
        }
        return sb.toString();
    }



   

	public int getCantidadLibrosDisponibles() {
        int cantidad = 0;
        for (Libro libro : libros.values()) {
            cantidad += libro.getCantidadCopias();
        }
        return cantidad;
    }

    
    public void agregarLibro(Libro libro) {
        libros.put(libro.getISBN(), libro);
        arbolLibrosSede.insertar(libro);
    }

    public boolean eliminarLibro(String ISBN) {
        Libro libro = libros.get(ISBN);

        if (libro != null) {
            libros.remove(ISBN);
            arbolLibrosSede.eliminar(ISBN);
            return true; 
        }
        return false; 
    }

    public Libro buscarLibro(String nombre, String ISBN) {
        for (Libro libro : libros.values()) {
            if (libro.getTitulo().equals(nombre) && libro.getISBN().equals(ISBN)) {
                return libro;
            }
        }
        return null; 
    }
    
    public boolean existeLibroConISBN(String isbn) {
        for (Libro libro : libros.values()) {
            if (libro.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }




	
}



