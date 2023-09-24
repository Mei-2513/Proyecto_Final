package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaApp extends JFrame {

    private Biblioteca biblioteca = Biblioteca.getInstancia();
    private JTextArea resultadoTextArea;
    private JTextField nombreSedeField = new JTextField(20);

    public BibliotecaApp() {
        setTitle("Biblioteca App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton agregarLibroButton = new JButton("Agregar Libro");
        JButton eliminarLibroButton = new JButton("Eliminar Libro");
        JButton buscarLibroButton = new JButton("Buscar Libro");
        JButton listarLibrosButton = new JButton("Listar Libros");
        JButton listarSedesButton = new JButton("Listar Sedes");
        JButton listarLibrosEnSedeButton = new JButton("Listar Libros en Sede");

        panel.add(agregarLibroButton);
        panel.add(eliminarLibroButton);
        panel.add(buscarLibroButton);
        panel.add(listarLibrosButton);
        panel.add(listarSedesButton);
        panel.add(listarLibrosEnSedeButton);

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
                    inputPanel.setLayout(new GridLayout(12, 2)); 
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
                    inputPanel.add(nombreSedeField); // Campo para ingresar el nombre de la sede
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
                        int cantidadCopias = Integer.parseInt(cantidadCopiasField.getText());

                        Autor autor = new Autor(nombreAutor, apellidoAutor, biografiaAutor);
                        String nombreSede = nombreSedeField.getText();
                        Sede sede = biblioteca.getSede(nombreSede);

                        if (sede != null) {
                            Libro libro = new Libro(titulo, isbn, volumen, editorial, autor, sede, cantidadCopias);
                            biblioteca.agregarLibro(libro);
                            resultadoTextArea.setText("Libro agregado con éxito.");
                        } else {
                            resultadoTextArea.setText("La sede especificada no existe.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    resultadoTextArea.setText("Error: Ingresa valores numéricos válidos.");
                } catch (Exception ex) {
                    resultadoTextArea.setText("Error inesperado: " + ex.getMessage());
                }
            }
        });

        eliminarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextField isbnField = new JTextField(20);
                    JTextField nombreSedeField = new JTextField(20);

                    JPanel inputPanel = new JPanel();
                    inputPanel.setLayout(new GridLayout(2, 2));
                    inputPanel.add(new JLabel("ISBN del libro:"));
                    inputPanel.add(isbnField);
                    inputPanel.add(new JLabel("Nombre de la sede:"));
                    inputPanel.add(nombreSedeField);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Eliminar Libro",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String ISBN = isbnField.getText();
                        String nombreSede = nombreSedeField.getText();

                        biblioteca.eliminarLibro(ISBN, nombreSede);
                        resultadoTextArea.setText("Libro eliminado con éxito.");
                    }
                } catch (Exception ex) {
                    resultadoTextArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        buscarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextField nombreLibroField = new JTextField(20);
                    JTextField isbnLibroField = new JTextField(20);
                    JTextField nombreSedeField = new JTextField(20);

                    JPanel inputPanel = new JPanel();
                    inputPanel.setLayout(new GridLayout(3, 2));
                    inputPanel.add(new JLabel("Nombre del libro:"));
                    inputPanel.add(nombreLibroField);
                    inputPanel.add(new JLabel("ISBN del libro:"));
                    inputPanel.add(isbnLibroField);
                    inputPanel.add(new JLabel("Nombre de la sede:"));
                    inputPanel.add(nombreSedeField);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Buscar Libro",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String nombreLibro = nombreLibroField.getText();
                        String isbnLibro = isbnLibroField.getText();
                        String nombreSede = nombreSedeField.getText();

                        Libro libro = biblioteca.buscarLibro(nombreLibro, isbnLibro, nombreSede);

                        if (libro != null) {
                            resultadoTextArea.setText("Información del libro:\n" +
                                    "Título: " + libro.getTitulo() + "\n" +
                                    "ISBN: " + libro.getISBN() + "\n" +
                                    "Volumen: " + libro.getVolumen() + "\n" +
                                    "Editorial: " + libro.getEditorial() + "\n" +
                                    "Nombre del Autor: " + libro.getAutor().getNombre() + "\n" +
                                    "Apellido del Autor: " + libro.getAutor().getApellido() + "\n" +
                                    "Biografía del Autor: " + libro.getAutor().getBiografia() + "\n" +
                                    "Cantidad de Copias Disponibles: " + libro.getCantidadCopias());
                        } else {
                            resultadoTextArea.setText("El libro no se encuentra en la sede especificada.");
                        }
                    }
                } catch (Exception ex) {
                    resultadoTextArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        listarLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String resultado = biblioteca.listarLibros();
                    resultadoTextArea.setText(resultado);
                } catch (Exception ex) {
                    resultadoTextArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        listarSedesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String resultado = biblioteca.listarSedes();
                    resultadoTextArea.setText(resultado);
                } catch (Exception ex) {
                    resultadoTextArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        listarLibrosEnSedeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreSede = nombreSedeField.getText();
                    String resultado = biblioteca.listarLibrosEnSede(nombreSede);
                    resultadoTextArea.setText(resultado);
                } catch (Exception ex) {
                    resultadoTextArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        resultadoTextArea = new JTextArea(10, 40);
        resultadoTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);

        add(panel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

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
