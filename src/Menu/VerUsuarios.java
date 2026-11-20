package Menu;

import javax.swing.*;
import java.util.ArrayList;

public class VerUsuarios extends JFrame {

    private JPanel panel_vu;
    private JLabel titulo;
    private JList<Usuario> lista;
    private JButton boton_eliminarusuario;
    private JButton boton_pdf;
    private JButton boton_editarusuario;

    private ArrayList<Usuario> listaverusuario = new ArrayList<>();
    private DefaultListModel<Usuario> modelo;

    public VerUsuarios(ArrayList<Usuario> listadeusuarios) {
        this.listaverusuario = (listadeusuarios != null) ? listadeusuarios : new ArrayList<>();

        setTitle("Ver usuarios");
        setContentPane(panel_vu);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarLista();

        if (boton_eliminarusuario != null) {
            boton_eliminarusuario.setText("Eliminar usuario");
            boton_eliminarusuario.addActionListener(e -> eliminarUsuarioSeleccionado());
        }

        if (boton_pdf != null) {
            boton_pdf.setText("Generar Ficha PDF");
            boton_pdf.addActionListener(e -> {
                Usuario seleccionado = lista.getSelectedValue();
                GeneradorPDF.exportarFichaUsuario(seleccionado, this);
            });
        }

        if (boton_editarusuario != null) {
            boton_editarusuario.setText("Editar usuario");
            boton_editarusuario.addActionListener(e -> editarUsuarioSeleccionado());
        }
    }

    private void cargarLista() {
        if (lista == null) {
            return;
        }

        this.modelo = new DefaultListModel<>();

        for (Usuario u : listaverusuario) {
            this.modelo.addElement(u);
        }

        lista.setModel(this.modelo);
    }

    private void editarUsuarioSeleccionado() {
        if (lista == null || modelo == null) {
            return;
        }

        int indice = lista.getSelectedIndex();
        Usuario seleccionado = lista.getSelectedValue();

        if (indice == -1 || seleccionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un usuario de la lista para editar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        CrearUsuario ventanaEditar = new CrearUsuario(listaverusuario);
        ventanaEditar.cargarParaEditar(seleccionado, () -> {
            modelo.set(indice, seleccionado);
        });
        ventanaEditar.setVisible(true);
    }

    private void eliminarUsuarioSeleccionado() {
        if (lista == null || modelo == null) {
            return;
        }

        int indice = lista.getSelectedIndex();
        Usuario seleccionado = lista.getSelectedValue();

        if (indice == -1 || seleccionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un usuario de la lista para eliminar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar a " + seleccionado.getPnombre() + " (" + seleccionado.getId() + ")?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaverusuario.remove(indice);
            PersistenciaUsuarios.guardar(listaverusuario);
            modelo.remove(indice);
        }
    }
}