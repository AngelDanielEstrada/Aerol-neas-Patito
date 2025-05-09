package personas;

public class Empleado extends Persona {
    private String puesto;

    public Empleado(String nombre, String correo, String puesto) {
        super(nombre, correo);
        this.puesto = puesto;
    }

    public void asignarTarea() {
        System.out.println("Tarea asignada al empleado: " + nombre);
    }

    public void verPerfil() {
        System.out.println("Empleado: " + nombre + ", Puesto: " + puesto);
    }
}