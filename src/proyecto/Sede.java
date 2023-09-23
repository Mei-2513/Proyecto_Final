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
		
	}
	
	public void eliminarLibro(Libro libro) {
		
	}
	
	public void listarLibros() {
		
	}
	
	

}
