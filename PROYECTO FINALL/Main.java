import controller.ProductoController;
import controller.ProveedorController;
import controller.UsuarioController;
import controller.VentaController;
import Model.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProveedorController proveedorController = new ProveedorController();
        ProductoController productoController = new ProductoController(proveedorController);
        UsuarioController usuarioController = new UsuarioController();
        VentaController ventaController = new VentaController();

        usuarioController.crearUsuarioAdmin_Cliente();
        productoController.subirProductos();

        Usuario usuarioLogueado = null;

        while (true) {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1) CRUD Usuarios");
            System.out.println("2) CRUD Productos");
            System.out.println("3) CRUD Proveedores");
            System.out.println("4) Ver Ventas");
            System.out.println("5) Iniciar Sesión");
            System.out.println("6) Registrar Venta");
            System.out.println("7) Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    usuarioController.menuCRUD(scanner);
                    break;
                case "2":
                    productoController.menuCRUD(scanner);
                    break;
                case "3":
                    proveedorController.menuCRUD(scanner);
                    break;
                case "4":
                    ventaController.mostrarVentas();
                    break;
                case "5":
                    usuarioLogueado = usuarioController.iniciarSesion(scanner);
                    break;
                case "6":
                    if (usuarioLogueado == null) {
                        System.out.println("Debe iniciar sesión primero.");
                        break;
                    }
                    productoController.listarProductos();
                    System.out.print("Ingrese ID del producto a comprar: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    var producto = productoController.buscarProductos(id);
                    if (producto == null) {
                        System.out.println("Producto no encontrado.");
                        break;
                    }
                    System.out.print("Cantidad: ");
                    int cantidad = Integer.parseInt(scanner.nextLine());
                    double monto = producto.getPrecio() * cantidad;
                    ventaController.registrarVenta(usuarioLogueado, producto, cantidad, monto);
                    break;
                case "7":
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
