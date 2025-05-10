package personas;

public abstract class Usuario {
    protected String nombre;
    protected String correo;
    protected String password;

    public Usuario(String nombre, String correo, String password) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public abstract void verPerfil();

    public boolean validarPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
}