package personas;

import vuelos.Vuelo;

public abstract class Empleado extends Usuario {
    private String idEmpleado;
    private Vuelo vueloAsignado;

    public Empleado(String idEmpleado, String nombre, String correo, String password) {
        super(nombre, correo, password);
        this.idEmpleado = idEmpleado;
    }

    public abstract void asignarAVuelo(Vuelo vuelo);

    public String getIdEmpleado() {
        return idEmpleado;
    }

    protected Vuelo getVueloAsignado() {
        return vueloAsignado;
    }

    protected void setVueloAsignado(Vuelo vuelo) {
        this.vueloAsignado = vuelo;
    }
}