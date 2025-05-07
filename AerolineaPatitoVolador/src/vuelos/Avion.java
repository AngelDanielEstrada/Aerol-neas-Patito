package vuelos;

public class Avion {
    private String modelo;
    private int capacidad;
    private String fabricante;

    public Avion(String modelo, int capacidad, String fabricante) {
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.fabricante = fabricante;
    }

    public String getInfo() {
        return modelo + " - " + capacidad + " pasajeros";
    }

    public String getFabricante() {
        return fabricante;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "modelo='" + modelo + '\'' +
                ", capacidad=" + capacidad +
                ", fabricante='" + fabricante + '\'' +
                '}';
    }
}
