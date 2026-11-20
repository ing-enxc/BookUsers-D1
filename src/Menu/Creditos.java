package Menu;

import javax.swing.*;

public class Creditos extends JFrame{
    private JLabel titulo_creditos;
    private JLabel labeln1;
    private JLabel labelEnoc;
    private JPanel panel_c;

    public Creditos() {
        //Unos ajustes de la ventana
        setLocationRelativeTo(null);
        setTitle("Ventana Principal");
        setContentPane(panel_c);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 300);
    }
}
