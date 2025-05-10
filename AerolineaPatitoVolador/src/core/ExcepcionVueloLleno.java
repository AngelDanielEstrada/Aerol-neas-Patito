package core;

public class ExcepcionVueloLleno extends Exception {
    public ExcepcionVueloLleno(String mensaje) {
        super(mensaje);
    }

    // Metodo adicional para registro
    public void registrarError() {
        System.err.println("Error registrado: " + getMessage());
    }
}