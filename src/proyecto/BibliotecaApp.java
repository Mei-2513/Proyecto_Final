package proyecto;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BibliotecaApp extends JFrame {
    private static final long serialVersionUID = 1L;
    private Biblioteca biblioteca = Biblioteca.getInstancia();
    private JTextArea resultadoTextArea;
    private JTextField nombreSedeField = new JTextField(20);
    private Sede sedeActual;
    private JButton listarLibrosEnAmbasSedesButton;

    public BibliotecaApp() {
        setTitle("Biblioteca App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        resultadoTextArea = new JTextArea(10, 40);
        resultadoTextArea.setEditable(false);

        JButton agregarLibroButton = new JButton("Agregar Libro");
        JButton eliminarLibroButton = new JButton("Eliminar Libro");
        JButton buscarLibroButton = new JButton("Buscar Libro");
        JButton listarSedesButton = new JButton("Listar Sedes");
        listarLibrosEnAmbasSedesButton = new JButton("Listar Libros en Ambas Sedes");

        panel.add(agregarLibroButton);
        panel.add(eliminarLibroButton);
        panel.add(buscarLibroButton);
        panel.add(listarSedesButton);    
        panel.add(listarLibrosEnAmbasSedesButton); 

        agregarLibroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAgregarLibro();
            }
        });

        eliminarLibroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleEliminarLibro();
            }
        });

        buscarLibroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleBuscarLibro();
            }
        });

        listarSedesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleListarSedes();
            }
        });

       

        listarLibrosEnAmbasSedesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleListarLibrosEnAmbasSedes();
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

    private void handleAgregarLibro() {
        try {
            JTextField tituloField = new JTextField(20);
            JTextField isbnField = new JTextField(20);
            JTextField volumenField = new JTextField(5);
            JTextField editorialField = new JTextField(20);
            JTextField nombreAutorField = new JTextField(20);
            JTextField apellidoAutorField = new JTextField(20);
            JTextField biografiaAutorField = new JTextField(20);
            JTextField cantidadCopiasField = new JTextField(5);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(12, 2));
            inputPanel.add(new JLabel("Título del libro (solo letras):"));
            inputPanel.add(tituloField);
            inputPanel.add(new JLabel("ISBN del libro (13 dígitos numéricos):"));
            inputPanel.add(isbnField);
            inputPanel.add(new JLabel("Volumen del libro (solo números):"));
            inputPanel.add(volumenField);
            inputPanel.add(new JLabel("Editorial del libro:"));
            inputPanel.add(editorialField);
            inputPanel.add(new JLabel("Nombre del autor (solo letras):"));
            inputPanel.add(nombreAutorField);
            inputPanel.add(new JLabel("Apellido del autor (solo letras):"));
            inputPanel.add(apellidoAutorField);
            inputPanel.add(new JLabel("Biografía del autor (solo letras o años numéricos, ejemplo: 1980):"));
            inputPanel.add(biografiaAutorField);
            inputPanel.add(new JLabel("Nombre de la sede:"));
            inputPanel.add(nombreSedeField);
            inputPanel.add(new JLabel("Cantidad de copias (solo números):"));
            inputPanel.add(cantidadCopiasField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Agregar Libro", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String titulo = tituloField.getText();
                String isbn = isbnField.getText();
                String volumenText = volumenField.getText();
                String editorial = editorialField.getText();
                String nombreAutor = nombreAutorField.getText();
                String apellidoAutor = apellidoAutorField.getText();
                String biografiaAutor = biografiaAutorField.getText();
                String cantidadCopiasText = cantidadCopiasField.getText();

                List<String> errores = new ArrayList<>();

                int cantidadCopias = 0;
                int volumen = 0;

                if (!titulo.matches("^[A-Za-z\\s]+$")) {
                    errores.add("El título debe contener solo letras.");
                }

                if (!isbn.matches("\\d{13}")) {
                    errores.add("El ISBN debe contener 13 dígitos numéricos.");
                }

                if (!volumenText.matches("\\d+")) {
                    errores.add("El volumen debe contener solo números.");
                } else {
                    volumen = Integer.parseInt(volumenText);
                }

                if (!editorial.matches("^[A-Za-z\\s]+$")) {
                    errores.add("La editorial debe contener solo letras y espacios.");
                }

                if (!biografiaAutor.matches("^[A-Za-z\\s\\d\\-]+$")) {
                    errores.add("La biografía del autor debe contener solo letras, números o rangos de años (ejemplo: 1970-2012).");
                }

                if (!cantidadCopiasText.matches("\\d+")) {
                    errores.add("La cantidad de copias debe contener solo números.");
                } else {
                    cantidadCopias = Integer.parseInt(cantidadCopiasText);
                }

                if (titulo.isEmpty() || isbn.isEmpty() || volumenText.isEmpty() || editorial.isEmpty() || nombreAutor.isEmpty() || apellidoAutor.isEmpty() || biografiaAutor.isEmpty() || cantidadCopiasText.isEmpty()) {
                    errores.add("Todos los campos deben ser diligenciados.");
                }

                
                String nombreSede = nombreSedeField.getText();
                if (!nombreSede.equals("Tunja") && !nombreSede.equals("Duitama")) {
                    errores.add("Error: El nombre de la sede debe ser 'Tunja' o 'Duitama'.");
                }

                if (!errores.isEmpty()) {
                    String mensajeError = "Errores:\n";
                    for (String error : errores) {
                        mensajeError += "- " + error + "\n";
                    }
                    resultadoTextArea.setText(mensajeError);
                    return;
                }

                Autor autor = new Autor(nombreAutor, apellidoAutor, biografiaAutor);
                Sede sede = biblioteca.getSede(nombreSede);

                if (sede != null) {
                    if (sede.existeLibroConISBN(isbn)) {
                        resultadoTextArea.setText("Ya existe un libro con el mismo ISBN en esta sede.");
                        return;
                    }

                    Libro libro = new Libro(titulo, isbn, volumen, editorial, autor, sede, cantidadCopias);
                    biblioteca.agregarLibro(libro);
                    resultadoTextArea.setText("Libro agregado con éxito.");
                } else {
                    resultadoTextArea.setText("La sede especificada no existe.");
                }
            }
        } catch (NumberFormatException ex) {
            resultadoTextArea.setText("Error: Deberás usar solo números en campos numéricos.");
        } catch (Exception ex) {
            resultadoTextArea.setText("Error inesperado: " + ex.getMessage());
        }
    }




    private void handleEliminarLibro() {
        try {
            JTextField isbnField = new JTextField(20);
            JTextField nombreSedeField = new JTextField(20);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(4, 2));
            inputPanel.add(new JLabel("ISBN del libro a eliminar:"));
            inputPanel.add(isbnField);
            inputPanel.add(new JLabel("Nombre de la sede (Tunja o Duitama):"));
            inputPanel.add(nombreSedeField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Eliminar Libro", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String isbn = isbnField.getText();
                String nombreSede = nombreSedeField.getText();

                if (!nombreSede.equals("Tunja") && !nombreSede.equals("Duitama")) {
                    resultadoTextArea.setText("Error: El nombre de la sede debe ser 'Tunja' o 'Duitama'.");
                    return;
                }

                boolean eliminado = biblioteca.eliminarLibro(isbn, nombreSede);

                if (eliminado) {
                    resultadoTextArea.setText("Libro eliminado con éxito de la sede " + nombreSede + ".");
                } else {
                    resultadoTextArea.setText("No se encontró ningún libro con el ISBN especificado en la sede " + nombreSede + ".");
                }
            }
        } catch (Exception ex) {
            resultadoTextArea.setText("Error inesperado: " + ex.getMessage());
        }
    }




    private void handleBuscarLibro() {
        try {
            JTextField nombreLibroField = new JTextField(20);
            JTextField isbnLibroField = new JTextField(20);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(2, 2));
            inputPanel.add(new JLabel("Nombre del libro (solo letras):"));
            inputPanel.add(nombreLibroField);
            inputPanel.add(new JLabel("ISBN del libro (13 dígitos numéricos):"));
            inputPanel.add(isbnLibroField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Buscar Libro", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String nombreLibro = nombreLibroField.getText();
                String isbnLibro = isbnLibroField.getText();

                if (nombreLibro.isEmpty() || isbnLibro.isEmpty()) {
                    resultadoTextArea.setText("Error: Todos los campos deben ser diligenciados.");
                    return;
                }

                
                if (!nombreLibro.matches("^[A-Za-z\\s]+$")) {
                    resultadoTextArea.setText("Error: El nombre del libro debe contener solo letras y espacios.");
                    return;
                }

                if (!isbnLibro.matches("\\d{13}")) {
                    resultadoTextArea.setText("Error: El ISBN del libro debe contener exactamente 13 dígitos numéricos.");
                    return;
                }

                
            }
        } catch (Exception ex) {
            resultadoTextArea.setText("Error: " + ex.getMessage());
        }
    }


    private void handleListarLibros() {
        try {
            String resultado = biblioteca.listarLibros();
            resultadoTextArea.setText(resultado);
        } catch (Exception ex) {
            resultadoTextArea.setText("Error: " + ex.getMessage());
        }
    }

    private void handleListarSedes() {
        try {
            String resultado = biblioteca.listarSedes();
            resultadoTextArea.setText(resultado);
        } catch (Exception ex) {
            resultadoTextArea.setText("Error: " + ex.getMessage());
        }
    }



    private void handleListarLibrosEnAmbasSedes() {
        try {
            List<Libro> librosEnAmbasSedes = biblioteca.listarLibrosEnAmbasSedes("Tunja", "Duitama");

            StringBuilder resultado = new StringBuilder();
            resultado.append("Listado de libros en ambas sedes:\n");

            for (Libro libro : librosEnAmbasSedes) {
                resultado.append("Sede: ").append(libro.getSede().getNombre()).append(", Título: ").append(libro.getTitulo()).append(", ISBN: ").append(libro.getISBN()).append("\n");
            }

            resultadoTextArea.setText(resultado.toString());
        } catch (Exception ex) {
            resultadoTextArea.setText("Error: " + ex.getMessage());
        }
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BibliotecaApp();
            }
        });
    }
}
