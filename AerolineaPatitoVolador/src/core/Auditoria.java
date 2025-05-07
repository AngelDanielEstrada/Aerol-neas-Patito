package core;

import java.time.LocalDateTime;

public class Auditoria {
    private String id;
    private String descripcion;
    private LocalDateTime fecha;

    public Auditoria(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    public void registrarAccion() {
        // Aquí podrías guardar la auditoría en una base de datos o archivo
        System.out.println("Acción registrada: " + descripcion);
    }

    public String getDescripcion() {
        return descripcion;
    }
}
