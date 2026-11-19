package Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VerUsuarios extends JFrame {

    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private Registro r;
    private Usuario usuario;

    // Recibimos el gestor ya poblado desde MenuRegistro
    public VerUsuarios(Registro gestor) {
        this.usuario = usuario;

        setTitle("Usuarios Registrados");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana

        // 1. Definir las columnas de la tabla
        String[] columnas = {"ID", "Username", "Nombre", "Apellido", "Correo", "Ruta Foto"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaUsuarios = new JTable(modeloTabla);

        // 2. Cargar los datos del ArrayList a la tabla
        cargarDatos();

        // 3. Agregar la tabla dentro de un JScrollPane
        add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);
    }

    private void cargarDatos() {
        // Limpia filas anteriores por si se recarga
        modeloTabla.setRowCount(0);

        // Recorre el ArrayList de GestorRegistros
        for (Usuario r : usuario.getListadeusuarios()) {
            Object[] fila = {
                    r.getTipodid(),
                    r.getUsername(),
                    r.getPnombre(),
                    r.getPapellido(),
                    r.getCorreo(),
                    r.getRutafoto()
            };
            modeloTabla.addRow(fila);
        }
    }
}