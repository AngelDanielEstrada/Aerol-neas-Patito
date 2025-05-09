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
        System.out.println("Error registrado: " + mensaje);
    }
}