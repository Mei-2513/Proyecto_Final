package proyecto;

public class Libro {
	
	String titulo;
	String ISBN;
	int volumen;
	String editorial;
	Autor autor;
	Sede sede;
	int cantidadCopias;
	
	
	public Libro(String titulo, String ISBN, int volumen, String editorial, Autor autor, Sede sede, int cantidadCopias) {
	    this.titulo = titulo;
	    this.ISBN = ISBN;
	    this.volumen = volumen;
	    this.editorial = editorial;
	    this.autor = autor;
	    this.sede = sede; // Ahora acepta una instancia de Sede
	    this.cantidadCopias = cantidadCopias;
	}






	
	
	
	

}
