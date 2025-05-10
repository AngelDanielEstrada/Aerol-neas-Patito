package reservaciones;

public class Pago {
    private double monto;
    private String metodo;
    private Reserva reserva;
    private Factura factura;

    public Pago(double monto, String metodo, Reserva reserva) {
        this.monto = monto;
        this.metodo = metodo;
        this.reserva = reserva;
    }

    public void procesarPago() {
        System.out.println("Procesando pago de $" + monto + " vía " + metodo);

        // Cambiar de validarEstado() a getEstado().equals("COMPLETADA")
        if (reserva != null && "COMPLETADA".equals(reserva.getEstado())) {
            this.factura = new Factura("FAC-" + reserva.getId(), this);
            System.out.println("Pago exitoso. Factura generada: " + factura.getIdFactura());
        } else {
            System.out.println("Error: Reserva no está completada");
        }
    }

    public void reembolsar() {
        System.out.println("Reembolsando $" + monto + " a " + reserva.getPasajero().getNombre());
        if (factura != null) {
            System.out.println("Factura " + factura.getIdFactura() + " anulada");
        }
    }

    // Getters
    public double getMonto() { return monto; }
    public String getMetodo() { return metodo; }
    public Reserva getReserva() { return reserva; }
    public Factura getFactura() { return factura; }
}