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

    public void registrarVenta(Scanner scanner, Usuario usuario, ProductoController productoController,
            UsuarioController usuarioController) {

        // Mostrar productos disponibles
        productoController.listarProductos();
        System.out.print("ID del producto a vender: ");

        // Validar ID del producto
        int idProducto;
        String idString = scanner.nextLine();
        try {
            idProducto = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Debe ser un número.");
            return;
        }

        Producto p = productoController.buscarProductos(idProducto);
        if (p == null) {
            System.out.println("Producto no encontrado. Verifica el ID ingresado.");
            return;
        }

        // Validar cantidad
        System.out.print("Cantidad a vender: ");
        int cantidad;
        String cantidadStr = scanner.nextLine();
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida. Debe ser un número.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a cero.");
            return;
        }

        if (p.getStock() < cantidad) {
            System.out.println("No hay suficiente stock. Stock actual: " + p.getStock());
            return;
        }

        // Buscar administrador
        Usuario admin = usuarioController.buscarAdministrador();
        if (admin == null) {
            System.out.println("No se encontró un usuario administrador en el sistema.");
            return;
        }

        // Calcular total de la venta
        double totalVentaIndividual = p.getPrecio() * cantidad;

        // Verificar saldo del usuario
        if (totalVentaIndividual > usuario.getSaldo()) {
            System.out.println("Saldo insuficiente para realizar la compra.");
            return;
        }

        // Actualiza los saldos
        usuario.setSaldo(usuario.getSaldo() - totalVentaIndividual);
        admin.setSaldo(admin.getSaldo() + totalVentaIndividual);
        saldoVenta += totalVentaIndividual;

        // Actualiza el stock
        p.setStock(p.getStock() - cantidad);

        // Registra la venta
        listaVentas.add(new Venta(usuario, p, cantidad, totalVentaIndividual));

        System.out.println("✅ Compra realizada con éxito.");
        System.out.println("Saldo restante del usuario: " + usuario.getSaldo());
        System.out.println("Venta registrada: " + usuario.getNombreUsuario()
                + " compró " + cantidad + " de " + p.getNombreProducto()
                + " por $" + totalVentaIndividual);
        System.out.println("Stock actualizado de " + p.getNombreProducto() + ": " + p.getStock());
    }

    public void mostrarVentas() {
        System.out.println("\n=== LISTA DE VENTAS ===");
        if (listaVentas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta v : listaVentas) {
                System.out.println("Usuario: " + v.getUsuario().getNombreUsuario()
                        + " | Producto: " + v.getProducto().getNombreProducto()
                        + " | Cantidad: " + v.getCantidad()
                        + " | Total de la venta: " + v.getMonto());
            }
            System.out.println("=== Total acumulado en caja: " + saldoVenta + " ===");
        }
    }

    // METODO PARA VER EL HISTORIAL DEL USUARIO
    public void mostrarHistorialUsuario(Usuario usuario) {
        System.out.println("\nTus compras:");
        for (Venta venta : listaVentas) {
            if (venta.getUsuario().equals(usuario)) {
                System.out.println("| Producto: " + venta.getProducto().getNombreProducto()
                        + " | Cantidad: " + venta.getCantidad()
                        + " | Total: $" + venta.getMonto());
            }

        }
    }

    // METODO PARA MOSTRAR EL HISTORIAL DE VENTAS
    public void historialVentas() {
        System.out.println("\nHistorial de ventas:");
        for (Venta venta : listaVentas) {
            System.out.println(venta);
        }
    }

    // muestra la cantidad de ventas por producto
    public void mostrarVentasPorProducto(String nombreProducto) {
        boolean encontrado = false;
        for (Venta v : listaVentas) {
            if (v.getProducto().getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                System.out.println("Usuario: " + v.getUsuario().getNombreUsuario()
                        + " | Cantidad: " + v.getCantidad()
                        + " | Total: $" + v.getMonto());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay ventas registradas para el producto: " + nombreProducto);
        }
    }

}
