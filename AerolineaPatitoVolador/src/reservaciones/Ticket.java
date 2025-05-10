package reservaciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String codigo;
    private LocalDateTime fecha;
    private Reserva reserva;

    public Ticket(String codigo, Reserva reserva) {
        this.codigo = codigo;
        this.reserva = reserva;
        this.fecha = LocalDateTime.now();
    }

    // Valida si la reserva está completada
    public boolean validarEstado() {
        return reserva.getEstado().equals("COMPLETADA");
    }

    // Muestra los detalles del ticket
    public void mostrarFactura() {
        System.out.println("=== FACTURA ===");
        System.out.println("Código: " + codigo);
        System.out.println("Fecha: " + fecha.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("Pasajero: " + reserva.getPasajero().getNombre());
        System.out.println("Vuelo: " + reserva.getVuelo().getNumero());
        System.out.println("Asiento: " + reserva.getAsiento());
        System.out.println("Estado: " + (validarEstado() ? "VÁLIDO" : "INVÁLIDO"));
    }

    // Getters
    public String getCodigo() { return codigo; }
    public LocalDateTime getFecha() { return fecha; }
}