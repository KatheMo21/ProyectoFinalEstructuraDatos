package controller;

import Model.Producto;
import Model.Usuario;
import Model.Venta;

import java.util.ArrayList;
import java.util.List;

public class VentaController {
    private List<Venta> listaVentas;

    public List<Venta> getListaVentas() {
    return listaVentas;
}

    public VentaController() {
        this.listaVentas = new ArrayList<>();
    }

    public void registrarVenta(Usuario usuario, Producto producto, int cantidad, double monto) {
        // Crear la venta
    Venta venta = new Venta(usuario.getNombreUsuario(), producto.getNombreProducto(), cantidad, monto);

    // Disminuir el stock del producto
    int stockActual = producto.getStock();
    if(cantidad <= stockActual) {
        producto.setStock(stockActual - cantidad);
        listaVentas.add(venta);
        System.out.println("Venta registrada: " + venta);
        System.out.println("Stock actualizado de " + producto.getNombreProducto() + ": " + producto.getStock());
    } else {
        System.out.println("No hay suficiente stock para realizar la venta. Stock actual: " + stockActual);
    }
    }

    public void mostrarVentas() {
        System.out.println("\n=== LISTA DE VENTAS ===");
        if (listaVentas.isEmpty()) {
            System.out.println(" No hay ventas registradas.");
        } else {
            for (Venta v : listaVentas) {
                System.out.println(v);
            }
        }
    }
}