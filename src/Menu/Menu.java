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

    private final ArrayList<Usuario> listaUsuarios = PersistenciaUsuarios.cargar();
    private final CrearUsuario ventanaCrear = new CrearUsuario(listaUsuarios);

    public Menu() {
        setTitle("Ventana Principal");
        setContentPane(panel_p);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        segundo_boton.addActionListener(e -> {
            ventanaCrear.setLocationRelativeTo(null);
            ventanaCrear.setVisible(true);

        });

        primer_boton.addActionListener(e -> {
            VerUsuarios ventanaVer = new VerUsuarios(listaUsuarios);
            ventanaVer.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }
}