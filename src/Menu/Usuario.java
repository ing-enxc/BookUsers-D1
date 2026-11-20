package Menu;

import javax.swing.*;
import java.io.Serializable;

public class Usuario implements Serializable {
    //Atributos que del objeto usuario que van en el array
    private String tipodid;
    private String ndid;
    private String pnombre;
    private String snombre;
    private String papellido;
    private String sapellido;
    private String contraseña;
    private String telefono;
    private String correo;
    private String rutafoto;
    private String username;
    private String id;

    //Esto es para que los objetos y el array se puedan guardar en bytes y no se limpie el array al
    private static final long serialVersionUID = 1L;

    // El constructor, donde se pasarán los datos para guardarlo en el array
    public Usuario(String tipodid, String ndid, String pnombre, String snombre, String papellido, String sapellido, String contraseña, String telefono, String correo, String rutafoto, String username, String id) {
        this.tipodid = tipodid;
        this.ndid = ndid;
        this.pnombre = pnombre;
        this.snombre = snombre;
        this.papellido = papellido;
        this.sapellido = sapellido;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.correo = correo;
        this.rutafoto = rutafoto;
        this.username = username;
        this.id = id;
    }

    // 3. Los Getters: permiten "leer" o "sacar" los datos desde otra clase
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getTipodid() {
        return tipodid;
    }

    public String getNdid() {
        return ndid;
    }

    public String getPnombre() {
        return pnombre;
    }

    public String getSnombre() {
        return snombre;
    }

    public String getPapellido() {
        return papellido;
    }

    public String getSapellido() {
        return sapellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRutafoto() {
        return rutafoto;
    }

    //Metodo para generar el username
    public static String generarUsername(String pnombre, String papellido, String sapellido) {
        //Guardamos cada dato necesario para construir el username en minúsucla
        String pNombre = pnombre.trim().toLowerCase();
        String pApellido = papellido.trim().toLowerCase();
        String sApellido = sapellido.trim().toLowerCase();

        //Tomamos la inicial del primer apellido y segundo apellido
        String inicial1 = pApellido.isEmpty() ? "" : String.valueOf(pApellido.charAt(0));
        String inicial2 = sApellido.isEmpty() ? "" : String.valueOf(sApellido.charAt(0));

        //Acá devuelve cómo se construye: inicial primer apellido, inicial segundo nombre y el primer nombre
        return inicial1 + inicial2 + pNombre;
    }

    //Acá se establece el formato cómo se verá la info de los usuarios registrados en el JList
    @Override
    public String toString() {
        return id + " - " + username;
    }
}