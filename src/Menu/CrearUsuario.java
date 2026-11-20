package Menu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import static Menu.Usuario.generarUsername;

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

    // Atributo en CrearUsuario:
    private Usuario usuarioEnEdicion = null;
    private Runnable callbackAlGuardar = null;

    public void cargarParaEditar(Usuario u, Runnable alGuardar) {
        this.usuarioEnEdicion = u;
        this.callbackAlGuardar = alGuardar;

        setTitle("Editar Usuario - " + u.getId());
        boton_d_registrar.setText("Guardar Cambios");

        ingresar_tipo_de_id.setSelectedItem(u.getTipodid());
        ingresar_num_de_id.setText(u.getNdid());
        ingresar_primer_nomb.setText(u.getPnombre());
        ingresar_segund_nomb.setText(u.getSnombre());
        ingresar_primer_apell.setText(u.getPapellido());
        ingresar_segund_apell.setText(u.getSapellido());
        ingresar_contraseña.setText(u.getContraseña());
        ingresar_telefono.setText(u.getTelefono());
        ingresar_email.setText(u.getCorreo());

        if (u.getRutafoto() != null && !u.getRutafoto().isEmpty()) {
            File archivoFoto = new File(u.getRutafoto());
            if (archivoFoto.exists()) {
                fotoSeleccionada = archivoFoto;
                ImageIcon iconoOriginal = new ImageIcon(archivoFoto.getAbsolutePath());
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                icon.setIcon(new ImageIcon(imagenEscalada));
                icon.setText("");
            }
        }

        boton_d_registrar.setEnabled(true);
    }

    //Contador
    private int contador = 1;

    //Metodo del id
    public String generarSiguienteId() {
        String idFormateado = String.format("%04d", contador);
        contador++;
        return idFormateado;
    }

    //Array
    private ArrayList<Usuario> listaCrearusuario = new ArrayList<>();

    public ArrayList<Usuario> getArray() {
        return listaCrearusuario;
    }

    public void agregarArray(Usuario usuario){
        listaCrearusuario.add(usuario);
    }

    //Variable para validar si se subió una foto
    private File fotoSeleccionada = null;

    //Metodo constructor
    public CrearUsuario(ArrayList<Usuario> listaUsuarios) {

        this.listaCrearusuario = (listaUsuarios != null) ? listaUsuarios : new ArrayList<>();
        this.contador = this.listaCrearusuario.size() + 1;

        //Unos ajustes de la ventana
        setLocationRelativeTo(null);
        setTitle("Ventana Principal");
        setContentPane(panel_r);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);

        //Este es el menusito ese para seleccionar los tipos de documentos
        ingresar_tipo_de_id.addItem(" -- Seleccionar documento -- ");
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
        ingresar_tipo_de_id.addActionListener(e -> validar.run());
        ingresar_num_de_id.getDocument().addDocumentListener(validadorCampos);
        ingresar_primer_nomb.getDocument().addDocumentListener(validadorCampos);
        ingresar_primer_apell.getDocument().addDocumentListener(validadorCampos);
        ingresar_segund_apell.getDocument().addDocumentListener(validadorCampos);
        ingresar_contraseña.getDocument().addDocumentListener(validadorCampos);
        ingresar_telefono.getDocument().addDocumentListener(validadorCampos);
        ingresar_email.getDocument().addDocumentListener(validadorCampos);

        //Arreglamos el label para el icon
        icon.setPreferredSize(new Dimension(90, 90));
        icon.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

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

                //Acá mostramos el icon
                ImageIcon iconoOriginal = new ImageIcon(fotoSeleccionada.getAbsolutePath());

                // 2. Redimensionar la imagen para que encaje en el JLabel (ej: 280x280)
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

                // 3. Asignar la imagen redimensionada al JLabel y quitar el texto
                icon.setIcon(new ImageIcon(imagenEscalada));
                icon.setText("");

                //Acá validamos que se haya adjuntdo la foto
                validar.run();
            }
        });

        //Esto es para que al presionar el boton de registrar se guarden los datos en la array
        boton_d_registrar.addActionListener( e -> {
            String tipodeidcap = String.valueOf(ingresar_tipo_de_id.getSelectedItem());
            String ndidcap = ingresar_num_de_id.getText().trim();
            String pnombrecap = ingresar_primer_nomb.getText().trim();
            String snombrecap = ingresar_segund_nomb.getText().trim();
            String papellidocap = ingresar_primer_apell.getText().trim();
            String sapellidocap = ingresar_segund_apell.getText().trim();
            String contraseñacap = ingresar_contraseña.getText().trim();
            String correocap = ingresar_email.getText().trim();
            String telefonocap = ingresar_telefono.getText().trim();
            String rutafotocap = "";
            String usernamecap = generarUsername(pnombrecap, papellidocap, sapellidocap);
            String idcap = generarSiguienteId();

            try {
                // 1. CREAR LA CARPETA LOCALMENTE (Evita cualquier conflicto global)
                File carpetaDestino = new File("Fotos");
                if (!carpetaDestino.exists()) {
                    carpetaDestino.mkdir();
                }

                // 2. CREAR EL DESTINO USANDO LA CARPETA Y EL NOMBRE DE LA FOTO SELECCIONADA
                File archivoDestino = new File(carpetaDestino, fotoSeleccionada.getName());

                // 3. MOVER / COPIAR EL ARCHIVO FÍSICAMENTE
                Files.copy(fotoSeleccionada.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Obtener la ruta absoluta lista para guardar
                String rutaFoto = archivoDestino.getAbsolutePath();

                rutafotocap = rutaFoto;

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al mover el archivo: " + ex.getMessage());
            }


            Usuario usuario = new Usuario(tipodeidcap, ndidcap, pnombrecap, snombrecap, papellidocap, sapellidocap, contraseñacap, telefonocap, correocap, rutafotocap, usernamecap, idcap);
            agregarArray(usuario);
            PersistenciaUsuarios.guardar(listaCrearusuario);

            System.out.println("Array: " + getArray());

            JOptionPane.showMessageDialog(this, "¡Usuario registrado con éxito!");
        });
    }
}