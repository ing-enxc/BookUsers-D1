package Menu;

import javax.swing.*;
import java.util.ArrayList;

public class Menu extends JFrame {
    private JPanel panel_p;
    private JLabel titulo;
    private JButton segundo_boton;
    private JButton tercer_boton;
    private JButton primer_boton;
    private JLabel creditos;
    private final ArrayList<Usuario> listaUsuarios = PersistenciaUsuarios.cargar();//Este carga la lista guardada del usuarios.dat
    private final CrearUsuario ventanaCrear = new CrearUsuario(listaUsuarios);

    //Metodo para crear la ventana
    public Menu() {
        //Ajustes necesarios
        setTitle("Ventana Principal");
        setContentPane(panel_p);
        setSize(700, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Dispose para que

        //ActionListener para la ventana CrearUsuario
        segundo_boton.addActionListener(e -> {
            ventanaCrear.setLocationRelativeTo(null);
            ventanaCrear.setVisible(true);

        });

        //ActionListener para la ventana VerUsuarios
        primer_boton.addActionListener(e -> {
            VerUsuarios ventanaVer = new VerUsuarios(listaUsuarios);
            ventanaVer.setVisible(true);
        });
    }

    //Main que crea la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }
}