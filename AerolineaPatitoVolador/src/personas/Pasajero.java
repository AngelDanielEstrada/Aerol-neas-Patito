package personas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pasajero extends Usuario {

    private String pasaporte;
    private Date fechaNacimiento;
    private List<Reservacion> reservas;

    public Pasajero(String id, String nombre, String email, String contraseña,
                    String pasaporte, Date fechaNacimiento) {
        super(id, nombre, email, contraseña);
        this.pasaporte = pasaporte;
        this.fechaNacimiento = fechaNacimiento;
        this.reservas = new ArrayList<>();
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Método para reservar un vuelo
    public Reservacion reservarVuelo(Vuelo vuelo) {
        if (vuelo.estaLleno()) {
            System.out.println("Error: El vuelo está lleno.");
            return null;
        }
        Reservacion nuevaReserva = new Reservacion(this, vuelo);
        reservas.add(nuevaReserva);
        System.out.println("Reserva realizada exitosamente para el vuelo " + vuelo.getNumeroVuelo());
        return nuevaReserva;
    }

    // Método para obtener todas las reservas del pasajero
    public List<Reservacion> verReservas() {
        return reservas;
    }
}
