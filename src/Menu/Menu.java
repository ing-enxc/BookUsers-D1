package Menu;

import javax.swing.*;


public class Menu extends JFrame {
    private JPanel panel_p;
    private JLabel titulo;
    private JButton segundo_boton;
    private JButton tercer_boton;
    private JButton primer_boton;
    private JLabel creditos;

    public Menu() {
        setLocationRelativeTo(null);
        setTitle("Ventana Principal");
        setContentPane(panel_p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,500);
    }


    public static void main(String[] args) {
        new Menu().setVisible(true);
    }
}