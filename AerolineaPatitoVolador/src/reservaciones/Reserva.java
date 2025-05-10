package reservaciones;

import personas.Pasajero;
import vuelos.Vuelo;
import core.ExcepcionReservaInvalida;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private String id;
    private LocalDateTime fecha;
    private String estado;
    private Pasajero pasajero;
    private Vuelo vuelo;
    private String asiento;

    public Reserva(String id, Pasajero pasajero, Vuelo vuelo, String asiento)
            throws ExcepcionReservaInvalida {
        if (pasajero == null || vuelo == null) {
            throw new ExcepcionReservaInvalida("Datos incompletos");
        }

        this.id = id;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.asiento = asiento;
        this.fecha = LocalDateTime.now();
        this.estado = "PENDIENTE";

        try {
            vuelo.agregarReserva(this);
        } catch (Exception e) {
            throw new ExcepcionReservaInvalida(e.getMessage());
        }
    }

    public void confirmar() {
        this.estado = "COMPLETADA";
        System.out.println(generarConfirmacion());
    }

    public void cancelar() {
        this.estado = "CANCELADA";
        vuelo.getAsientos().stream()
                .filter(a -> a.getNumero().equals(asiento))
                .findFirst()
                .ifPresent(asiento -> asiento.desocupar());
        System.out.println("Reserva " + id + " cancelada");
    }

    private String generarConfirmacion() {
        return String.format("""
            ===== CONFIRMACIÓN =====
            ID: %s
            Pasajero: %s
            Vuelo: %s (%s -> %s)
            Asiento: %s
            Fecha: %s
            Estado: %s
            ========================
            """,
                id, pasajero.getNombre(), vuelo.getNumero(),
                vuelo.getOrigen(), vuelo.getDestino(), asiento,
                fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                estado);
    }

    // Getters
    public String getId() { return id; }
    public Pasajero getPasajero() { return pasajero; }
    public Vuelo getVuelo() { return vuelo; }
    public String getAsiento() { return asiento; }
    public String getEstado() { return estado; }
}