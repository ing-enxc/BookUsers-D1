package Menu;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class PersistenciaUsuarios {
    private static final String ARCHIVO = "usuarios.dat";

    public static void guardar(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(usuarios);
            oos.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuarios: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Usuario> cargar() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                return (ArrayList<Usuario>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar usuarios: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}