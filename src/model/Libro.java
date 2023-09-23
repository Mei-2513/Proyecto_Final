package model;

public class Libro {
	
	String titulo;
	String ISBN;
	int volumen;
	String editorial;
	Autor autor;
	Sede sede;
	int cantidadCopias;
	
	
	public Libro(String titulo, String iSBN, int volumen, String editorial, Autor autor, Sede sede,
			int cantidadCopias) {
	
		this.titulo = titulo;
		ISBN = iSBN;
		this.volumen = volumen;
		this.editorial = editorial;
		this.autor = autor;
		this.sede = sede;
		this.cantidadCopias = cantidadCopias;
	}
	
	

}
