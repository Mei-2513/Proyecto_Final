package proyecto;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
	
	private AVLTree arbolLibros= new AVLTree();
	private Map<String, Sede> sedes = new HashMap<>();
	
	

	public Biblioteca() {
		
		sedes.put(null, null);
		sedes.put(null, null);
		
	}



	public static void main(String[] args) {
		

	}

}
