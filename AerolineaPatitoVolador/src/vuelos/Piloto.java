package vuelos;

public class Piloto {
    private String licencia;
    private int horasVuelo;

    public Piloto(String licencia, int horasVuelo) {
        this.licencia = licencia;
        this.horasVuelo = horasVuelo;
    }

    public boolean verificarLicencia() {
        return licencia != null && !licencia.isEmpty();
    }

    public void asignarVuelo(Vuelo vuelo) {
        System.out.println("Piloto asignado al vuelo: " + vuelo.getNumeroVuelo());
    }

    public String getLicencia() {
        return licencia;
    }

    public int getHorasVuelo() {
        return horasVuelo;
    }

    @Override
    public String toString() {
        return "Piloto{" +
                "licencia='" + licencia + '\'' +
                ", horasVuelo=" + horasVuelo +
                '}';
    }
}
