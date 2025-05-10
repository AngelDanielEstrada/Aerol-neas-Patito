package personas;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    private List<Usuario> listaUsuarios;

    public Administrador(String nombre, String correo, String password) {
        super(nombre, correo, password);
        this.listaUsuarios = new ArrayList<>();
    }

    @Override
    public void verPerfil() {
        System.out.println("\n=== PERFIL ADMINISTRADOR ===");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Total usuarios: " + listaUsuarios.size());
    }

    public void crearUsuario(Usuario nuevoUsuario) {
        listaUsuarios.add(nuevoUsuario);
        System.out.println("Usuario '" + nuevoUsuario.getNombre() + "' creado exitosamente.");
    }

    public void eliminarUsuario(String correo) {
        if (listaUsuarios.removeIf(u -> u.getCorreo().equals(correo))) {
            System.out.println("Usuario con correo '" + correo + "' eliminado.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    public void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUARIOS ===");
        listaUsuarios.forEach(u -> {
            System.out.println("Nombre: " + u.getNombre() + " | Correo: " + u.getCorreo() +
                    " | Tipo: " + u.getClass().getSimpleName());
        });
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}