package core;

public class ExcepcionReservaInvalida extends Exception {
    public ExcepcionReservaInvalida(String mensaje) {
        super(mensaje);
    }

    public void registrarError() {
        System.err.println("[ERROR] " + getMessage());
    }
}