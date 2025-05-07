package core;

import java.time.LocalDateTime;
import java.util.UUID;

public class Sesion {
    private String token;
    private LocalDateTime fechaInicio;
    private boolean activa;

    public Sesion() {
        this.token = null;
        this.fechaInicio = null;
        this.activa = false;
    }

    public void iniciar() {
        this.token = UUID.randomUUID().toString();
        this.fechaInicio = LocalDateTime.now();
        this.activa = true;
        System.out.println("Sesión iniciada. Token: " + token + " - Fecha: " + fechaInicio);
    }

    public void cerrar() {
        if (activa) {
            System.out.println("Sesión cerrada. Token: " + token);
            this.token = null;
            this.fechaInicio = null;
            this.activa = false;
        } else {
            System.out.println("No hay sesión activa para cerrar.");
        }
    }

    public boolean estaActiva() {
        return activa;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
}
