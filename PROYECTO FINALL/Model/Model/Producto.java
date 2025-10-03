package Model;

/**
 * Clase que representa un producto dentro del sistema.
 * Contiene información como identificador, nombre, precio, stock, descripción y proveedor.
 * 
 * @author 
 * Katherin Yesenia Monroy Echeverry  
 * Mariana Salgado Lopez
 * Jaime Andres Rodriguez
 */
public class Producto {
    
    /** Identificador único del producto */
    private int id;

    /** Nombre del producto */
    private String nombreProducto;

    /** Precio unitario del producto */
    private double precio;

    /** Cantidad disponible en stock */ 
    private int stock;

    
    /** Descripción detallada del producto */
    private String descripcion;

    /** Proveedor asociado al producto */
    private Proveedor proveedor;

    /**
     * Constructor de la clase Producto.
     *
     * @param id Identificador único del producto
     * @param nombreProducto Nombre del producto
     * @param precio Precio unitario
     * @param stock Cantidad en inventario
     * @param descripcion Descripción del producto
     * @param proveedor Proveedor asociado
     */
    public Producto(int id, String nombreProducto, double precio, int stock, String descripcion,Proveedor proveedor) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }
    /**
     * Obtiene el identificador del producto.
     * 
     * @return id del producto
     */
    public int getId() { return id; }

     /**
     * Establece el identificador del producto.
     * 
     * @param id identificador nuevo
     */
    public void setId(int id){this.id=id;}
    
    /**
     * Obtiene el nombre del producto.
     * 
     * @return nombre del producto
     */
    public String getNombreProducto() { return nombreProducto; }

     /**
     * Establece el nombre del producto.
     * 
     * @param nombre nombre nuevo
     */
    public void setNombreProducto(String nombre){this.nombreProducto=nombreProducto;}
    
      /**
     * Obtiene el precio del producto.
     * 
     * @return precio del producto
     */
    public double getPrecio() { return precio; }

     /**
     * Establece el precio del producto.
     * 
     * @param precio nuevo precio
     */
    public void setPrecio(Double precio){this.precio=precio;}

       /**
     * Obtiene el stock disponible del producto.
     * 
     * @return cantidad en stock
     */
    public int getStock() { return stock; }

     /**
     * Establece la cantidad en stock.
     * 
     * @param stock nueva cantidad
     */
    public void setStock(int stock) { this.stock = stock; }

    /**
     * Obtiene la descripción del producto.
     * 
     * @return descripción del producto
     */
    public String getDescripcion(){return descripcion;}

    /**
     * Establece la descripción del producto.
     * 
     * @param descripcion nueva descripción
     */
    public void setDescricpion(String descripcion){this.descripcion=descripcion;}

    /**
     * Obtiene el proveedor asociado al producto.
     * 
     * @return proveedor del producto
     */
    public Proveedor getProveedor() { return proveedor; }
    
    /**
     * Devuelve una representación en cadena del objeto Producto.
     * 
     * @return información del producto en formato legible
     */
    @Override
    public String toString() {
        return "Producto [ID=" + id + ", Nombre=" + nombreProducto + ", Precio=$" + precio +
                ", Stock=" + stock + ", Proveedor=" + proveedor.getNombre() + "]";
    }
}