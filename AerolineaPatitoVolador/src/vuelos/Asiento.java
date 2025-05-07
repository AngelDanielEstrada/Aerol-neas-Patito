package vuelos;

public class Asiento {
    private String numero;
    private String clase;
    private boolean disponible;

    public Asiento(String numero, String clase) {
        this.numero = numero;
        this.clase = clase;
        this.disponible = true;
    }

    public boolean reservar() {
        if (disponible) {
            disponible = false;
            return true;
        }
        return false;
    }

    public void liberar() {
        disponible = true;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    // Getters
    public String getNumero() {
        return numero;
    }

    public String getClase() {
        return clase;
    }

    public boolean isDisponible() {
        return disponible;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "numero='" + numero + '\'' +
                ", clase='" + clase + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
