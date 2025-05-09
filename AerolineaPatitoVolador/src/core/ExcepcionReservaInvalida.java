package core;

public class ExcepcionReservaInvalida extends Exception {
    private String mensaje;

    public ExcepcionReservaInvalida(String mensaje) {
        this.mensaje = mensaje;
    }

    public String mostrarMensaje() {
        return mensaje;
    }

    public void registrarError() {
        System.out.println("Error registrado: " + mensaje);
    }
}