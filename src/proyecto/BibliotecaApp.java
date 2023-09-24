package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaApp extends JFrame {

    private Biblioteca biblioteca = new Biblioteca();

    public BibliotecaApp() {
        setTitle("Biblioteca App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JButton agregarLibroButton = new JButton("Agregar Libro");
        JButton eliminarLibroButton = new JButton("Eliminar Libro");
        JButton buscarLibroButton = new JButton("Buscar Libro");
        JButton listarLibrosButton = new JButton("Listar Libros");
        JButton listarLibrosEnSedeButton = new JButton("Listar Libros en Sede");

        agregarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextField tituloField = new JTextField(20);
                    JTextField isbnField = new JTextField(20);
                    JTextField volumenField = new JTextField(5);
                    JTextField editorialField = new JTextField(20);
                    JTextField nombreAutorField = new JTextField(20);
                    JTextField apellidoAutorField = new JTextField(20);
                    JTextField biografiaAutorField = new JTextField(20);
                    JTextField nombreSedeField = new JTextField(20);
                    JTextField cantidadCopiasField = new JTextField(5);

                    JPanel inputPanel = new JPanel();
                    inputPanel.setLayout(new GridLayout(9, 2));
                    inputPanel.add(new JLabel("Título del libro:"));
                    inputPanel.add(tituloField);
                    inputPanel.add(new JLabel("ISBN del libro:"));
                    inputPanel.add(isbnField);
                    inputPanel.add(new JLabel("Volumen del libro:"));
                    inputPanel.add(volumenField);
                    inputPanel.add(new JLabel("Editorial del libro:"));
                    inputPanel.add(editorialField);
                    inputPanel.add(new JLabel("Nombre del autor:"));
                    inputPanel.add(nombreAutorField);
                    inputPanel.add(new JLabel("Apellido del autor:"));
                    inputPanel.add(apellidoAutorField);
                    inputPanel.add(new JLabel("Biografía del autor:"));
                    inputPanel.add(biografiaAutorField);
                    inputPanel.add(new JLabel("Nombre de la sede:"));
                    inputPanel.add(nombreSedeField);
                    inputPanel.add(new JLabel("Cantidad de copias:"));
                    inputPanel.add(cantidadCopiasField);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Agregar Libro",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String titulo = tituloField.getText();
                        String isbn = isbnField.getText();
                        int volumen = Integer.parseInt(volumenField.getText());
                        String editorial = editorialField.getText();
                        String nombreAutor = nombreAutorField.getText();
                        String apellidoAutor = apellidoAutorField.getText();
                        String biografiaAutor = biografiaAutorField.getText();
                        String nombreSede = nombreSedeField.getText();
                        int cantidadCopias = Integer.parseInt(cantidadCopiasField.getText());

                        Autor autor = new Autor(nombreAutor, apellidoAutor, biografiaAutor);
                        Sede sede = biblioteca.getSede(nombreSede);

                        if (sede != null) {
                            Libro libro = new Libro(titulo, isbn, volumen, editorial, autor, sede, cantidadCopias);
                            biblioteca.agregarLibro(libro);
                            JOptionPane.showMessageDialog(null, "Libro agregado con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(null, "La sede especificada no existe.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Ingresa valores numéricos válidos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ISBN = JOptionPane.showInputDialog("Ingrese el ISBN del libro a eliminar:");
                    String nombreSede = JOptionPane.showInputDialog("Ingrese el nombre de la sede:");

                    Sede sede = biblioteca.getSede(nombreSede);

                    if (sede != null) {
                        biblioteca.eliminarLibro(ISBN, nombreSede);
                        JOptionPane.showMessageDialog(null, "Libro eliminado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "La sede especificada no existe.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buscarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del libro (deje en blanco si no es relevante):");
                    String ISBN = JOptionPane.showInputDialog("Ingrese el ISBN del libro (deje en blanco si no es relevante):");
                    String nombreSede = JOptionPane.showInputDialog("Ingrese el nombre de la sede (deje en blanco si no es relevante):");

                    if (nombre == null) nombre = "";
                    if (ISBN == null) ISBN = "";

                    Sede sede = biblioteca.getSede(nombreSede);

                    if (sede != null) {
                        Libro libro = biblioteca.buscarLibro(nombre, ISBN, nombreSede);

                        if (libro != null) {
                            JOptionPane.showMessageDialog(null, "Información del libro:\n" +
                                    "Título: " + libro.titulo + "\n" +
                                    "ISBN: " + libro.ISBN + "\n" +
                                    "Volumen: " + libro.volumen + "\n" +
                                    "Editorial: " + libro.editorial + "\n" +
                                    "Nombre del Autor: " + libro.autor.nombre + "\n" +
                                    "Apellido del Autor: " + libro.autor.apelliddo + "\n" +
                                    "Biografía del Autor: " + libro.autor.biografia + "\n" +
                                    "Cantidad de Copias Disponibles: " + libro.cantidadCopias);
                        } else {
                            JOptionPane.showMessageDialog(null, "El libro no se encuentra en la sede especificada.", "Información",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La sede especificada no existe.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        listarLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null, "Listado de todos los libros en todas las sedes:\n");
                    biblioteca.listarLibros();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        listarLibrosEnSedeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreSede = JOptionPane.showInputDialog("Ingrese el nombre de la sede:");

                    if (nombreSede != null && nombreSede.matches("[a-zA-Z]+")) {
                        Sede sede = biblioteca.getSede(nombreSede);
                        if (sede != null) {
                            JOptionPane.showMessageDialog(null, "Listado de libros en la sede " + nombreSede + ":\n");
                            biblioteca.listarLibrosEnSede(nombreSede);
                        } else {
                            JOptionPane.showMessageDialog(null, "La sede especificada no existe.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre de sede inválido. Ingrese solo letras.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(agregarLibroButton);
        panel.add(eliminarLibroButton);
        panel.add(buscarLibroButton);
        panel.add(listarLibrosButton);
        panel.add(listarLibrosEnSedeButton);

        getContentPane().add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BibliotecaApp();
            }
        });
    }
}

