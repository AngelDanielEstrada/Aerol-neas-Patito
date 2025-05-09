package core;

import java.time.LocalDateTime;

public class Auditoria {
    private String id;
    private String descripcion;
    private LocalDateTime fecha;

    public Auditoria(String id, String descripcion, LocalDateTime fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public void registrarAccion() {
        System.out.println("Acción registrada: " + descripcion + " en " + fecha);
    }

    public String getDescripcion() {
        return descripcion;
    }
}