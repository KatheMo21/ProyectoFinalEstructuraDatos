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

    public void registrarVenta(Usuario usuario, Producto producto, double monto) {
        Venta venta = new Venta(usuario.getNombreUsuario(),producto.getNombreProducto(), monto);
        listaVentas.add(venta);
        System.out.println("Venta registrada: " + venta);
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