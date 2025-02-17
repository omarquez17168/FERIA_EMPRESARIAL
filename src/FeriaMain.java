import models.*;
import java.util.List;
import java.util.Scanner;

public class FeriaMain {
    public static void main(String[] args) {
        Feria feria = new Feria("Feria Empresarial 2025", "Centro de Convenciones");
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión de la Feria Empresarial ---");
            System.out.println("1. Registrar Empresa");
            System.out.println("2. Editar Empresa");
            System.out.println("3. Eliminar Empresa");
            System.out.println("4. Registrar Visitante");
            System.out.println("5. Editar Visitante");
            System.out.println("6. Eliminar Visitante");
            System.out.println("7. Asignar Stand a Empresa");
            System.out.println("8. Registrar Visita y Calificación");
            System.out.println("9. Mostrar Stands Ocupados");
            System.out.println("10. Mostrar Empresas y Stands");
            System.out.println("11. Mostrar Comentarios de Stands");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarEmpresa(feria, scanner);
                    break;
                case 2:
                    editarEmpresa(feria, scanner);
                    break;
                case 3:
                    eliminarEmpresa(feria, scanner);
                    break;
                case 4:
                    registrarVisitante(feria, scanner);
                    break;
                case 5:
                    editarVisitante(feria, scanner);
                    break;
                case 6:
                    eliminarVisitante(feria, scanner);
                    break;
                case 7:
                    asignarStand(feria, scanner);
                    break;
                case 8:
                    registrarVisita(feria, scanner);
                    break;
                case 9:
                    mostrarStandsOcupados(feria);
                    break;
                case 10:
                    mostrarEmpresasYStands(feria);
                    break;
                case 11:
                    mostrarComentarios(feria, scanner);
                    break;
                case 12:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 12);

