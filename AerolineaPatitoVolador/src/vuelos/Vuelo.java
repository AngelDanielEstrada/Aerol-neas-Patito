package vuelos;

import personas.Cliente;
import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private String numero;
    private String origen;
    private String destino;
    private List<Cliente> pasajeros;

    public Vuelo(String numero, String origen, String destino) {
        this.numero = numero;
        this.origen = origen;
        this.destino = destino;
        this.pasajeros = new ArrayList<>();
    }

    public void agregarPasajero(Cliente cliente) {
        pasajeros.add(cliente);
    }

    public String getNumero() {
        return numero;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public List<Cliente> getPasajeros() {
        return pasajeros;
    }
}