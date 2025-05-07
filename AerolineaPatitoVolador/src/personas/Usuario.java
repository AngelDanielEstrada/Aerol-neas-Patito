package personas;

public class Usuario {

    protected String id;
    protected String nombre;
    protected String email;
    protected String contraseña;
    private boolean sesionActiva;

    public Usuario(String id, String nombre, String email, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.sesionActiva = false;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public boolean login() {
        if (!sesionActiva) {
            sesionActiva = true;
            System.out.println("Inicio de sesión exitoso para " + nombre);
            return true;
        } else {
            System.out.println("El usuario ya ha iniciado sesión.");
            return false;
        }
    }

    public void logout() {
        if (sesionActiva) {
            sesionActiva = false;
            System.out.println("Sesión cerrada para " + nombre);
        } else {
            System.out.println("El usuario no tiene una sesión activa.");
        }
    }

    public void cambiarContraseña(String nueva) {
        this.contraseña = nueva;
        System.out.println("Contraseña actualizada para " + nombre);
    }
}
