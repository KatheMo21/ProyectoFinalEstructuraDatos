package Model;

/**
 * Clase que representa a un usuario dentro del sistema.
 * Incluye datos como nombre de usuario, contraseña, rol y saldo.
 * 
 * Además, implementa la interfaz {@link Comparable} para permitir
 * la comparación de usuarios por nombre de manera insensible a mayúsculas.
 * 
 * @author 
 * Katherin Yesenia Monroy Echeverry  
 * Mariana Salgado Lopez
 * Jaime Andres Rodriguez
 */
public class Usuario implements Comparable<Usuario> {
    /** Nombre único del usuario */
    private String nombreUsuario;

    /** Contraseña del usuario */
    private String contrasena;

    /** Rol del usuario dentro del sistema (ej: ADMIN, CLIENTE) */
    private String rol;

    /** Saldo asociado al usuario */
    private double saldo;
 
    /**
     * Enumeración de tipos de usuario posibles.
     */
    public enum TipoUsuario {
        ADMIN, CLIENTE
    }

    /**
     * Constructor de la clase Usuario.
     * 
     * @param nombreUsuario Nombre único del usuario
     * @param contrasena Contraseña del usuario
     * @param rol Rol del usuario (ejemplo: "ADMIN", "CLIENTE")
     */
    public Usuario(String nombreUsuario, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.saldo = 0.0;
    }


////////// METODOS DE ACCESO //////////
    /**
     * Obtiene el nombre del usuario.
     * 
     * @return nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

      /**
     * Establece un nuevo nombre para el usuario.
     * 
     * @param nombreUsuario nuevo nombre
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece una nueva contraseña para el usuario.
     * 
     * @param contrasena nueva contraseña
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el rol del usuario.
     * 
     * @return rol del usuario
     */
    public String getRol() {
        return rol;
    }
    /**
     * Establece un nuevo rol para el usuario.
     * 
     * @param rol nuevo rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Obtiene el saldo del usuario.
     * 
     * @return saldo actual
     */
    public double getSaldo() {
        return saldo;
    }

      /**
     * Establece el saldo del usuario.
     * 
     * @param saldo nuevo saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

      /**
     * Devuelve una representación en cadena del usuario,
     * mostrando su nombre y saldo.
     * 
     * @return cadena con los datos principales del usuario
     */
    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario + ", Saldo: $" + saldo;
    }

      /**
     * Compara este usuario con otro según su nombre, 
     * ignorando mayúsculas y minúsculas.
     * 
     * @param otro Usuario con el que se compara
     * @return valor negativo si va antes, positivo si va después, 0 si son iguales
     */
    @Override
    public int compareTo(Usuario otro) {
        // Ordenar usuarios por nombre, ignorando mayúsculas/minúsculas
        return this.nombreUsuario.compareToIgnoreCase(otro.nombreUsuario);
    }

     /**
     * Compara si dos usuarios son iguales en base a su nombre (sin distinguir mayúsculas).
     * 
     * @param o objeto a comparar
     * @return true si los usuarios tienen el mismo nombre, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario);
    }

     /**
     * Calcula el hashCode del usuario en función de su nombre en minúsculas.
     * 
     * @return código hash del usuario
     */
    @Override
    public int hashCode() {
        return nombreUsuario.toLowerCase().hashCode();
    }
  
}
