package personas;

import vuelos.Vuelo;

public class Azafato extends Empleado {
    private int añosExperiencia;

    public Azafato(String idEmpleado, String nombre, String correo, String password, int añosExperiencia) {
        super(idEmpleado, nombre, correo, password);
        this.añosExperiencia = añosExperiencia;
    }

    @Override
    public void verPerfil() {
        System.out.println("\n=== PERFIL AZAFATO ===");
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID Empleado: " + getIdEmpleado());
        System.out.println("Años de experiencia: " + añosExperiencia);
        System.out.println("Vuelo asignado: " +
                (getVueloAsignado() != null ? getVueloAsignado().getNumero() : "Ninguno"));
    }

    @Override
    public void asignarAVuelo(Vuelo vuelo) {
        if (vuelo.getAzafatos().size() >= 3) {
            System.out.println("Error: El vuelo ya tiene el máximo de azafatos");
            return;
        }
        super.setVueloAsignado(vuelo);
    }

    public void realizarRevisionSeguridad() {
        if (getVueloAsignado() != null) {
            System.out.println(getNombre() + " está realizando la revisión de seguridad en el vuelo "
                    + getVueloAsignado().getNumero());
        }
    }

    public int getAñosExperiencia() {
        return añosExperiencia;
    }
}