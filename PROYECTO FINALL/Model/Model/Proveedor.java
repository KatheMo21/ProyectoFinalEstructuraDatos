package Model;

public class Proveedor {
    private int id;
    private String nombre;
    private String contacto;

    public Proveedor(int id, String nombre, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public Proveedor() {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }

    @Override
    public String toString() {
        return "Proveedor [ID=" + id + ", Nombre=" + nombre + ", Contacto=" + contacto + "]";
    }
}