package core;

import personas.Administrador;
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
    public static Usuario crearUsuarioAdminPorDefecto2() {
        return new Administrador("Admin Eve", "2019776f@umich.mx", "adminchaparra");
    }
    public static Usuario crearUsuarioAdminPorDefecto3() {
        return new Administrador("Admin Oliver", "sefidoalker@gmail.com", "admin234");
    }


}