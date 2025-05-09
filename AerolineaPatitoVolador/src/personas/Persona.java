package personas;

public abstract class Persona {
    protected String nombre;
    protected String correo;

    public Persona(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public abstract void verPerfil();

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
}