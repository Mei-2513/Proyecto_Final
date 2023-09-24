package proyecto;

public class Autor {
	
	String nombre;
	String apellido;
	String biografia;
	
	
	public Autor(String nombre, String apellido, String biografia) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.biografia = biografia;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getBiografia() {
		return biografia;
	}


	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	
	

}
