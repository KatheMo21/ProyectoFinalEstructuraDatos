package Model;




import java.time.LocalDateTime;

public class Venta {

    private String usuario;
    private String producto;
    private double monto;
    private int cantidad;
    private LocalDateTime fecha;

    ///// CONSTRUCTORES /////
 
    public Venta(String usuario, String producto, int cantidad, double monto) {
        this.usuario = usuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    ////////// METODOS DE ACCESO //////////
   
     public String getUsuario() {
        return usuario;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad(){
        return cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }


    @Override
    public String toString() {
        return "Venta{"
                + "usuario='" + usuario + '\''
                + ", producto='" + producto + '\''
                + ", monto=" + monto
                + ", fecha=" + fecha
                + '}';
    }

}
