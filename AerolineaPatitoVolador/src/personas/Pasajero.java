package personas;

public class Pasajero extends Usuario {
    private String idFrecuente;

    public Pasajero(String nombre, String correo, String password, String idFrecuente) {
        super(nombre, correo, password);
        this.idFrecuente = idFrecuente;
    }

    @Override
    public void verPerfil() {
        System.out.println("\n=== PERFIL PASAJERO ===");
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID Frecuente: " + idFrecuente);
        System.out.println("Correo: " + getCorreo());
    }

    public String getIdFrecuente() {
        return idFrecuente;
    }

}