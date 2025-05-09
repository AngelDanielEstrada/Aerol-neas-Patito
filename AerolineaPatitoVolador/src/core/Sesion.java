package core;

import java.time.LocalDateTime;

public class Sesion {
    private String token;
    private LocalDateTime fechaInicio;

    public Sesion(String token, LocalDateTime fechaInicio) {
        this.token = token;
        this.fechaInicio = fechaInicio;
    }

    public void iniciar() {
        System.out.println("Sesión iniciada con token: " + token);
    }

    public void cerrar() {
        System.out.println("Sesión cerrada.");
    }
}