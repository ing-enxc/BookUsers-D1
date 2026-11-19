package Menu;

public class Registro extends Usuario {
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

    // 2. El constructor: sirve para inicializar los datos al crear un nuevo objeto
    public Registro(String tipodid, String ndid, String pnombre, String snombre, String papellido, String sapellido, String contraseña, String telefono, String correo, String rutafoto, String username, String id) {
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

    public void setId(String id) {
        this.id = id;
    }

    public String getTipodid() {
        return tipodid;
    }

    public void setTipodid(String tipodid) {
        this.tipodid = tipodid;
    }

    public String getNdid() {
        return ndid;
    }

    public void setNdid(String ndid) {
        this.ndid = ndid;
    }

    public String getPnombre() {
        return pnombre;
    }

    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getPapellido() {
        return papellido;
    }

    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    public String getSapellido() {
        return sapellido;
    }

    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRutafoto() {
        return rutafoto;
    }

    public void setRutafoto(String rutafoto) {
        this.rutafoto = rutafoto;
    }

    public static String generarUsername(String pnombre, String papellido, String sapellido) {
        String pNombre = pnombre.trim().toLowerCase();
        String pApellido = papellido.trim().toLowerCase();
        String sApellido = sapellido.trim().toLowerCase();

        // Tomamos la primera letra de cada apellido (si existe)
        String inicial1 = pApellido.isEmpty() ? "" : String.valueOf(pApellido.charAt(0));
        String inicial2 = sApellido.isEmpty() ? "" : String.valueOf(sApellido.charAt(0));

        return inicial1 + inicial2 + pNombre;
    }
}
