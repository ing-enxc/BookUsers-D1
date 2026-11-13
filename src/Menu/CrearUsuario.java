package Menu;

import javax.swing.*;
import java.io.File;

public class CrearUsuario extends JFrame {

    //Las variables de la clase JFrame
    private JLabel titulo;
    private JPanel panel_r;
    private JLabel tipo_de_id;
    private JLabel num_de_id;
    private JLabel primer_nombre;
    private JLabel segundo_nombre;
    private JLabel primer_apellido;
    private JLabel segundo_apellido;
    private JLabel contraseña;
    private JLabel telefono;
    private JLabel email;
    private JLabel foto;
    private JTextField ingresar_num_de_id;
    private JTextField ingresar_primer_nomb;
    private JTextField ingresar_segund_nomb;
    private JTextField ingresar_primer_apell;
    private JTextField ingresar_segund_apell;
    private JPasswordField ingresar_contraseña;
    private JTextField ingresar_telefono;
    private JTextField ingresar_email;
    private JComboBox ingresar_tipo_de_id;
    private JButton subir_foto;


    //Método constructor
    public CrearUsuario() {

        //Unos ajustes de la ventana
        setLocationRelativeTo(null);
        setTitle("Ventana Principal");
        setContentPane(panel_r);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        //EL botonsito para subir la foto
        subir_foto.addActionListener(e -> {

            //Esto es eel filechooser
            JFileChooser fileChooser = new JFileChooser();

            //Esta es la ventanita para seleccionar la foto
            int resultado = fileChooser.showOpenDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
            }
        });
    }

    public static void main(String[] args) {
        new CrearUsuario().setVisible(true);
    }

}
