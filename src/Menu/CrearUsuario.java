package Menu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
    private JButton boton_d_registrar;
    private JButton limpiarButton;
    private JLabel icon;

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
            boolean comboValido = ingresar_tipo_de_id.getSelectedIndex() > 0;
            boolean campo1Lleno = !ingresar_num_de_id.getText().trim().isEmpty();
            boolean campo2Lleno = !ingresar_primer_nomb.getText().trim().isEmpty();
            boolean campo3Lleno = !ingresar_primer_apell.getText().trim().isEmpty();
            boolean campo4Lleno = !ingresar_segund_apell.getText().trim().isEmpty();
            boolean campo5Lleno = !ingresar_contraseña.getText().trim().isEmpty();
            boolean campo6Lleno = !ingresar_telefono.getText().trim().isEmpty();
            boolean campo7Lleno = !ingresar_email.getText().trim().isEmpty();
            boolean campo8Lleno = (fotoSeleccionada != null);

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

        //Aplicando el documentlistener a los textfields
        ingresar_tipo_de_id.addActionListener(e -> {
            validar.run();
        });
        ingresar_num_de_id.getDocument().addDocumentListener(validadorCampos);
        ingresar_primer_nomb.getDocument().addDocumentListener(validadorCampos);
        ingresar_primer_apell.getDocument().addDocumentListener(validadorCampos);
        ingresar_segund_apell.getDocument().addDocumentListener(validadorCampos);
        ingresar_contraseña.getDocument().addDocumentListener(validadorCampos);
        ingresar_telefono.getDocument().addDocumentListener(validadorCampos);
        ingresar_email.getDocument().addDocumentListener(validadorCampos);

        //EL botonsito para subir la foto
        subir_foto.addActionListener(e -> {

            //Esto es eel filechooser
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filtroTexto = new FileNameExtensionFilter("Archivos de imagen (*.png, jpg)", "jpg", "png");
            fileChooser.setFileFilter(filtroTexto);
            fileChooser.setAcceptAllFileFilterUsed(false);

            //Esta es la ventanita para seleccionar la foto
            int resultado = fileChooser.showOpenDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                fotoSeleccionada = fileChooser.getSelectedFile();
                //Acá validamos que se haya adjuntdo la foto
                validar.run();
            }
        });

        //Instanciamos la clase
        Usuario gestor = new Usuario();

        //Esto es para que al presionar el boton de registrar se guarden los datos en la array
        boton_d_registrar.addActionListener( e -> {
            String tipodeidcap = String.valueOf(ingresar_tipo_de_id.getSelectedItem());
            String ndidcap = ingresar_primer_nomb.getText().trim();
            String pnombrecap = ingresar_primer_nomb.getText().trim();
            String snombrecap = ingresar_segund_nomb.getText().trim();
            String papellidocap = ingresar_primer_apell.getText().trim();
            String sapellidocap = ingresar_segund_apell.getText().trim();
            String contraseñacap = ingresar_contraseña.getText().trim();
            String correocap = ingresar_email.getText().trim();
            String telefonocap = ingresar_telefono.getText().trim();

            //Acá atrapamos la foto y la ubicamos
            File fotoCapturada = fotoSeleccionada;

            try {
                //Esto es para obtener la foto
                File carpetaDestino = new File("Fotos");

                if (!carpetaDestino.exists()) {
                    carpetaDestino.mkdir();
                }

                if (!carpetaDestino.isDirectory()) {
                    throw new IOException("La ruta de destino no es un directorio válido.");
                }

                File archivoDestino = new File(carpetaDestino, fotoSeleccionada.getName());

                Files.copy(fotoSeleccionada.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                String rutaFoto = archivoDestino.getAbsolutePath();

                //Esto es para empaquetar y guardar
                Registro usuarioCreado = new Registro(tipodeidcap, ndidcap, pnombrecap, snombrecap, papellidocap, sapellidocap, contraseñacap, telefonocap, correocap, rutaFoto);

                //Metodo agregar a la array
                gestor.agregarArray(usuarioCreado);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al mover el archivo: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        new CrearUsuario().setVisible(true);
    }
}