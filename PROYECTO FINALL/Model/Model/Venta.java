package Model;

import java.time.LocalDateTime;

/**
 * Clase que representa una venta dentro del sistema.
 * Una venta está compuesta por un usuario, un producto,
 * la cantidad adquirida, el monto total y la fecha en la que se realizó.
 * 
 * @author 
 * Katherin Yesenia Monroy Echeverry  
 * Mariana Salgado Lopez
 * Jaime Andres Rodriguez
 */
public class Venta {

    /** Usuario que realiza la compra */
    private Usuario usuario;

     /** Producto adquirido en la venta */
    private Producto producto;

    /** Monto total de la venta */
    private double monto;

    /** Cantidad de productos vendidos */
    private int cantidad;

    /** Fecha y hora en que se registró la venta */
    private LocalDateTime fecha;
    

      /**
     * Constructor de la clase Venta.
     *
     * @param usuario Usuario que realiza la compra
     * @param producto Producto adquirido
     * @param cantidad Cantidad de productos comprados
     * @param monto Monto total de la transacción
     */
    public Venta(Usuario usuario, Producto producto, int cantidad, double monto) {
        this.usuario = usuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    ////////// METODOS DE ACCESO //////////
   /**
     * Obtiene el usuario que realizó la venta.
     *
     * @return usuario asociado a la venta
     */
    public Usuario getUsuario() {return usuario;}
    
    /**
     * Obtiene el producto adquirido en la venta.
     *
     * @return producto de la venta
     */
    public Producto getProducto() {return producto;}

     /**
     * Obtiene la cantidad de productos vendidos.
     *
     * @return cantidad de productos
     */
    public int getCantidad(){return cantidad;}
    /**
     * Obtiene el monto total de la venta.
     *
     * @return monto de la venta
     */
    public double getMonto() {return monto;}
    /**
     * Obtiene la fecha y hora en la que se registró la venta.
     *
     * @return fecha de la venta
     */
    public LocalDateTime getFecha() {return fecha;}

      /**
     * Devuelve una representación en cadena de la venta,
     * mostrando usuario, producto, monto y fecha.
     *
     * @return cadena con la información de la venta
     */
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
