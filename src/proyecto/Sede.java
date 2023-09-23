package proyecto;

import java.util.HashMap;
import java.util.Map;

public class Sede {
	
	String nombre;
	String campus;
	Map<String, Libro> libros= new HashMap<>();
	
	
	public Sede(String nombre, String campus) {
	
		this.nombre = nombre;
		this.campus = campus;
	}
	
	public void agregarLibro(Libro libro) {
		 libros.put(libro.ISBN, libro);
		
	}
	
	public void eliminarLibro(Libro libro) {
		libros.remove(libro.ISBN);
		
	}
	
	public void listarLibros() {
		 System.out.println("Libros en la Sede " + nombre + " (" + campus + "):");
         for (Libro libro : libros.values()) {
             System.out.println("Título: " + libro.titulo + ", ISBN: " + libro.ISBN);
         }
     }
}
