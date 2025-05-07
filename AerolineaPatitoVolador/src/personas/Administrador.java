package personas;
public class Administrador extends Usuario {

    private int nivelAcceso;

    public Administrador(String id, String nombre, String email, String contraseña, int nivelAcceso) {
        super(id, nombre, email, contraseña);
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    // Método para crear un usuario (de tipo Usuario o sus subclases)
    public void crearUsuario(Usuario usuario) {
        // Aquí podrías agregarlo a una base de datos o lista general de usuarios
        System.out.println("Usuario creado: " + usuario.getNombre());
        // Por ahora, imprimimos para simular la acción
    }

    // Método para eliminar usuario por ID
    public void eliminarUsuario(String id) {
        // Aquí podrías buscar al usuario por ID y eliminarlo de una base de datos o lista
        System.out.println("Usuario eliminado con ID: " + id);
        // Por ahora, simulamos la eliminación con un mensaje
    }
}
