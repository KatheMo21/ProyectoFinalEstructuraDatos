package controller;

import Model.Usuario;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Controlador para la gestión de usuarios dentro del sistema.
 * Comprende operaciones CRUD (Crear, Leer, Actualizar, Eliminar),
 * así como validaciones de seguridad en las contraseñas e inicio de sesión.
 *
 * @author  
 * Katherin Yesenia Monroy Echeverry  
 * Mariana Salgado Lopez
 * Jaime Andres Rodriguez
 */
public class UsuarioController {
  /**
     * Estructura de datos que almacena los usuarios registrados.
     * Se utiliza un {@link TreeSet} con comparador para evitar duplicados,
     * ordenando por nombre de usuario.
     */
    private TreeSet<Usuario> listaUsuarios = new TreeSet<>(Comparator.comparing(Usuario::getNombreUsuario));

         // ====== Códigos ANSI para colores ======
        final String RESET   = "\u001B[0m";   // reset (volver a normal)
        final String RED     = "\u001B[31m";  // rojo
        final String GREEN   = "\u001B[32m";  // verde
        final String YELLOW  = "\u001B[33m";  // amarillo
        final String BLUE    = "\u001B[34m";  // azul
        final String PURPLE  = "\u001B[35m";  // morado
        final String CYAN    = "\u001B[36m";  // cian
        final String WHITE   = "\u001B[37m";  // blanco


    /**
     * Permite que un usuario inicie sesión validando su nombre de usuario y contraseña.
     *
     * @param scanner Objeto de entrada para leer datos del usuario.
     * @return El {@link Usuario} autenticado, o {@code null} si no se encuentra o la contraseña es incorrecta.
     */
    public Usuario iniciarSesion(Scanner scanner) {
        try {
            if (listaUsuarios.isEmpty()) {
                System.out.println(" <<< Por el momento no hay usuarios registrados en el sistema, por favor registrese primero. >>>");
                return null;
            }

            System.out.print(BLUE+"Usuario: " + RESET);
            String nombre = scanner.nextLine();
            System.out.print(BLUE +"Contraseña:"+ RESET);
            String contrasena = scanner.nextLine();

            for (Usuario u : listaUsuarios) {
                if (u.getNombreUsuario().equals(nombre) && u.getContrasena().equals(contrasena)) {
                    System.out.println("Login exitoso como " + u.getRol());
                    System.out.println("Dinero disponible: " + u.getSaldo());
                    return u;
                }
            }
            System.out.println(">>> Usuario o contraseña incorrectos. <<<");
        } catch (Exception e) {
            System.out.println(" >>> Ocurrió un error durante el inicio de sesión: <<<" + e.getMessage());
        }
        return null;
    }
  /**
     * Busca un usuario existente por su nombre de usuario.
     *
     * @param nombre Nombre del usuario a buscar.
     * @return Objeto {@link Usuario} encontrado o {@code null} si no existe.
     */
    public Usuario buscarUsuario(String nombre) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equals(nombre)) {
                return u;
            }
        }
        return null;
    }
  /**
     * Busca un usuario con rol de administrador dentro del sistema.
     *
     * @return El {@link Usuario} con rol ADMIN, o {@code null} si no existe.
     */
    public Usuario buscarAdministrador() {
        for (Usuario u : listaUsuarios) {
            if (u.getRol().equalsIgnoreCase("ADMIN")) {
                return u; 
            }
        }
        return null; 
    }
 /**
     * Registra un nuevo usuario en el sistema.
     * Comprende validación de nombre único, verificación de contraseña y asignación de rol.
     *
     * @param scanner Objeto de entrada para leer datos del usuario.
     */
    public boolean validarClave(String contraseña) {
        if (contraseña == null || contraseña.length() < 8) {
            return false;
        }

        int tieneMayuscula = 0;
        int tieneNumero = 0;
        int tieneEspecial = 0;

        // Cuenta las mayusculas los numeros y el caracter
        for (char c : contraseña.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula++;
            } else if (Character.isDigit(c)) {
                tieneNumero++;
            } else if (!Character.isLetterOrDigit(c)) {
                tieneEspecial++;
            }
        }

        if (tieneMayuscula == 1) {
        }
        if (tieneNumero == 1) {
        }
        if (tieneEspecial == 1) {
        }
        if (contraseña.length() >= 8 && tieneMayuscula > 0 && tieneNumero > 0 && tieneEspecial > 0) {
            System.out.println("Contraseña guardada");
            return true;
        } else {
            System.out.println(" >>> La contraseña no cumple los requisitos. <<<");
            return false;
        }

    }


    /**
     * Muestra la lista de usuarios existentes en el sistema.
     * Presenta los datos en formato de tabla.
     */
