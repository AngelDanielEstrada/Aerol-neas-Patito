package core;

public class ExcepcionVueloLleno extends Exception {
    private String mensaje;

    public ExcepcionVueloLleno(String mensaje) {
        this.mensaje = mensaje;
    }

    public String mostrarMensaje() {
        return mensaje;
    }

    public void registrarError() {
        System.out.println("Vuelo lleno: " + mensaje);
        // Registro en auditoría si es necesario
    }
}
