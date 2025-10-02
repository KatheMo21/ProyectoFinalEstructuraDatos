package Model;

import java.time.LocalDateTime;

public class Venta {

    private Usuario usuario;
    private Producto producto;
    private double monto;
    private int cantidad;
    private LocalDateTime fecha;
    

    ///// CONSTRUCTORES /////
 
    public Venta(Usuario usuario, Producto producto, int cantidad, double monto) {
        this.usuario = usuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

   



    ////////// METODOS DE ACCESO //////////
   
    public Usuario getUsuario() {return usuario;}
    public Producto getProducto() {return producto;}
    public int getCantidad(){return cantidad;}
    public double getMonto() {return monto;}
    public LocalDateTime getFecha() {return fecha;}
    
@Override
    public String toString() {
        return "Venta{"
                + "usuario: '" + usuario + '\''
                + ", producto: '" + producto + '\''
                + ", monto: " + monto
                + ", fecha: " + fecha
                + '}';
    }

}
