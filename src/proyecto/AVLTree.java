package proyecto;

public class AVLTree {
	
	private AVLNode raiz;
	
	public AVLTree() {
        raiz = null;
    }
	
	private int altura(AVLNode nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }
	
	private int balanceFactor(AVLNode nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.izquierdo) - altura(nodo.derecho);
    }
	
	private void actualizarAltura(AVLNode nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        }
    }
	
	private AVLNode rotarIzquierda(AVLNode y) {
        AVLNode x = y.derecho;
        AVLNode T2 = x.izquierdo;

        x.izquierdo = y;
        y.derecho = T2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }
	
	private AVLNode rotarDerecha(AVLNode x) {
        AVLNode y = x.izquierdo;
        AVLNode T2 = y.derecho;

        y.derecho = x;
        x.izquierdo = T2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }
	private AVLNode encontrarMinimo(AVLNode nodo) {
        if (nodo == null) {
            return null;
        }

        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }

        return nodo;
    }
	
	public void insertar(Libro libro) {
        raiz = insertar(raiz, libro);
    }
	
	 private AVLNode insertar(AVLNode nodo, Libro libro) {
	        if (nodo == null) {
	            return new AVLNode(libro);
	        }

	        if (libro.ISBN.compareTo(nodo.libro.ISBN) < 0) {
	            nodo.izquierdo = insertar(nodo.izquierdo, libro);
	        } else if (libro.ISBN.compareTo(nodo.libro.ISBN) > 0) {
	            nodo.derecho = insertar(nodo.derecho, libro);
	        } else {
	            
	            return nodo;
	        }

	        actualizarAltura(nodo);

	        int balance = balanceFactor(nodo);

	        
	        if (balance > 1 && libro.ISBN.compareTo(nodo.izquierdo.libro.ISBN) < 0) {
	            return rotarDerecha(nodo);
	        }
	        if (balance < -1 && libro.ISBN.compareTo(nodo.derecho.libro.ISBN) > 0) {
	            return rotarIzquierda(nodo);
	        }
	        if (balance > 1 && libro.ISBN.compareTo(nodo.izquierdo.libro.ISBN) > 0) {
	            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
	            return rotarDerecha(nodo);
	        }
	        if (balance < -1 && libro.ISBN.compareTo(nodo.derecho.libro.ISBN) < 0) {
	            nodo.derecho = rotarDerecha(nodo.derecho);
	            return rotarIzquierda(nodo);
	        }

	        return nodo;
	    }
	 
	 public void eliminar(String ISBN) {
	        raiz = eliminar(raiz, ISBN);
	    }
	 
	 private AVLNode eliminar(AVLNode nodo, String ISBN) {
	        if (nodo == null) {
	            return nodo;
	        }

	        if (ISBN.compareTo(nodo.libro.ISBN) < 0) {
	            nodo.izquierdo = eliminar(nodo.izquierdo, ISBN);
	        } else if (ISBN.compareTo(nodo.libro.ISBN) > 0) {
	            nodo.derecho = eliminar(nodo.derecho, ISBN);
	        } else {
	           

	            
	            if (nodo.izquierdo == null || nodo.derecho == null) {
	                AVLNode temp = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;

	                if (temp == null) {
	                    temp = nodo;
	                    nodo = null;
	                } else {
	                    nodo = temp;
	                }

	                temp = null;
	            } else { 
	                AVLNode temp = encontrarMinimo(nodo.derecho);
	                nodo.libro = temp.libro;
	                nodo.derecho = eliminar(nodo.derecho, temp.libro.ISBN);
	            }
	        }

	        if (nodo == null) {
	            return nodo;
	        }

	        actualizarAltura(nodo);

	        int balance = balanceFactor(nodo);

	        if (balance > 1 && balanceFactor(nodo.izquierdo) >= 0) {
	            return rotarDerecha(nodo);
	        }
	        if (balance > 1 && balanceFactor(nodo.izquierdo) < 0) {
	            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
	            return rotarDerecha(nodo);
	        }
	        if (balance < -1 && balanceFactor(nodo.derecho) <= 0) {
	            return rotarIzquierda(nodo);
	        }
	        if (balance < -1 && balanceFactor(nodo.derecho) > 0) {
	            nodo.derecho = rotarDerecha(nodo.derecho);
	            return rotarIzquierda(nodo);
	        }

	        return nodo;
	    }
	 
	 public Libro buscar(String nombre, String ISBN) {
	        return buscar(raiz, nombre, ISBN);
	    }

	    private Libro buscar(AVLNode nodo, String nombre, String ISBN) {
	        if (nodo == null) {
	            return null;
	        }

	        if (nombre.compareTo(nodo.libro.titulo) < 0 || (nombre.equals("") && ISBN.compareTo(nodo.libro.ISBN) < 0)) {
	            return buscar(nodo.izquierdo, nombre, ISBN);
	        } else if (nombre.compareTo(nodo.libro.titulo) > 0 || (nombre.equals("") && ISBN.compareTo(nodo.libro.ISBN) > 0)) {
	            return buscar(nodo.derecho, nombre, ISBN);
	        } else {
	            return nodo.libro;
	        }
	    }

	   
	    public String inOrderTraversal() {
	        StringBuilder sb = new StringBuilder();
	        inOrderTraversal(raiz, sb);
	        return sb.toString();
	    }

	    private void inOrderTraversal(AVLNode nodo, StringBuilder sb) {
	        if (nodo != null) {
	            inOrderTraversal(nodo.izquierdo, sb);
	            sb.append("Título: ").append(nodo.libro.getTitulo()).append("\n");
	            inOrderTraversal(nodo.derecho, sb);
	        }
	    }
}