package Menu;
import java.util.ArrayList;

public class Usuario {
    //Array
    private ArrayList<Usuario> listadeusuarios = new ArrayList<>();

    //El set
    public void agregarArray (Registro usuarioCreado) {
        listadeusuarios.add(usuarioCreado);
    }

    //El get
    public ArrayList<Usuario> getListadeusuarios() {
        return listadeusuarios;
    }
}