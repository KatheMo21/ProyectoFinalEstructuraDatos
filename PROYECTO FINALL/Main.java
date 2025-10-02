
import Model.Usuario;
import controller.ProductoController;
import controller.ProveedorController;
import controller.UsuarioController;
import controller.VentaController;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProveedorController proveedorController = new ProveedorController();
        ProductoController productoController = new ProductoController(proveedorController);
        UsuarioController usuarioController = new UsuarioController();
        VentaController ventaController = new VentaController();

        //usuarioController.crearUsuarioAdmin_Cliente();
        productoController.subirProductos();

        Usuario usuarioLogueado = null;

        // Códigos ANSI para colores
        final String RESET   = "\u001B[0m";   // reset (volver a normal)
        final String RED     = "\u001B[31m";  // rojo
        final String GREEN   = "\u001B[32m";  // verde
        final String YELLOW  = "\u001B[33m";  // amarillo
        final String BLUE    = "\u001B[34m";  // azul
        final String PURPLE  = "\u001B[35m";  // morado
        final String CYAN    = "\u001B[36m";  // cian
        final String WHITE   = "\u001B[37m";  // blanco

                System.out.println(YELLOW + "  ╔═══════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(YELLOW + "  ║        BIENVENIDO A LA TIENDA VIRTUAL INTENCIONADAMENTE       ║" + RESET);
                System.out.println(YELLOW + "  ╚═══════════════════════════════════════════════════════════════╝" + RESET);

                System.out.println(WHITE+ "══════ Recuerde que los usuarios disponibles en la tienda son: ═════"+ RESET);
                System.out.println(CYAN + "  ╔═══════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "  ║                   ADMINISTRADOR ║ CLIENTE                     ║" + RESET);
                System.out.println(CYAN + "  ╚═══════════════════════════════════════════════════════════════╝" + RESET);
        while (true) {

            if (usuarioLogueado == null) {
                System.out.println(CYAN + "  ╔═══════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "  ║        -1. Iniciar sesión                                     ║" + RESET);
                System.out.println(CYAN + "  ║        -2. Registarse                                         ║" + RESET);
                System.out.println(CYAN + "  ║        -3. Salir                                              ║" + RESET);
                System.out.println(CYAN + "  ║        -> Seleccione una opción                               ║" + RESET);
                System.out.println(CYAN + "  ╚═══════════════════════════════════════════════════════════════╝" + RESET);
                String opcion = scanner.nextLine();
                switch (opcion) {
                    case "1":
                        usuarioLogueado = usuarioController.iniciarSesion(scanner);
                        break;
                    case "2":
                        usuarioController.registrarNuevoUsuario(scanner);
                        break;
                    case "3":
                        System.out.println("¡Hasta luego!");
                            scanner.close();
                        return;
                    default:
                        System.out.println("Opción inválida.");
                }
            } else if (usuarioLogueado.getRol().equals("ADMIN")) {

                System.out.println(CYAN + "  ╔═══════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "  ║            TIENDA VIRTUAL INTENCIONADAMENTE   ║" + RESET);
                System.out.println(CYAN + "  ╚═══════════════════════════════════════════════╝" + RESET);
                System.out.println(PURPLE + "  ║               MENU ADMINISTRADOR              ║" + RESET);
                System.out.println("  ║                                               ║");
                System.out.println(GREEN + "  ║  -1. Gestionar Usuarios                       ║" + RESET);
                System.out.println(GREEN + "  ║  -2. Gestionar Productos                      ║" + RESET);
                System.out.println(GREEN + "  ║  -3. Gestionar Proveedores                    ║" + RESET);
                System.out.println(YELLOW + "  ║  -4. Ver historial de Ventas                  ║" + RESET);
                System.out.println(YELLOW + "  ║  -5. Consultar dinero disponible              ║" + RESET);
                System.out.println(YELLOW + "  ║  -6. Cerrar sesión                            ║" + RESET);
                System.out.println("  ║                                               ║");
                System.out.println(PURPLE + "  ║  -> Seleccione una opción:                    ║" + RESET);
                System.out.println(CYAN + "  ╚═══════════════════════════════════════════════╝" + RESET);

                String opcion = scanner.nextLine();
                switch (opcion) {
                    case "1":
                        usuarioController.menuCrudUsuarios(scanner);
                        break;
                    case "2":
                        productoController.menuCrudProductos(scanner);
                        break;
                    case "3":
                        proveedorController.menuCrudProveedores(scanner);
                        break;
                    case "4":
                        ventaController.historialVentas();
                        break;
                    case "5":
                        System.out.println("Saldo del administrador: " + usuarioLogueado.getSaldo());
                        System.out.println("Saldo acumulado en ventas (caja): " + ventaController.getSaldoVenta());
                        break;

                    case "6":
                        usuarioLogueado = null;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } else {
                System.out.println(CYAN + "  ╔═══════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "  ║        TIENDA VIRTUAL INTENCIONADAMENTE       ║" + RESET);
                System.out.println(CYAN + "  ╚═══════════════════════════════════════════════╝" + RESET);
                System.out.println(PURPLE + "  ║               MENU CLIENTE              ║" + RESET);
                System.out.println("  ║                                               ║");
                System.out.println(GREEN + "  ║  -1. Ver productos disponibles                ║" + RESET);
                System.out.println(GREEN + "  ║  -2. Comprar Productos                        ║" + RESET);
                System.out.println(GREEN + "  ║  -3. Ver mi historial de compras              ║" + RESET);
                System.out.println(YELLOW + "  ║  -4. Consultar saldo disponible               ║" + RESET);
                System.out.println(YELLOW + "  ║  -5. Cerrar sesióm                            ║" + RESET);
                System.out.println("  ║                                               ║");
                System.out.println(PURPLE + "  ║  -> Seleccione una opción:                    ║" + RESET);
                System.out.println(CYAN + "  ╚═══════════════════════════════════════════════╝" + RESET);

                String opcion = scanner.nextLine();
                switch (opcion) {
                    case "1":
                        productoController.listarProductos();
                        break;
                    case "2":
                        ventaController.registrarVenta(scanner, usuarioLogueado, productoController, usuarioController);
                        System.out.println("Dinero disponible: " + usuarioLogueado.getSaldo());
                        break;
                    case "3":
                        ventaController.mostrarHistorialUsuario(usuarioLogueado);
                        break;

                    case "4":
                        System.out.println("Dinero disponible (cliente): " + usuarioLogueado.getSaldo());
                        break;
                    case "5":
                        usuarioLogueado = null;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            }
        }
    }
}
