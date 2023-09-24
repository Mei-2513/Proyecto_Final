package proyecto;

import java.util.ArrayList;

import java.util.List;



public class Sede {
 private String nombre;
 private String campus;
 private List<Libro> libros = new ArrayList<>();
 private AVLTree arbolLibrosSede = new AVLTree(); 
 

 public Sede(String nombre, String campus) {
	    this.nombre = nombre;
	    this.campus = campus;
	    this.libros = new ArrayList<>(); 
	}


    public String getNombre() {
        return nombre;
    }

    public String getCampus() {
        return campus;
    }
    
    public List<Libro> getLibros() {
        return libros;
    }
    

    public String listarLibros() {
        StringBuilder sb = new StringBuilder();
        for (Libro libro : libros) {
            sb.append("Sede: ").append(nombre).append(", Título: ").append(libro.getTitulo()).append(", ISBN: ").append(libro.getISBN()).append("\n");
        }
        return sb.toString();
    }




   

    public int getCantidadLibrosDisponibles() {
        int cantidad = 0;
        for (Libro libro : libros) {
            cantidad += libro.getCantidadCopias();
        }
        return cantidad;
    }

    
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        arbolLibrosSede.insertar(libro);
    }


	 public boolean eliminarLibro(String ISBN) {
		    for (Libro libro : libros) {
		        if (libro.getISBN().equals(ISBN)) {
		            libros.remove(libro);
		            arbolLibrosSede.eliminar(ISBN);
		            return true; 
		        }
		    }
		    return false; 
		}


    public Libro buscarLibro(String nombre, String ISBN) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(nombre) && libro.getISBN().equals(ISBN)) {
                return libro;
            }
        }
        return null; 
    }

    
    public boolean existeLibroConISBN(String isbn) {
        for (Libro libro : libros) {
            if (libro.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }




	
}



