package personas;

public class Cliente extends Persona {
    private String idFrecuente;

    public Cliente(String nombre, String correo, String idFrecuente) {
        super(nombre, correo);
        this.idFrecuente = idFrecuente;
    }

    public void realizarReserva() {
        System.out.println("Reserva realizada por: " + nombre);
    }

    public void verPerfil() {
        System.out.println("Cliente: " + nombre + ", ID frecuente: " + idFrecuente);
    }
}