package core;

import personas.Usuario;
import java.time.LocalDateTime;

public class Sesion {
    private Usuario usuario;
    private LocalDateTime fechaInicio;
    private boolean activa;

    public Sesion(Usuario usuario) {
        this.usuario = usuario;
        this.fechaInicio = LocalDateTime.now();
        this.activa = true;
    }

    public void iniciar() {
        System.out.println("\n=== SESIÓN INICIADA ===");
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Fecha: " + fechaInicio);
        System.out.println("Rol: " + usuario.getClass().getSimpleName());
    }

    public void cerrar() {
        System.out.println("\nSesión de " + usuario.getNombre() + " cerrada.");
        this.activa = false;
    }

    public boolean esAdministrador() {
        return usuario instanceof personas.Administrador;
    }

    public boolean estaActiva() {
        return activa;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}