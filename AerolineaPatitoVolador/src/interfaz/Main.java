package interfaz;

import core.*;
import personas.*;
import vuelos.*;
import reservaciones.*;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Vuelo> vuelos = new ArrayList<>();
    private static Autenticacion autenticacion;
    private static Sesion sesionActual;

    public static void main(String[] args) {
        // Configuración inicial
        inicializarSistema();

        // Menú principal
        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> gestionarUsuarios();
                case 2 -> gestionarVuelos();
                case 3 -> gestionarReservas();
                case 4 -> verMiPerfil();
                case 5 -> { salir = true; cerrarSesion(); }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void inicializarSistema() {
        // Crear admin por defecto
        usuarios.add(Autenticacion.crearUsuarioAdminPorDefecto());
        usuarios.add(Autenticacion.crearUsuarioAdminPorDefecto2());
        usuarios.add(Autenticacion.crearUsuarioAdminPorDefecto3());
        autenticacion = new Autenticacion(usuarios);

        // Autenticación
        boolean autenticado = false;
        while (!autenticado) {
            System.out.println("\n=== SISTEMA DE AEROLÍNEA ===");
            System.out.print("Correo: ");
            String correo = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            sesionActual = autenticacion.iniciarSesion(correo, password);
            if (sesionActual != null) {
                sesionActual.iniciar();
                autenticado = true;
            } else {
                System.out.println("Credenciales incorrectas. Intente nuevamente.");
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Usuarios");
        System.out.println("2. Gestión de Vuelos");
        System.out.println("3. Gestión de Reservas");
        System.out.println("4. Ver mi perfil");
        System.out.println("5. Cerrar sesión y salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void gestionarUsuarios() {
        if (!sesionActual.esAdministrador()) {
            System.out.println("Error: Solo los administradores pueden gestionar usuarios");
            return;
        }

        boolean volver = false;
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE USUARIOS ===");
            System.out.println("1. Crear usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion();
            Administrador admin = (Administrador) sesionActual.getUsuario();

            switch (opcion) {
                case 1 -> crearUsuario(admin);
                case 2 -> eliminarUsuario(admin);
                case 3 -> admin.listarUsuarios();
                case 4 -> volver = true;
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void crearUsuario(Administrador admin) {
        System.out.println("\n=== CREAR NUEVO USUARIO ===");
        System.out.println("1. Pasajero");
        System.out.println("2. Piloto");
        System.out.println("3. Azafato");
        System.out.println("4. Volver");
        System.out.print("Seleccione tipo de usuario: ");

        int tipo = leerOpcion();
        if (tipo == 4) return;

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario nuevoUsuario = null;

        switch (tipo) {
            case 1 -> {
                System.out.print("ID Frecuente: ");
                String idFrecuente = scanner.nextLine();
                nuevoUsuario = new Pasajero(nombre, correo, password, idFrecuente);
            }
            case 2 -> {
                System.out.print("ID Empleado: ");
                String idEmpleado = scanner.nextLine();
                System.out.print("Número de licencia: ");
                String licencia = scanner.nextLine();

                LocalDate fechaVencimiento = null;
                while (fechaVencimiento == null) {
                    System.out.print("Fecha vencimiento licencia (AAAA-MM-DD): ");
                    String fechaStr = scanner.nextLine();
                    try {
                        fechaVencimiento = LocalDate.parse(fechaStr);
                    } catch (DateTimeParseException e) {
                        System.out.println("Fecha inválida. Usa el formato AAAA-MM-DD.");
                    }
                }

                nuevoUsuario = new Piloto(idEmpleado, nombre, correo, password, licencia, fechaVencimiento);
            }
            case 3 -> {
                System.out.print("ID Empleado: ");
                String idEmpleado = scanner.nextLine();

                int experiencia = -1;
                while (experiencia < 0) {
                    System.out.print("Años de experiencia: ");
                    try {
                        experiencia = Integer.parseInt(scanner.nextLine());
                        if (experiencia < 0) {
                            System.out.println("La experiencia no puede ser negativa.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Ingresa un número entero.");
                    }
                }

                nuevoUsuario = new Azafato(idEmpleado, nombre, correo, password, experiencia);
            }
            default -> {
                System.out.println("Opción inválida");
                return;
            }
        }

        admin.crearUsuario(nuevoUsuario);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado exitosamente");
    }

    private static void eliminarUsuario(Administrador admin) {
        System.out.print("\nIngrese el correo del usuario a eliminar: ");
        String correo = scanner.nextLine();
        admin.eliminarUsuario(correo);
    }

    private static void gestionarVuelos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE VUELOS ===");
            System.out.println("1. Crear vuelo");
            System.out.println("2. Asignar tripulación");
            System.out.println("3. Ver vuelos");
            System.out.println("4. Eliminar vuelo");
            System.out.println("5. Cancelar vuelo");
            System.out.println("6. Reactivar vuelo");
            System.out.println("7. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> crearVuelo();
                case 2 -> asignarTripulacion();
                case 3 -> listarVuelos();
                case 4 -> eliminarVuelo();
                case 5 -> cancelarVuelo();
                case 6 -> reactivarVuelo();
                case 7 -> volver = true;
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void crearVuelo() {
        System.out.println("\n=== CREAR NUEVO VUELO ===");
        System.out.print("Número de vuelo: ");
        String numero = scanner.nextLine();
        System.out.print("Origen: ");
        String origen = scanner.nextLine();
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Capacidad máxima: ");
        int capacidad = Integer.parseInt(scanner.nextLine());

        vuelos.add(new Vuelo(numero, origen, destino, capacidad));
        System.out.println("Vuelo creado exitosamente");
    }

    private static void asignarTripulacion() {
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados");
            return;
        }

        System.out.println("\n=== ASIGNAR TRIPULACIÓN ===");
        System.out.println("Vuelos disponibles (solo activos):");
        vuelos.stream()
                .filter(v -> "ACTIVO".equals(v.getEstado()))
                .forEach(v -> System.out.println("- " + v.getNumero() + ": " + v.getOrigen() + " a " + v.getDestino()));

        System.out.print("Seleccione el número de vuelo: ");
        String numeroVuelo = scanner.nextLine();

        Vuelo vuelo = vuelos.stream()
                .filter(v -> v.getNumero().equals(numeroVuelo))
                .findFirst()
                .orElse(null);

        if (vuelo == null) {
            System.out.println("Vuelo no encontrado");
            return;
        }

        if ("CANCELADO".equals(vuelo.getEstado())) {
            System.out.println("No se puede asignar tripulación a un vuelo cancelado");
            return;
        }

        System.out.println("1. Asignar piloto");
        System.out.println("2. Asignar azafato");
        System.out.print("Seleccione opción: ");
        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> asignarPiloto(vuelo);
            case 2 -> asignarAzafato(vuelo);
            default -> System.out.println("Opción inválida");
        }
    }

    private static void asignarPiloto(Vuelo vuelo) {
        List<Piloto> pilotos = usuarios.stream()
                .filter(u -> u instanceof Piloto)
                .map(u -> (Piloto) u)
                .toList();

        if (pilotos.isEmpty()) {
            System.out.println("No hay pilotos registrados");
            return;
        }

        System.out.println("\nPilotos disponibles:");
        pilotos.forEach(p -> System.out.println("- " + p.getNombre() + " (" + p.getIdEmpleado() + ")"));

        System.out.print("Ingrese ID del piloto: ");
        String idPiloto = scanner.nextLine();

        Piloto piloto = pilotos.stream()
                .filter(p -> p.getIdEmpleado().equals(idPiloto))
                .findFirst()
                .orElse(null);

        if (piloto != null) {
            vuelo.asignarPiloto(piloto);
        } else {
            System.out.println("Piloto no encontrado");
        }
    }

    private static void asignarAzafato(Vuelo vuelo) {
        List<Azafato> azafatos = usuarios.stream()
                .filter(u -> u instanceof Azafato)
                .map(u -> (Azafato) u)
                .toList();

        if (azafatos.isEmpty()) {
            System.out.println("No hay azafatos registrados");
            return;
        }

        System.out.println("\nAzafatos disponibles:");
        azafatos.forEach(a -> System.out.println("- " + a.getNombre() + " (" + a.getIdEmpleado() + ")"));

        System.out.print("Ingrese ID del azafato: ");
        String idAzafato = scanner.nextLine();

        Azafato azafato = azafatos.stream()
                .filter(a -> a.getIdEmpleado().equals(idAzafato))
                .findFirst()
                .orElse(null);

        if (azafato != null) {
            vuelo.agregarAzafato(azafato);
        } else {
            System.out.println("Azafato no encontrado");
        }
    }

    private static void listarVuelos() {
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados");
            return;
        }

        System.out.println("\n=== LISTA DE VUELOS ===");
        vuelos.forEach(v -> {
            System.out.println("\nVuelo: " + v.getNumero());
            System.out.println("Estado: " + v.getEstado());
            System.out.println("Ruta: " + v.getOrigen() + " - " + v.getDestino());
            System.out.println("Pasajeros: " + v.getReservas().size() + "/" + v.getCapacidadMaxima());
            System.out.println("Piloto: " + (v.getPiloto() != null ? v.getPiloto().getNombre() : "No asignado"));
            System.out.println("Azafatos: " + v.getAzafatos().stream().map(Azafato::getNombre).toList());
        });
    }

    private static void eliminarVuelo() {
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados para eliminar");
            return;
        }

        System.out.println("\n=== ELIMINAR VUELO ===");
        listarVuelos();
        System.out.print("Ingrese el número del vuelo a eliminar: ");
        String numeroVuelo = scanner.nextLine();

        Vuelo vueloAEliminar = vuelos.stream()
                .filter(v -> v.getNumero().equals(numeroVuelo))
                .findFirst()
                .orElse(null);

        if (vueloAEliminar != null) {
            vuelos.remove(vueloAEliminar);
            System.out.println("Vuelo eliminado exitosamente");
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private static void cancelarVuelo() {
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados");
            return;
        }

        System.out.println("\n=== CANCELAR VUELO ===");
        System.out.println("Vuelos activos disponibles:");
        vuelos.stream()
                .filter(v -> "ACTIVO".equals(v.getEstado()))
                .forEach(v -> System.out.println("- " + v.getNumero() + ": " + v.getOrigen() + " a " + v.getDestino()));

        System.out.print("Ingrese el número del vuelo a cancelar: ");
        String numeroVuelo = scanner.nextLine();

        Vuelo vuelo = vuelos.stream()
                .filter(v -> v.getNumero().equals(numeroVuelo))
                .findFirst()
                .orElse(null);

        if (vuelo != null) {
            if ("ACTIVO".equals(vuelo.getEstado())) {
                vuelo.cancelar();
                System.out.println("Vuelo " + numeroVuelo + " cancelado exitosamente");
            } else {
                System.out.println("El vuelo ya está cancelado");
            }
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private static void reactivarVuelo() {
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados");
            return;
        }

        System.out.println("\n=== REACTIVAR VUELO ===");
        System.out.println("Vuelos cancelados disponibles:");
        vuelos.stream()
                .filter(v -> "CANCELADO".equals(v.getEstado()))
                .forEach(v -> System.out.println("- " + v.getNumero() + ": " + v.getOrigen() + " a " + v.getDestino()));

        System.out.print("Ingrese el número del vuelo a reactivar: ");
        String numeroVuelo = scanner.nextLine();

        Vuelo vuelo = vuelos.stream()
                .filter(v -> v.getNumero().equals(numeroVuelo))
                .findFirst()
                .orElse(null);

        if (vuelo != null) {
            if ("CANCELADO".equals(vuelo.getEstado())) {
                vuelo.reactivar();
                System.out.println("Vuelo " + numeroVuelo + " reactivado exitosamente");
            } else {
                System.out.println("El vuelo no está cancelado");
            }
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private static void gestionarReservas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE RESERVAS ===");
            System.out.println("1. Crear reserva");
            System.out.println("2. Confirmar reserva");
            System.out.println("3. Cancelar reserva");
            System.out.println("4. Ver reservas");
            System.out.println("5. Volver");
            System.out.println("6. Ver factura");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> crearReserva();
                case 2 -> confirmarReserva();
                case 3 -> cancelarReserva();
                case 4 -> listarReservas();
                case 5 -> volver = true;
                case 6 -> verFactura();
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void crearReserva() {
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos disponibles");
            return;
        }

        // Listar pasajeros
        List<Pasajero> pasajeros = usuarios.stream()
                .filter(u -> u instanceof Pasajero)
                .map(u -> (Pasajero) u)
                .toList();

        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados");
            return;
        }

        System.out.println("\n=== CREAR RESERVA ===");
        System.out.println("Pasajeros disponibles:");
        pasajeros.forEach(p -> System.out.println("- " + p.getNombre() + " (" + p.getCorreo() + ")"));

        System.out.print("Seleccione correo del pasajero: ");
        String correoPasajero = scanner.nextLine();

        Pasajero pasajero = pasajeros.stream()
                .filter(p -> p.getCorreo().equals(correoPasajero))
                .findFirst()
                .orElse(null);

        if (pasajero == null) {
            System.out.println("Pasajero no encontrado");
            return;
        }

        // Listar vuelos con asientos disponibles y activos
        System.out.println("\nVuelos disponibles (solo activos):");
        vuelos.stream()
                .filter(v -> "ACTIVO".equals(v.getEstado()))
                .forEach(v -> {
                    System.out.println("\nVuelo: " + v.getNumero());
                    System.out.println("Ruta: " + v.getOrigen() + " - " + v.getDestino());
                    System.out.println("Asientos disponibles: " + v.getAsientosDisponibles().size());
                });

        System.out.print("Seleccione número de vuelo: ");
        String numeroVuelo = scanner.nextLine();

        Vuelo vuelo = vuelos.stream()
                .filter(v -> v.getNumero().equals(numeroVuelo))
                .findFirst()
                .orElse(null);

        if (vuelo == null) {
            System.out.println("Vuelo no encontrado");
            return;
        }

        if ("CANCELADO".equals(vuelo.getEstado())) {
            System.out.println("No se pueden hacer reservas en vuelos cancelados");
            return;
        }

        // Validar que tenga azafatos asignados
        if (vuelo.getAzafatos() == null || vuelo.getAzafatos().isEmpty()) {
            System.out.println("No se puede reservar este vuelo porque no tiene azafatos asignados");
            return;
        }

        // Mostrar asientos disponibles
        System.out.println("\nAsientos disponibles:");
        vuelo.getAsientosDisponibles().forEach(a -> System.out.print(a.getNumero() + " "));
        System.out.print("\nSeleccione asiento: ");
        String asiento = scanner.nextLine();

        try {
            Reserva reserva = new Reserva("RES-" + (vuelo.getReservas().size() + 1), pasajero, vuelo, asiento);
            System.out.println("Reserva creada exitosamente (pendiente de confirmación)");
        } catch (Exception e) {
            System.out.println("Error al crear reserva: " + e.getMessage());
        }
    }

    private static void confirmarReserva() {
        listarReservas();

        System.out.print("\nIngrese el ID de la reserva a confirmar (ej: RES-1): ");
        String idReserva = scanner.nextLine();

        boolean encontrada = false;

        for (Vuelo vuelo : vuelos) {
            for (Reserva reserva : vuelo.getReservas()) {
                if (reserva.getId().equalsIgnoreCase(idReserva)) {
                    encontrada = true;
                    if (reserva.getEstado().equals("PENDIENTE")) {
                        System.out.println("💳 Métodos de pago:");
                        System.out.println("1. Efectivo");
                        System.out.println("2. Tarjeta");
                        System.out.print("Seleccione una opción: ");
                        int opcionPago = leerOpcion();

                        String metodoPago = (opcionPago == 1) ? "EFECTIVO" : "TARJETA";
                        Pago pago = new Pago(350.00, metodoPago, reserva);
                        pago.procesarPago();
                        reserva.confirmar();
                        System.out.println("✅ Reserva confirmada y pago registrado.");
                    } else {
                        System.out.println("La reserva ya está " + reserva.getEstado());
                    }
                    return;
                }
            }
        }

        if (!encontrada) {
            System.out.println("❌ No se encontró la reserva con ID: " + idReserva);
        }
    }

    private static void cancelarReserva() {
        listarReservas();
        System.out.print("\nIngrese el ID de la reserva a cancelar: ");
        String idReserva = scanner.nextLine();

        for (Vuelo vuelo : vuelos) {
            for (Reserva reserva : vuelo.getReservas()) {
                if (reserva.getId().equalsIgnoreCase(idReserva)) {
                    if (reserva.getEstado().equals("COMPLETADA")) {
                        System.out.println("🔄 Reembolsando pago...");
                    }
                    reserva.cancelar();
                    System.out.println("✅ Reserva cancelada exitosamente.");
                    return;
                }
            }
        }
        System.out.println("❌ Reserva no encontrada.");
    }

    private static void listarReservas() {
        if (vuelos.stream().allMatch(v -> v.getReservas().isEmpty())) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        System.out.println("\n=== LISTADO DE RESERVAS ===");
        vuelos.forEach(vuelo -> {
            if (!vuelo.getReservas().isEmpty()) {
                System.out.println("\n✈️ Vuelo: " + vuelo.getNumero() + " (" + vuelo.getOrigen() + " → " + vuelo.getDestino() + ")");
                System.out.println("Estado vuelo: " + vuelo.getEstado());
                vuelo.getReservas().forEach(reserva -> {
                    System.out.println("   🆔 ID: " + reserva.getId());
                    System.out.println("   👤 Pasajero: " + reserva.getPasajero().getNombre());
                    System.out.println("   💺 Asiento: " + reserva.getAsiento());
                    System.out.println("   📌 Estado: " + reserva.getEstado());
                    System.out.println("   ─────────────────────");
                });
            }
        });
    }

    private static void verFactura() {
        listarReservas();
        System.out.print("\nIngrese el ID de la reserva para generar factura: ");
        String idReserva = scanner.nextLine();

        for (Vuelo vuelo : vuelos) {
            for (Reserva reserva : vuelo.getReservas()) {
                if (reserva.getId().equalsIgnoreCase(idReserva)) {
                    if (!reserva.getEstado().equals("COMPLETADA")) {
                        System.out.println("❌ La reserva aún no está completada. No se puede generar factura.");
                        return;
                    }

                    Ticket ticket = new Ticket("TCK-" + idReserva, reserva);
                    ticket.mostrarFactura();
                    return;
                }
            }
        }
        System.out.println("❌ Reserva no encontrada.");
    }

    private static void verMiPerfil() {
        sesionActual.getUsuario().verPerfil();
    }

    private static void cerrarSesion() {
        sesionActual.cerrar();
    }
}