package reservaciones;

import personas.Pasajero;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Factura {
    private String idFactura;
    private double total;         // Subtotal sin IVA
    private double totalConIva;  // Total + IVA (19%)
    private Pago pago;           // Relación con Pago
    private LocalDateTime fechaEmision;

    // Constructor
    public Factura(String idFactura, Pago pago) {
        this.idFactura = idFactura;
        this.pago = pago;
        this.fechaEmision = LocalDateTime.now();
        this.total = pago.getMonto();
        this.totalConIva = calcularTotalConIva();
    }

    // Calcula el total + IVA (19%)
    private double calcularTotalConIva() {
        return total * 1.19; // IVA del 19%
    }

    // Genera el texto de la factura
    public String generarFactura() {
        return String.format(
                """
                === FACTURA ===
                ID: %s
                Fecha: %s
                Pasajero: %s
                Vuelo: %s
                Asiento: %s
                Subtotal: $%.2f
                IVA (19%%): $%.2f
                Total a pagar: $%.2f
                Método de pago: %s
                """,
                idFactura,
                fechaEmision.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                pago.getReserva().getPasajero().getNombre(),
                pago.getReserva().getVuelo().getNumero(),
                pago.getReserva().getAsiento(),
                total,
                totalConIva - total,
                totalConIva,
                pago.getMetodo()
        );
    }

    // Simula el envío por email
    public void enviarPorEmail() {
        String email = pago.getReserva().getPasajero().getCorreo();
        System.out.println("Enviando factura a: " + email);
        System.out.println(generarFactura());
    }

    // Getters
    public String getIdFactura() { return idFactura; }
    public double getTotalConIva() { return totalConIva; }
}