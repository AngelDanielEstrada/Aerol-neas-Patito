package reservaciones;

import personas.Cliente;
import vuelos.Vuelo;

public class Reserva {
    private Cliente cliente;
    private Vuelo vuelo;
    private String asiento;

    public Reserva(Cliente cliente, Vuelo vuelo, String asiento) {
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.asiento = asiento;
    }

    public void confirmar() {
        System.out.println("Reserva confirmada para " + cliente.getNombre() + " en el vuelo " + vuelo.getNumero());
    }

    public String getAsiento() {
        return asiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }
}