        scanner.close();
    }

    private static void registrarEmpresa(Feria feria, Scanner scanner) {
        System.out.print("Ingrese nombre de la empresa: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese sector de la empresa: ");
        String sector = scanner.nextLine();
        System.out.print("Ingrese correo de contacto: ");
        String contacto = scanner.nextLine();

        Empresa empresa = new Empresa(nombre, sector, contacto);
        feria.registrarEmpresa(empresa);
        System.out.println("Empresa registrada exitosamente.");
       //modificacion nombre de empresa
    }

    private static void editarEmpresa(Feria feria, Scanner scanner) {
    // Mostrar la lista de empresas antes de editar
    System.out.println("\n--- Lista de Empresas Registradas ---");
    List<Empresa> empresas = feria.listarEmpresas();

    // Si no hay empresas registradas, mostrar mensaje y salir
    if (empresas.isEmpty()) {
        System.out.println("No hay empresas registradas.");
        return;
    }

    // Listar todas las empresas con un índice
    for (int i = 0; i < empresas.size(); i++) {
        System.out.println((i + 1) + ". " + empresas.get(i).getNombre() + " - " + empresas.get(i).getSector());
    }

    // Pedir el nombre de la empresa a editar
    System.out.print("\nIngrese el nombre de la empresa a editar: ");
    String nombre = scanner.nextLine();
    Empresa empresa = feria.buscarEmpresa(nombre);

    if (empresa != null) {
        // Mostrar la información actual de la empresa
        System.out.println("\n--- Información Actual de la Empresa ---");
        System.out.println("Nombre: " + empresa.getNombre());
        System.out.println("Sector: " + empresa.getSector());
        System.out.println("Contacto: " + empresa.getContacto());

        // Pedir nuevos valores, si están vacíos, mantener el actual
        System.out.print("\nIngrese nuevo sector (deje en blanco para mantener): ");
        String nuevoSector = scanner.nextLine();
        if (nuevoSector.isEmpty()) {
            nuevoSector = empresa.getSector();
        }

        System.out.print("Ingrese nuevo contacto (deje en blanco para mantener): ");
        String nuevoContacto = scanner.nextLine();
        if (nuevoContacto.isEmpty()) {
            nuevoContacto = empresa.getContacto();
        }

        // Editar la empresa con los nuevos valores
        feria.editarEmpresa(nombre, nuevoSector, nuevoContacto);
        System.out.println("\nEmpresa actualizada exitosamente.");
    } else {
        System.out.println("\nEmpresa no encontrada.");
    }
}

    private static void eliminarEmpresa(Feria feria, Scanner scanner) {
        System.out.print("Ingrese nombre de la empresa a eliminar: ");
        String nombre = scanner.nextLine();

        if (feria.eliminarEmpresa(nombre)) {
            System.out.println("Empresa eliminada exitosamente.");
        } else {
            System.out.println("Empresa no encontrada.");
        }
    }

    private static void mostrarEmpresasYStands(Feria feria) {
        System.out.println("\n--- Empresas y Stands ---");
        for (Empresa empresa : feria.listarEmpresas()) {
            Stand stand = empresa.getStand();
            System.out.println("Empresa: " + empresa.getNombre() + " - Sector: " + empresa.getSector() +
                    " - Stand: " + (stand != null ? stand.getNumero() + " (" + stand.getUbicacion() + ")" : "Sin stand"));
        }
    }

    private static void registrarVisitante(Feria feria, Scanner scanner) {
        System.out.print("Ingrese nombre del visitante: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese número de identificación: ");
        String identificacion = scanner.nextLine();
        System.out.print("Ingrese correo electrónico: ");
        String correo = scanner.nextLine();

        Visitante visitante = new Visitante(nombre, identificacion, correo);
        feria.registrarVisitante(visitante);
        System.out.println("Visitante registrado exitosamente.");
    }

    private static void asignarStand(Feria feria, Scanner scanner) {
        // Mostrar pabellones disponibles
        System.out.println("\n--- Pabellones Disponibles ---");
        List<Pabellon> pabellones = feria.getPabellones();
        for (int i = 0; i < pabellones.size(); i++) {
            System.out.println((i + 1) + ". " + pabellones.get(i).getNombre());
        }

        // Seleccionar pabellón
        System.out.print("\nSeleccione el número del pabellón: ");
        int pabellonIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (pabellonIndex < 0 || pabellonIndex >= pabellones.size()) {
            System.out.println(" Pabellón inválido.");
            return;
        }

        Pabellon pabellonSeleccionado = pabellones.get(pabellonIndex);

        // Seleccionar tamaño de stand
        System.out.print("\nIngrese el tamaño del stand (Pequeño, Mediano, Grande): ");
        String tamañoBuscado = scanner.nextLine();

        // Mostrar stands disponibles por tamaño en el pabellón seleccionado
        List<Stand> libres = pabellonSeleccionado.getStandsLibresPorTamaño(tamañoBuscado);
        if (libres.isEmpty()) {
            System.out.println(" No hay stands disponibles de este tamaño en este pabellón.");
            return;
        }

        System.out.println("\n--- Stands Disponibles en " + pabellonSeleccionado.getNombre() + " (" + tamañoBuscado + ") ---");
        for (Stand stand : libres) {
            System.out.println("Stand #" + stand.getNumero() + " - " + stand.getUbicacion() + " - Tamaño: " + stand.getTamaño());
        }

        // Seleccionar stand
        System.out.print("\nIngrese el número del stand a asignar: ");
        int numStand = scanner.nextInt();
        scanner.nextLine();

        // Seleccionar empresa
        System.out.print("\nIngrese el nombre de la empresa: ");
        String nombreEmpresa = scanner.nextLine();
        Empresa empresa = feria.buscarEmpresa(nombreEmpresa);

        if (empresa != null) {
            feria.asignarStand(empresa, pabellonSeleccionado, numStand);
        } else {
            System.out.println("Empresa no encontrada.");
        }
    }

    private static void mostrarStandsOcupados(Feria feria) {
        System.out.println("\n--- Stands Ocupados en la Feria ---");
        for (Pabellon pabellon : feria.getPabellones()) {
            List<Stand> ocupados = pabellon.getStandsOcupados();
            if (!ocupados.isEmpty()) {
                System.out.println("\nPabellón " + pabellon.getNombre() + ":");
                for (Stand stand : ocupados) {
                    System.out.println("Stand #" + stand.getNumero() + " - Empresa: " + stand.getEmpresa().getNombre() + " - Tamaño: " + stand.getTamaño());
                }
            }
        }
    }

    private static void editarVisitante(Feria feria, Scanner scanner) {
        System.out.print("Ingrese la identificación del visitante a editar: ");
        String identificacion = scanner.nextLine();
        Visitante visitante = feria.buscarVisitantePorId(identificacion);

        if (visitante != null) {
            System.out.println("\n--- Información Actual ---");
            System.out.println("Nombre: " + visitante.getNombre());
            System.out.println("Correo: " + visitante.getCorreo());

            System.out.print("Ingrese nuevo nombre (deje en blanco para mantener): ");
            String nuevoNombre = scanner.nextLine();
            if (nuevoNombre.isEmpty()) nuevoNombre = visitante.getNombre();

            System.out.print("Ingrese nuevo correo (deje en blanco para mantener): ");
            String nuevoCorreo = scanner.nextLine();
            if (nuevoCorreo.isEmpty()) nuevoCorreo = visitante.getCorreo();

            feria.editarVisitante(identificacion, nuevoNombre, nuevoCorreo);
            System.out.println("Visitante actualizado correctamente.");
        } else {
            System.out.println("Visitante no encontrado.");
        }
    }

    private static void eliminarVisitante(Feria feria, Scanner scanner) {
        System.out.print("Ingrese la identificación del visitante a eliminar: ");
        String identificacion = scanner.nextLine();

        if (feria.eliminarVisitante(identificacion)) {
            System.out.println("Visitante eliminado correctamente.");
        } else {
            System.out.println("Visitante no encontrado.");
        }
    }

    private static void registrarVisita(Feria feria, Scanner scanner) {
        System.out.print("Ingrese la identificación del visitante: ");
        String identificacion = scanner.nextLine();
        Visitante visitante = feria.buscarVisitantePorId(identificacion);

        if (visitante == null) {
            System.out.println("Visitante no encontrado.");
            return;
        }

        System.out.print("Ingrese número del stand visitado: ");
        int numStand = scanner.nextInt();
        scanner.nextLine();

        Stand stand = null;
        for (Pabellon pabellon : feria.getPabellones()) {
            stand = pabellon.buscarStand(numStand);
            if (stand != null) break;
        }

        if (stand == null) {
            System.out.println("⚠Stand no encontrado.");
            return;
        }

        System.out.print("Ingrese calificación (1-5): ");
        int calificacion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese comentario: ");
        String mensaje = scanner.nextLine();

        Comentario comentario = new Comentario(visitante, stand, calificacion, mensaje);
        stand.agregarComentario(comentario);
        System.out.println("Comentario registrado correctamente.");
    }

    private static void mostrarComentarios(Feria feria, Scanner scanner) {
        System.out.print("Ingrese el número del stand: ");
        int numStand = scanner.nextInt();
        scanner.nextLine();

        Stand stand = null;
        for (Pabellon pabellon : feria.getPabellones()) {
            stand = pabellon.buscarStand(numStand);
            if (stand != null) break;
        }

        if (stand == null) {
            System.out.println("Stand no encontrado.");
            return;
        }

        System.out.println("\n--- Comentarios para Stand #" + numStand + " ---");
        for (Comentario comentario : stand.getComentarios()) {
            System.out.println(comentario.getFecha() + " - " + comentario.getVisitante().getNombre() +
                    " - " + comentario.getCalificacion() + "★: " + comentario.getMensaje());
        }

        double promedio = stand.calcularPromedioCalificaciones();
        System.out.println("\n Calificación promedio del stand: " + promedio + " estrellas.");
    }


}