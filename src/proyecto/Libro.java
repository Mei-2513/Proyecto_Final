package proyecto;

public class Libro {
	
	String titulo;
	String ISBN;
	int volumen;
	String editorial;
	Autor autor;
	int cantidadCopias;
	private Sede sede; 

    public Libro(String titulo, String ISBN, int volumen, String editorial, Autor autor, Sede sede, int cantidadCopias) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.volumen = volumen;
        this.editorial = editorial;
        this.autor = autor;
        this.sede = sede; 
        this.cantidadCopias = cantidadCopias;
    }
	
	public Sede getSede() {
        return sede;
    }


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public int getVolumen() {
		return volumen;
	}


	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}


	


	public void setSede(Sede sede) {
		this.sede = sede;
	}


	public int getCantidadCopias() {
		return cantidadCopias;
	}


	public void setCantidadCopias(int cantidadCopias) {
		this.cantidadCopias = cantidadCopias;
	}
	
	@Override
	public String toString() {
	    return "Título: " + titulo + "\n" +
	           "ISBN: " + ISBN + "\n" +
	           "Volumen: " + volumen + "\n" +
	           "Editorial: " + editorial + "\n" +
	           "Autor: " + autor.getNombre() + " " + autor.getApellido() + "\n" +
	           "Biografía del Autor: " + autor.getBiografia() + "\n" +
	           "Cantidad de Copias: " + cantidadCopias + "\n";
	}

	

}