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

        agregarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    Libro libro = new Libro(titulo, isbn, volumen, editorial, autor, biblioteca.getSede(nombreSede), cantidadCopias);

                    biblioteca.agregarLibro(libro);
                    JOptionPane.showMessageDialog(null, "Libro agregado con éxito.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(agregarLibroButton);

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


