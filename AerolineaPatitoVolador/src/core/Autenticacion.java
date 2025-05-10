package core;

import personas.Usuario;
import java.util.List;

public class Autenticacion {
    private List<Usuario> usuariosRegistrados;

    public Autenticacion(List<Usuario> usuarios) {
        this.usuariosRegistrados = usuarios;
    }

    public Sesion iniciarSesion(String correo, String password) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getCorreo().equals(correo) && usuario.validarPassword(password)) {
                return new Sesion(usuario);
            }
        }
        return null;
    }

    public static Usuario crearUsuarioAdminPorDefecto() {
        return new personas.Administrador("Admin", "lecuarrao@gmail.com", "admin123");
    }
}