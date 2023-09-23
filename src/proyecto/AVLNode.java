package proyecto;

public class AVLNode {
	
	Libro libro;
	int altura;
	AVLNode izquierdo;
	AVLNode derecho;
	
	
	public AVLNode(Libro libro) {
		super();
		this.libro = libro;
		this.altura = 1;
		this.izquierdo = null;
		this.derecho = null;
	}
	

}
