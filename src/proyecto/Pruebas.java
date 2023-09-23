package proyecto;

public class Pruebas {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.agregarSede("Tunja", "Campus Facultad de Medicina");

        biblioteca.agregarLibro("Libro 1", "ISBN123", 1, "Editorial 1", "Autor 1", "Apellido 1", "Biografía 1", "Tunja", 10);
        biblioteca.agregarLibro("Libro 2", "ISBN456", 2, "Editorial 2", "Autor 2", "Apellido 2", "Biografía 2", "Tunja", 5);

        biblioteca.listarLibros();

        biblioteca.listarLibrosEnSede("Tunja");

        Libro libroEncontrado = biblioteca.buscarLibro("Libro 1", "", "Tunja");
        if (libroEncontrado != null) {
            System.out.println("Libro encontrado: " + libroEncontrado.titulo);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
}

