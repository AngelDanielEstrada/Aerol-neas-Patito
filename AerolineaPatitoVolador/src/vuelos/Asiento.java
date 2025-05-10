package vuelos;

public class Asiento {
    private String numero;
    private boolean ocupado;
    private Vuelo vuelo;

    public Asiento(String numero, Vuelo vuelo) {
        this.numero = numero;
        this.vuelo = vuelo;
        this.ocupado = false;
    }

    // Getters y Setters
    public String getNumero() {
        return numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void ocupar() {
        this.ocupado = true;
    }

    public void desocupar() {
        this.ocupado = false;
    }
}