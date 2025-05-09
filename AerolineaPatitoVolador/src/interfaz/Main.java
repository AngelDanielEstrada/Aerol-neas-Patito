package interfaz;

import personas.Cliente;
import personas.Empleado;
import vuelos.Vuelo;
import reservaciones.Reserva;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Vuelo> vuelos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ AEROLÍNEA PATITO VOLADOR ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Crear vuelo");
            System.out.println("3. Reservar vuelo");
            System.out.println("4. Ver vuelos");
            System.out.println("5. Ver reservas");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> crearVuelo();
                case 3 -> reservarVuelo();
                case 4 -> verVuelos();
                case 5 -> verReservas();
                case 6 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }

        System.out.println("Programa finalizado.");
    }

    private static void registrarCliente() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("ID frecuente: ");
        String id = scanner.nextLine();
        clientes.add(new Cliente(nombre, correo, id));
        System.out.println("Cliente registrado.");
    }

    private static void crearVuelo() {
        System.out.print("Número de vuelo: ");
        String numero = scanner.nextLine();
        System.out.print("Origen: ");
        String origen = scanner.nextLine();
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        vuelos.add(new Vuelo(numero, origen, destino));
        System.out.println("Vuelo creado.");
    }

    private static void reservarVuelo() {
        if (clientes.isEmpty() || vuelos.isEmpty()) {
            System.out.println("Debe haber al menos un cliente y un vuelo.");
            return;
        }

        System.out.println("Clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ". " + clientes.get(i).getNombre());
        }

        System.out.print("Seleccione cliente: ");
        int clienteIndex = scanner.nextInt();
        scanner.nextLine();
        Cliente cliente = clientes.get(clienteIndex);

        System.out.println("Vuelos:");
        for (int i = 0; i < vuelos.size(); i++) {
            System.out.println(i + ". " + vuelos.get(i).getNumero() + " (" + vuelos.get(i).getOrigen() + " -> " + vuelos.get(i).getDestino() + ")");
        }

        System.out.print("Seleccione vuelo: ");
        int vueloIndex = scanner.nextInt();
        scanner.nextLine();
        Vuelo vuelo = vuelos.get(vueloIndex);

        System.out.print("Asiento: ");
        String asiento = scanner.nextLine();

        vuelo.agregarPasajero(cliente);
        reservas.add(new Reserva(cliente, vuelo, asiento));
        System.out.println("Reserva realizada.");
    }

    private static void verVuelos() {
        for (Vuelo vuelo : vuelos) {
            System.out.println("Vuelo " + vuelo.getNumero() + ": " + vuelo.getOrigen() + " -> " + vuelo.getDestino());
        }
    }

    private static void verReservas() {
        for (Reserva reserva : reservas) {
            System.out.println("Reserva: Cliente " + reserva.getCliente().getNombre() + ", Vuelo " + reserva.getVuelo().getNumero() + ", Asiento " + reserva.getAsiento());
        }
    }
}
