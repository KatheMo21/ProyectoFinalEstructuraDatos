package Model;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private double saldo;
 


    public enum TipoUsuario {
        ADMIN, CLIENTE
    }

    // Constructor
    public Usuario(String nombreUsuario, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.saldo = 0.0;
    }


////////// METODOS DE ACCESO //////////
/// 
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario + ", Saldo: $" + saldo;
    }

    
}
