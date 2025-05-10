package vuelos;

import personas.Pasajero;
import personas.Piloto;
import personas.Azafato;
import reservaciones.Reserva;
import core.ExcepcionVueloLleno;
import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private String numero;
    private String origen;
    private String destino;
    private List<Pasajero> pasajeros;
    private List<Asiento> asientos;
    private List<Reserva> reservas;
    private Piloto piloto;
    private List<Azafato> azafatos;
    private int capacidadMaxima;

    public Vuelo(String numero, String origen, String destino, int capacidadMaxima) {
        this.numero = numero;
        this.origen = origen;
        this.destino = destino;
        this.pasajeros = new ArrayList<>();
        this.asientos = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.azafatos = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
        inicializarAsientos();
    }

    private void inicializarAsientos() {
        for (int i = 1; i <= capacidadMaxima; i++) {
            asientos.add(new Asiento("A" + i, this));
        }
    }

    // Métodos para gestión de tripulación
    public void asignarPiloto(Piloto piloto) {
        if (!piloto.licenciaEstaVigente()) {
            System.out.println("No se puede asignar piloto con licencia vencida");
            return;
        }
        this.piloto = piloto;
        piloto.asignarAVuelo(this);
        System.out.println("Piloto " + piloto.getNombre() + " asignado al vuelo " + numero);
    }

    public void agregarAzafato(Azafato azafato) {
        if (azafatos.size() >= 3) { // Ejemplo: máximo 3 azafatos por vuelo
            System.out.println("No se pueden asignar más azafatos a este vuelo");
            return;
        }
        azafatos.add(azafato);
        azafato.asignarAVuelo(this);
        System.out.println("Azafato " + azafato.getNombre() + " asignado al vuelo " + numero);
    }

    // Métodos existentes para reservas (sin cambios)
    public void agregarReserva(Reserva reserva) throws ExcepcionVueloLleno {
        if (reservas.size() >= capacidadMaxima) {
            throw new ExcepcionVueloLleno("Vuelo " + numero + " lleno");
        }

        Asiento asientoReserva = asientos.stream()
                .filter(a -> a.getNumero().equals(reserva.getAsiento()))
                .findFirst()
                .orElseThrow(() -> new ExcepcionVueloLleno("Asiento no existe"));

        if (asientoReserva.isOcupado()) {
            throw new ExcepcionVueloLleno("Asiento " + reserva.getAsiento() + " ocupado");
        }

        asientoReserva.ocupar();
        reservas.add(reserva);
        pasajeros.add(reserva.getPasajero());
    }

    // Getters actualizados
    public List<Asiento> getAsientos() {
        return asientos;
    }

    public List<Asiento> getAsientosDisponibles() {
        List<Asiento> disponibles = new ArrayList<>();
        for (Asiento asiento : asientos) {
            if (!asiento.isOcupado()) {
                disponibles.add(asiento);
            }
        }
        return disponibles;
    }

    public String getNumero() { return numero; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public List<Reserva> getReservas() { return reservas; }
    public Piloto getPiloto() { return piloto; }
    public List<Azafato> getAzafatos() { return azafatos; }
    public int getCapacidadMaxima() { return capacidadMaxima; }

    // Método para mostrar información del vuelo
    public void mostrarInfoVuelo() {
        System.out.println("=== INFORMACIÓN DE VUELO ===");
        System.out.println("Vuelo: " + numero + " | Ruta: " + origen + " -> " + destino);
        System.out.println("Piloto: " + (piloto != null ? piloto.getNombre() : "No asignado"));
        System.out.println("Azafatos: " + azafatos.stream().map(Azafato::getNombre).toList());
        System.out.println("Pasajeros: " + pasajeros.size() + "/" + capacidadMaxima);
        System.out.println("Asientos disponibles: " + getAsientosDisponibles().size());
    }
}