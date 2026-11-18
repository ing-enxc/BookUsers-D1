package Menu;
import java.util.ArrayList;

import java.util.ArrayList;

public class Usuario {
    //Array
    private ArrayList<Usuario> listadeusuarios = new ArrayList<>();

    //Metodo que guarda las cosas en el arraylist

    //El set
    public void agregarArray (Registro usuarioCreado) {
        listadeusuarios.add(usuarioCreado);
    }

    //El get
    public ArrayList<Usuario> getListadeusuarios() {
        return listadeusuarios;
    }
}