public void registrarNuevoUsuario(Scanner scanner) {

    System.out.print(BLUE + "Nombre de usuario: " + RESET);
    String nombre = scanner.nextLine();
    if (buscarUsuario(nombre) != null) {
        System.out.println(">>> El usuario ya existe. No se puede registrar. <<<");
        return;
    }

    System.out.println(">>> La contraseña debe tener al menos 8 caracteres, un caracter especial, un número y una letra Mayúscula <<<");

    String contrasena;
    while (true) {  // Bucle hasta que la clave sea válida
        System.out.print(BLUE + "Contraseña: " + RESET);
        contrasena = scanner.nextLine();

        if (!validarClave(contrasena)) {
            System.out.println(RED + ">>> La contraseña no cumple los requisitos. Intente nuevamente. <<<" + RESET);
        } else {
            // Verificar si ya existe esa contraseña
            boolean existeClave = false;
            for (Usuario u : listaUsuarios) {
                if (u.getContrasena().equals(contrasena)) {
                    existeClave = true;
                    break;
                }
            }
            if (existeClave) {
                System.out.println(RED + ">>> La contraseña ya existe. Intente con otra. <<<" + RESET);
            } else {
                break; // clave válida y única → salir del bucle
            }
        }
    }

    System.out.print(BLUE + "Elija el Rol (ADMIN/CLIENTE): " + RESET);
    String rol = scanner.nextLine().toUpperCase();
    if (!rol.equals("ADMIN") && !rol.equals("CLIENTE")) {
        System.out.println(">>> Rol inválido. Debe ser ADMIN o CLIENTE <<<");
        return;
    }

    System.out.print(BLUE + "Saldo inicial: " + RESET);
    double saldo = 0.0;
    try {
        saldo = Double.parseDouble(scanner.nextLine());
        if (saldo < 0) {
            System.out.println(" >>> El saldo inicial no puede ser negativo. <<<");
            return;
        }
    } catch (NumberFormatException e) {
        System.out.println(">>> Saldo inválido. Se asigna 0 por defecto. <<<");
    }

    Usuario nuevoUsuario = new Usuario(nombre, contrasena, rol);
    nuevoUsuario.setSaldo(saldo);
    listaUsuarios.add(nuevoUsuario);
    System.out.println("Usuario " + nombre + " ha sido registrado exitosamente con un saldo inicial de: "
            + nuevoUsuario.getSaldo());
}


 /**
     * Permite actualizar la información de un usuario (nombre, contraseña o rol).
     *
     * @param scanner Objeto de entrada para leer datos del usuario.
     */
    public void mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println(">>> No hay usuarios registrados en el sistema.<<<");
            return;
        }
        System.out.println("\nUsuarios:");

    // Encabezado
        System.out.println(BLUE+ "+-----------------+-----------------+--------------+------------+" + RESET);
        System.out.printf("| %-15s | %-15s | %-12s | %-10s |%n", 
                "Usuario", "Clave", "Rol", "Saldo");
        System.out.println(BLUE+"+-----------------+-----------------+--------------+------------+" + RESET);

        // Filas
        for (Usuario u : listaUsuarios) {
            System.out.printf("| %-15s | %-15s | %-12s | %-10.2f |%n",
                    u.getNombreUsuario(),
                    u.getContrasena(),
                    u.getRol(),
                    u.getSaldo());
        }
        System.out.println(BLUE+"+-----------------+-----------------+--------------+------------+"+ RESET);


        }
    /**
     * Elimina un usuario del sistema a partir de su nombre de usuario.
     *
     * @param scanner Objeto de entrada para leer datos del usuario.
     */
    /// El método .trim() elimina los espacios en blanco al inicio y al final de la
    ///////// cadena. es ideal usarlo para asegurarnos que no se ingresen
    /// nombres vacíos o con espacios.
    public void actualizarUsuario(Scanner scanner) {
        System.out.print(BLUE + "Nombre de usuario a editar: " + RESET);
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println(">>> El nombre no puede estar vacío. <<<");
            return;
        }
    
        Usuario u = buscarUsuario(nombre);
        if (u == null) {
            System.out.println(">>> Usuario no encontrado. Verifica el nombre ingresado. <<<");
            return;
        }
    
        // Lo removemos del TreeSet antes de modificarlo
        listaUsuarios.remove(u);
    
        System.out.print(BLUE + "Nuevo nombre (actual: " + u.getNombreUsuario() + "): " + RESET);
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            u.setNombreUsuario(nuevoNombre);
        } else {
            System.out.println(">>> El nombre no puede estar vacío. <<<");
            // volvemos a meterlo para no perder el usuario
            listaUsuarios.add(u);
            return;
        }
    
        System.out.print(BLUE + "Nueva contraseña: " + RESET);
        String nuevaContrasena = scanner.nextLine();
        if (!nuevaContrasena.isEmpty()) {
            if (validarClave(nuevaContrasena)) {
                u.setContrasena(nuevaContrasena);
            } else {
                System.out.println(">>> La contraseña no cumple los requisitos. <<<");
            }
        }
    
        System.out.print(BLUE + "Nuevo rol (ADMIN/CLIENTE): " + RESET);
        String nuevoRol = scanner.nextLine().toUpperCase();
        if (nuevoRol.equals("ADMIN") || nuevoRol.equals("CLIENTE")) {
            u.setRol(nuevoRol);
        } else if (!nuevoRol.isEmpty()) {
            System.out.println(">>> Rol inválido. Debe ser ADMIN o CLIENTE. <<<");
        }
    
        // Lo volvemos a insertar ya con los cambios
        listaUsuarios.add(u);
    
        System.out.println("Usuario " + u.getNombreUsuario() + " ha sido actualizado correctamente.");
    }
    

  /**
     * Despliega un menú interactivo para la gestión CRUD de usuarios.
     * Comprende: listar, crear, editar y eliminar usuarios.
     *
     * @param scanner Objeto de entrada para leer opciones del usuario.
     */
    public void eliminarUsuario(Scanner scanner) {
        System.out.print(BLUE + "Nombre de usuario a eliminar: " + RESET);
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println(">>> El nombre no puede estar vacío. <<<");
            return;
        }
    
        Usuario u = buscarUsuario(nombre);
        if (u == null) {
            System.out.println(">>> Usuario no encontrado. Verifica el nombre ingresado. <<<");
            return;
        }
    
        // Aquí sí funciona normal porque se usa el comparador
        listaUsuarios.remove(u);
        System.out.println("Usuario " + u.getNombreUsuario() + " ha sido eliminado correctamente.");
    }
    //////////// MENU CRUD USUARIOS ////////////
    public void menuCrudUsuarios(Scanner scanner) {
        while (true) {

            System.out.println(CYAN + "  ╔═══════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "  ║                CRUD DE USUARIOS               ║" + RESET);
            System.out.println("  ║                                               ║");
            System.out.println(GREEN + "  ║  -1. Listar usuarios                          ║" + RESET);
            System.out.println(GREEN + "  ║  -2. Crear usuario                            ║" + RESET);
            System.out.println(GREEN + "  ║  -3. Editar usuario                           ║" + RESET);
            System.out.println(GREEN + "  ║  -4. Eliminar usuario                         ║" + RESET);
            System.out.println(GREEN + "  ║  -5. Volver                                   ║" + RESET);
            System.out.println("  ║                                               ║");
            System.out.println(PURPLE + "  ║  -> Seleccione una opción:                    ║" + RESET);
            System.out.println(CYAN + "  ╚═══════════════════════════════════════════════╝" + RESET);
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    mostrarUsuarios();
                    break;
                case "2":
                    registrarNuevoUsuario(scanner);
                    break;
                case "3":
                    actualizarUsuario(scanner);
                    break;
                case "4":
                    eliminarUsuario(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

}
