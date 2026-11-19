package Menu;
import java.util.ArrayList;

public class Usuario {
    //Array
    private ArrayList<Usuario> listadeusuarios = new ArrayList<>();

    private int siguienteNumeroId = 1;

    public String generarSiguienteId() {
        String idFormateado = String.format("%04d", siguienteNumeroId);
        siguienteNumeroId++;
        return idFormateado;
    }

    //El set
    public void agregarArray (Registro usuarioCreado) {
        listadeusuarios.add(usuarioCreado);
    }

    //El get
    public ArrayList<Usuario> getListadeusuarios() {
        return listadeusuarios;
    }
}