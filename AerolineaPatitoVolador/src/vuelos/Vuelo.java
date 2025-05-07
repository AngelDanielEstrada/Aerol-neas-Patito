package vuelos;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private String numeroVuelo;
    private String origen;
    private String destino;
    private String estado;
    private String fecha;
    private int capacidad;
    private List<Asiento> asientos;

    public Vuelo(String numeroVuelo, String origen, String destino, String fecha, int capacidad) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.capacidad = capacidad;
        this.estado = "Programado";
        this.asientos = new ArrayList<>();
        for (int i = 1; i <= capacidad; i++) {
            String numeroAsiento = "A" + i;
            this.asientos.add(new Asiento(numeroAsiento, "Económica"));
        }
    }

    public int verDisponibilidad() {
        int disponibles = 0;
        for (Asiento asiento : asientos) {
            if (asiento.estaDisponible()) {
                disponibles++;
            }
        }
        return disponibles;
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public boolean asignarAsiento(Asiento asiento) {
        if (asiento.estaDisponible()) {
            asiento.reservar();
            return true;
        }
        return false;
    }

    public boolean estaLleno() {
        return verDisponibilidad() == 0;
    }

    // Getters y Setters
    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getEstado() {
        return estado;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
