package Model;


/**
 * Clase que representa un proveedor dentro del sistema.
 * Contiene información básica como identificador, nombre y datos de contacto.
 * 
 * @author 
 * Katherin Yesenia Monroy Echeverry  
 * Mariana Salgado Lopez
 * Jaime Andres Rodriguez
 */
public class Proveedor {

    /** Identificador único del proveedor */
    private int id;

    /** Nombre del proveedor */
    private String nombre;

    /** Información de contacto del proveedor */
    private String contacto;

       /**
     * Constructor de la clase Proveedor.
     * 
     * @param id Identificador único del proveedor
     * @param nombre Nombre del proveedor
     * @param contacto Información de contacto del proveedor
     */
    public Proveedor(int id, String nombre, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
    }

     /**
     * Obtiene el identificador del proveedor.
     * 
     * @return id del proveedor
     */
    public int getId() {
        return id;
    }

      /**
     * Obtiene el nombre del proveedor.
     * 
     * @return nombre del proveedor
     */
    public String getNombre() {
        return nombre;
    }

     /**
     * Establece un nuevo nombre para el proveedor.
     * 
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la información de contacto del proveedor.
     * 
     * @param contacto nueva información de contacto
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
       /**
     * Obtiene la información de contacto del proveedor.
     * 
     * @return información de contacto
     */
    public String getContacto() {
        return contacto;
    }
    /**
     * Devuelve una representación en cadena del objeto Proveedor.
     * 
     * @return información del proveedor en formato legible
     */
    @Override
    public String toString() {
        return "Proveedor [ID=" + id + ", Nombre=" + nombre + ", Contacto=" + contacto + "]";
    }

}
