package personas;

import vuelos.Vuelo;
import java.time.LocalDate;

public class Piloto extends Empleado {
    private String numeroLicencia;
    private LocalDate fechaVencimientoLicencia;

    public Piloto(String idEmpleado, String nombre, String correo, String password,
                  String numeroLicencia, LocalDate fechaVencimiento) {
        super(idEmpleado, nombre, correo, password);
        this.numeroLicencia = numeroLicencia;
        this.fechaVencimientoLicencia = fechaVencimiento;
    }

    @Override
    public void verPerfil() {
        System.out.println("\n=== PERFIL PILOTO ===");
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID Empleado: " + getIdEmpleado());
        System.out.println("Licencia: " + numeroLicencia);
        System.out.println("Licencia vigente: " + (licenciaEstaVigente() ? "Sí" : "No"));
        System.out.println("Vuelo asignado: " +
                (getVueloAsignado() != null ? getVueloAsignado().getNumero() : "Ninguno"));
    }

    public boolean licenciaEstaVigente() {
        return LocalDate.now().isBefore(fechaVencimientoLicencia);
    }

    @Override
    public void asignarAVuelo(Vuelo vuelo) {
        if (!licenciaEstaVigente()) {
            System.out.println("Error: No se puede asignar piloto con licencia vencida");
            return;
        }
        super.setVueloAsignado(vuelo);
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public LocalDate getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }
}