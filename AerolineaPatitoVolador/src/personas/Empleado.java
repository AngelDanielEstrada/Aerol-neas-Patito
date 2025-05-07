package personas;

public class Empleado extends Usuario {

    private String numeroEmpleado;
    private String puesto;

    public Empleado(String id, String nombre, String email, String contraseña,
                    String numeroEmpleado, String puesto) {
        super(id, nombre, email, contraseña);
        this.numeroEmpleado = numeroEmpleado;
        this.puesto = puesto;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    // Método para gestionar un vuelo (puede actualizar estado, etc.)
    public void gestionarVuelo(Vuelo vuelo) {
        // Acción simulada
        System.out.println("Empleado " + nombre + " gestionando el vuelo " + vuelo.getNumeroVuelo());
        // Aquí podrías actualizar estado, horario, etc.
    }

    // Método para asignar un asiento a una reserva
    public void asignarAsiento(Reservacion reservacion, Asiento asiento) {
        if (asiento.estaDisponible()) {
            reservacion.setAsiento(asiento);
            asiento.reservar();
            System.out.println("Asiento " + asiento.getNumero() + " asignado exitosamente.");
        } else {
            System.out.println("El asiento ya está ocupado.");
        }
    }
}
