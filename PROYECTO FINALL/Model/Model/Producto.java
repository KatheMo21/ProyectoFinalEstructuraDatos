package Model;

public class Producto {
    private int id;
    private String nombreProducto;
    private double precio;
    private int stock;
    private String descripcion;
    private Proveedor proveedor;

    public Producto(int id, String nombreProducto, double precio, int stock, String descripcion,Proveedor proveedor) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public int getId() { return id; }
    public void setId(int id){this.id=id;}
    
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombre){this.nombreProducto=nombreProducto;}
    
    public double getPrecio() { return precio; }
    public void setPrecio(Double precio){this.precio=precio;}
    
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getDescripcion(){return descripcion;}
    public void setDescricpion(String descripcion){this.descripcion=descripcion;}

    public Proveedor getProveedor() { return proveedor; }
    
    @Override
    public String toString() {
        return "Producto [ID=" + id + ", Nombre=" + nombreProducto + ", Precio=$" + precio +
                ", Stock=" + stock + ", Proveedor=" + proveedor.getNombre() + "]";
    }
}