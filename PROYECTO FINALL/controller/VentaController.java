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

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public VentaController() {
        this.listaVentas = new ArrayList<>();
        this.saldoVenta = 0.0;
    }

    public void registrarVenta(Scanner scanner, Usuario usuario, ProductoController productoController,
            UsuarioController usuarioController) {

        productoController.listarProductos();
        System.out.print("ID del producto a vender: ");

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
            System.out.println("No hay productos Stock actual: " + p.getStock());
            return;
        }

        

        // Buscar el admin real
        Usuario admin = usuarioController.buscarAdministrador();
        if (admin == null) {
            System.out.println("No se encontró el usuario administrador.");
            return;
        }

        // Verificar si el usuario tiene suficiente dinero
        // ... código para leer producto y cantidad ...
        double totalVentaIndividual = p.getPrecio() * cantidad;
        double saldoAdministrador = admin.getSaldo();
        saldoAdministrador += totalVentaIndividual;
        admin.setSaldo(saldoAdministrador);
        // Realizar la transacción
        if (totalVentaIndividual <= usuario.getSaldo()) {
            usuario.setSaldo(usuario.getSaldo() - totalVentaIndividual);
            saldoVenta += totalVentaIndividual; // acumulado general
            p.setStock(p.getStock() - cantidad);
            listaVentas.add(new Venta(usuario, p, cantidad, totalVentaIndividual));

            System.out.println("Compra realizada. Saldo restante: " + usuario.getSaldo());
        } else {
            System.out.println("Saldo insuficiente.");
            return;
        }

        // DISMINUIR STOCK DEL PRODUCTO
        int stockActual = p.getStock();
    
        if (stockActual >= cantidad) {
        int operacion = (stockActual - cantidad);
        
        p.setStock(operacion);

        // Registrar la venta
        System.out.println("Venta registrada: " + usuario.getNombreUsuario() + 
        " compró " + cantidad + " de " + p.getNombreProducto() + 
        " por " + (p.getPrecio() * cantidad));
            System.out.println("Stock actualizado de " + p.getNombreProducto() + ": " + p.getStock());
        } else {
            System.out.println("No hay suficiente stock para realizar la venta. Stock actual: " + stockActual);
        }
    }

    public void mostrarVentas() {
        System.out.println("\n=== LISTA DE VENTAS ===");
        if (listaVentas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta v : listaVentas) {
                System.out.println("Usuario: " + v.getUsuario().getNombreUsuario() +
                        " | Producto: " + v.getProducto().getNombreProducto() +
                        " | Cantidad: " + v.getCantidad() +
                        " | Total de la venta: " + v.getMonto());
            }
            System.out.println("=== Total acumulado en caja: " + saldoVenta + " ===");
        }
    }

    // METODO PARA VER EL HISTORIAL DEL USUARIO
    public void mostrarHistorialUsuario(Usuario usuario) {
        System.out.println("\nTus compras:");
        for (Venta venta : listaVentas) {
            if (venta.getUsuario().equals(usuario.getNombreUsuario())) {
                System.out.println(venta);
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
}
