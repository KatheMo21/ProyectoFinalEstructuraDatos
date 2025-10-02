package controller;

import Model.Producto;
import Model.Usuario;
import Model.Venta;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VentaController {

    private List<Venta> listaVentas;
    private double saldoVenta;

    // Códigos ANSI
    final String RESET   = "\u001B[0m";
    final String RED     = "\u001B[31m";
    final String GREEN   = "\u001B[32m";
    final String YELLOW  = "\u001B[33m";
    final String BLUE    = "\u001B[34m";
    final String PURPLE  = "\u001B[35m";
    final String CYAN    = "\u001B[36m";
    final String WHITE   = "\u001B[37m";

    public double getSaldoVenta() {
        return saldoVenta;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public VentaController() {
        this.listaVentas = new ArrayList<>();
        this.saldoVenta = 0.0;
    }

    //////////////////// REGISTRAR VENTA ////////////////////
    public void registrarVenta(Scanner scanner, Usuario usuario, 
                               ProductoController productoController,
                               UsuarioController usuarioController) {

        productoController.listarProductos();
        System.out.print(BLUE + "ID del producto a vender: " + RESET);

        int idProducto;
        try {
            idProducto = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println( ">>> ID inválido. Debe ser un número. <<<");
            return;
        }

        Producto p = productoController.buscarProductos(idProducto);
        if (p == null) {
            System.out.println( ">>> Producto no encontrado. <<<");
            return;
        }

        System.out.print(BLUE + "Cantidad a vender: ");
        int cantidad;
        try {
            cantidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(">>> Cantidad inválida. <<<");
            return;
        }

        if (cantidad <= 0 || p.getStock() < cantidad) {
            System.out.println(">>> Stock insuficiente. Stock actual: " + p.getStock());
            return;
        }

        Usuario admin = usuarioController.buscarAdministrador();
        if (admin == null) {
            System.out.println(">>> No hay administrador en el sistema. <<<");
            return;
        }

        double totalVenta = p.getPrecio() * cantidad;
        if (totalVenta > usuario.getSaldo()) {
            System.out.println(">>> Saldo insuficiente para la compra. <<<");
            return;
        }

        // Actualizaciones
        usuario.setSaldo(usuario.getSaldo() - totalVenta);
        admin.setSaldo(admin.getSaldo() + totalVenta);
        saldoVenta += totalVenta;
        p.setStock(p.getStock() - cantidad);

        listaVentas.add(new Venta(usuario, p, cantidad, totalVenta));

        System.out.println( ">>> Compra realizada con éxito. <<<" );
    }

    //////////////////// MOSTRAR VENTAS ////////////////////
    public void mostrarVentas() {
        if (listaVentas.isEmpty()) {
            System.out.println(">>> No hay ventas registradas. <<<");
            return;
        }

        System.out.println(CYAN + "\n+-----------------+-----------------+----------+------------+" + RESET);
        System.out.printf("| %-15s | %-15s | %-8s | %-10s |%n", 
                          "Usuario", "Producto", "Cantidad", "Monto");
        System.out.println(CYAN + "+-----------------+-----------------+----------+------------+" + RESET);

        for (Venta v : listaVentas) {
            System.out.printf("| %-15s | %-15s | %-8d | %-10.2f |%n",
                    v.getUsuario().getNombreUsuario(),
                    v.getProducto().getNombreProducto(),
                    v.getCantidad(),
                    v.getMonto());
        }

        System.out.println(CYAN + "+-----------------+-----------------+----------+------------+" + RESET);
        System.out.println(YELLOW + "Total acumulado en caja: $" + saldoVenta + RESET);
    }

    //////////////////// MOSTRAR HISTORIAL POR USUARIO ////////////////////
    public void mostrarHistorialUsuario(Usuario usuario) {
        System.out.println(PURPLE + "\nHistorial de compras de " + usuario.getNombreUsuario() + ":" + RESET);

        boolean tieneCompras = false;
        for (Venta v : listaVentas) {
            if (v.getUsuario().equals(usuario)) {
                System.out.printf("| Producto: %-15s | Cantidad: %-5d | Total: $%-8.2f |%n",
                        v.getProducto().getNombreProducto(),
                        v.getCantidad(),
                        v.getMonto());
                tieneCompras = true;
            }
        }
        if (!tieneCompras) {
            System.out.println(">>> No tiene compras registradas. <<<");
        }
    }

    //////////////////// HISTORIAL COMPLETO ////////////////////
    public void historialVentas() {
        System.out.println(PURPLE + "\nHISTORIAL COMPLETO DE VENTAS:" + RESET);
        mostrarVentas();
    }

    //////////////////// MENU CRUD VENTAS ////////////////////
    public void menuCrudVentas(Scanner scanner, Usuario usuario, 
                               ProductoController productoController, 
                               UsuarioController usuarioController) {
        while (true) {
            System.out.println(CYAN + "  ╔═══════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "  ║                  CRUD DE VENTAS               ║" + RESET);
            System.out.println("  ║                                               ║");
            System.out.println(GREEN + "  ║  -1. Registrar venta                          ║" + RESET);
            System.out.println(GREEN + "  ║  -2. Listar ventas                            ║" + RESET);
            System.out.println(GREEN + "  ║  -3. Ver ventas por producto                  ║" + RESET);
            System.out.println(GREEN + "  ║  -4. Ver historial de un usuario              ║" + RESET);
            System.out.println(YELLOW + "  ║  -5. Volver                                   ║" + RESET);
            System.out.println("  ║                                               ║");
            System.out.println(PURPLE + "  ║  -> Seleccione una opción:                    ║" + RESET);
            System.out.println(CYAN + "  ╚═══════════════════════════════════════════════╝" + RESET);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    registrarVenta(scanner, usuario, productoController, usuarioController);
                    break;
                case "2":
                    mostrarVentas();
                    break;
                case "3":
                    System.out.print(BLUE + "Ingrese nombre del producto: " + RESET);
                    String nombreProducto = scanner.nextLine();
                    mostrarVentasPorProducto(nombreProducto);
                    break;
                case "4":
                    mostrarHistorialUsuario(usuario);
                    break;
                case "5":
                    return;
                default:
                    System.out.println(">>> Opción inválida. <<<");
            }
        }
    }

    //////////////////// VENTAS POR PRODUCTO ////////////////////
    public void mostrarVentasPorProducto(String nombreProducto) {
        boolean encontrado = false;

        System.out.println(CYAN + "\nVentas del producto: " + nombreProducto + RESET);
        for (Venta v : listaVentas) {
            if (v.getProducto().getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                System.out.printf("| Usuario: %-15s | Cantidad: %-5d | Total: $%-8.2f |%n",
                        v.getUsuario().getNombreUsuario(),
                        v.getCantidad(),
                        v.getMonto());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(">>> No hay ventas registradas para " + nombreProducto + " <<<");
        }
    }
}
