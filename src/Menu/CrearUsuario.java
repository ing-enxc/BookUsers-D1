package Menu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
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

    //Getters y setters
    public JTextField getIngresar_num_de_id() {
        return ingresar_num_de_id;
    }

    public void setIngresar_num_de_id(JTextField ingresar_num_de_id) {
        this.ingresar_num_de_id = ingresar_num_de_id;
    }

    public JTextField getIngresar_primer_nomb() {
        return ingresar_primer_nomb;
    }

    public void setIngresar_primer_nomb(JTextField ingresar_primer_nomb) {
        this.ingresar_primer_nomb = ingresar_primer_nomb;
    }

    public JTextField getIngresar_segund_nomb() {
        return ingresar_segund_nomb;
    }

    public void setIngresar_segund_nomb(JTextField ingresar_segund_nomb) {
        this.ingresar_segund_nomb = ingresar_segund_nomb;
    }

    public JTextField getIngresar_primer_apell() {
        return ingresar_primer_apell;
    }

    public void setIngresar_primer_apell(JTextField ingresar_primer_apell) {
        this.ingresar_primer_apell = ingresar_primer_apell;
    }

    public JTextField getIngresar_segund_apell() {
        return ingresar_segund_apell;
    }

    public void setIngresar_segund_apell(JTextField ingresar_segund_apell) {
        this.ingresar_segund_apell = ingresar_segund_apell;
    }

    public JPasswordField getIngresar_contraseña() {
        return ingresar_contraseña;
    }

    public void setIngresar_contraseña(JPasswordField ingresar_contraseña) {
        this.ingresar_contraseña = ingresar_contraseña;
    }

    public JTextField getIngresar_telefono() {
        return ingresar_telefono;
    }

    public void setIngresar_telefono(JTextField ingresar_telefono) {
        this.ingresar_telefono = ingresar_telefono;
    }

    public JTextField getIngresar_email() {
        return ingresar_email;
    }

    public void setIngresar_email(JTextField ingresar_email) {
        this.ingresar_email = ingresar_email;
    }

    private JTextField ingresar_segund_nomb;
    private JTextField ingresar_primer_apell;
    private JTextField ingresar_segund_apell;
    private JPasswordField ingresar_contraseña;
    private JTextField ingresar_telefono;
    private JTextField ingresar_email;
    private JComboBox ingresar_tipo_de_id;
    private JButton subir_foto;
    private JButton boton_d_registrar;
    private JButton limpiarButton;

    //Variable para validar si se subió una foto
    private File fotoSeleccionada = null;

    //Metodo constructor
    public CrearUsuario() {

        //Unos ajustes de la ventana
        setLocationRelativeTo(null);
        setTitle("Ventana Principal");
        setContentPane(panel_r);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);


        //Este es el menusito ese para seleccionar los tipos de documentos
        ingresar_tipo_de_id.addItem(" ");
        ingresar_tipo_de_id.addItem("Registro civil de nacimiento");
        ingresar_tipo_de_id.addItem("Cédula de ciudadanía");
        ingresar_tipo_de_id.addItem("Tarjeta de identidad");
        ingresar_tipo_de_id.addItem("Permiso especial de permanencia");
        ingresar_tipo_de_id.addItem("Permiso por protección temporal");
        ingresar_tipo_de_id.addItem("Cédula de extranjería");
        ingresar_tipo_de_id.addItem("Pasaporte");
        ingresar_tipo_de_id.addItem("Documento de identificación extranjero");
        ingresar_tipo_de_id.addItem("NIUP");
        ingresar_tipo_de_id.addItem("No tiene");

        //ActionListener para registrar los usuarios
        boton_d_registrar.setEnabled(false);

        Runnable validar = () -> {
            // Verificamos que ninguno de los campos esté vacío (eliminando espacios en blanco)
            boolean campo1Lleno = !ingresar_num_de_id.getText().trim().isEmpty();
            boolean campo2Lleno = !ingresar_primer_nomb.getText().trim().isEmpty();
            boolean campo3Lleno = !ingresar_primer_apell.getText().trim().isEmpty();
            boolean campo4Lleno = !ingresar_segund_apell.getText().trim().isEmpty();
            boolean campo5Lleno = !ingresar_contraseña.getText().trim().isEmpty();
            boolean campo6Lleno = !ingresar_telefono.getText().trim().isEmpty();
            boolean campo7Lleno = !ingresar_email.getText().trim().isEmpty();
            boolean campo8Lleno = (fotoSeleccionada != null);
            boolean comboValido = ingresar_tipo_de_id.getSelectedIndex() > 0;

            // El botón solo se activa si TODOS los campos tienen contenido
            boton_d_registrar.setEnabled(campo1Lleno && campo2Lleno && campo3Lleno && campo4Lleno && campo5Lleno && campo6Lleno && campo7Lleno && campo8Lleno && comboValido);
        };

        DocumentListener validadorCampos = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {validar.run(); }
            @Override
            public void removeUpdate(DocumentEvent e) { validar.run(); }
            @Override
            public void changedUpdate(DocumentEvent e) { validar.run(); }
        };

        ingresar_primer_nomb.getDocument().addDocumentListener(validadorCampos);
        ingresar_primer_apell.getDocument().addDocumentListener(validadorCampos);
        ingresar_segund_apell.getDocument().addDocumentListener(validadorCampos);
        ingresar_contraseña.getDocument().addDocumentListener(validadorCampos);
        ingresar_telefono.getDocument().addDocumentListener(validadorCampos);
        ingresar_num_de_id.getDocument().addDocumentListener(validadorCampos);
        ingresar_email.getDocument().addDocumentListener(validadorCampos);
        ingresar_primer_nomb.getDocument().addDocumentListener(validadorCampos);

        //EL botonsito para subir la foto
        subir_foto.addActionListener(e -> {

            //Esto es eel filechooser
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filtroTexto = new FileNameExtensionFilter("Archivos de imagen (*.PNG, JPG)", "JPG", "PNG");
            fileChooser.setFileFilter(filtroTexto);
            fileChooser.setAcceptAllFileFilterUsed(false);

            //Esta es la ventanita para seleccionar la foto
            int resultado = fileChooser.showOpenDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File fotoSeleccionada = fileChooser.getSelectedFile();
                validar.run();
            }
        });

        ingresar_tipo_de_id.addActionListener(e -> {
            validar.run();
        });


    }

    public static void main(String[] args) {
        new CrearUsuario().setVisible(true);
    }
}