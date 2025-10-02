package controller;

import Model.Usuario;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class UsuarioController {

    // TreeSet con comparador para evitar duplicados
    private TreeSet<Usuario> listaUsuarios = new TreeSet<>(Comparator.comparing(Usuario::getNombreUsuario));

     // Códigos ANSI para colores
        final String RESET   = "\u001B[0m";   // reset (volver a normal)
        final String RED     = "\u001B[31m";  // rojo
        final String GREEN   = "\u001B[32m";  // verde
        final String YELLOW  = "\u001B[33m";  // amarillo
        final String BLUE    = "\u001B[34m";  // azul
        final String PURPLE  = "\u001B[35m";  // morado
        final String CYAN    = "\u001B[36m";  // cian
        final String WHITE   = "\u001B[37m";  // blanco

    
    // METODO PARA INICIAR SESION
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

    /////////////// METODO PARA BUSCAR USUARIO //////////
    public Usuario buscarUsuario(String nombre) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equals(nombre)) {
                return u;
            }
        }
        return null;
    }

    ////////// METODO PARA BUSCAR ADMINISTRADOR //////////
    public Usuario buscarAdministrador() {
        for (Usuario u : listaUsuarios) {
            if (u.getRol().equalsIgnoreCase("ADMIN")) {
                return u; 
            }
        }
        return null; 
    }

    /////////// METODO PARA VALIDAR LA CONTRASEÑA ////////////
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

    /// METODO PARA REGISTRAR UN NUEVO USUARIO
    public void registrarNuevoUsuario(Scanner scanner) {
        
        System.out.print(BLUE+"Nombre de usuario: "+ RESET);
        String nombre = scanner.nextLine();
        if (buscarUsuario(nombre) != null) {
            System.out.println(">>> El usuario ya existe. No se puede registrar. <<<");
            return;
        }

        System.out.print(">>> La contraseña debe tener almenos 8 carateres,un caracter especial, un numero y una letra Mayuscula <<<  \n");
       
        System.out.print(BLUE+ "Contraseña: " + RESET); 
        String contrasena = scanner.nextLine();

        if (!validarClave(contrasena)) {
            System.out.println(">>> La contraseña no cumple los requisitos. <<<");
            return;
        }
        for (Usuario u : listaUsuarios) {
            if (u.getContrasena().equals(contrasena)) {
                System.out.println(">>> La contraseña ya existe. <<<");
                return;
            }
        }

        System.out.print(BLUE +"Elija el Rol (ADMIN/CLIENTE): " + RESET);
        String rol = scanner.nextLine().toUpperCase();
        if (!rol.equals("ADMIN") && !rol.equals("CLIENTE")) {
            System.out.println(">>> Rol inválido. Debe ser ADMIN o CLIENTE <<<");
            return;
        }
        System.out.print(BLUE+ "Saldo inicial: " + RESET);
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
        System.out.println("Usuario" + nombre + " ha sido registrado exitosamente con un saldo inicial de: "
                + nuevoUsuario.getSaldo());
    }

    // METODO PARA MOSTRAR USUARIOS EXISTENTES
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

    ///////// METODO PARA ACTUALIZAR USUARIO //////////
    /// El método .trim() elimina los espacios en blanco al inicio y al final de la
    ///////// cadena. es ideal usarlo para asegurarnos que no se ingresen
    /// nombres vacíos o con espacios.
    public void actualizarUsuario(Scanner scanner) {
        System.out.print(BLUE+ "Nombre de usuario a editar: " + RESET);
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println(">>> El nombre no puede estar vacío. <<<");
            return;
        }
        Usuario u = buscarUsuario(nombre);
        if (u == null) {
            System.out.println(">>> Usuario no encontrado. Verifica el nombre ingresado.<<<");
            return;
        }

        System.out.print(BLUE+ "Nuevo nombre (actual: " + u.getNombreUsuario() + "): "+ RESET);
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            u.setNombreUsuario(nuevoNombre);
        }  else {
                System.out.println(">>> El nombre no puede estar vacío. <<<");
                return;
            }

        System.out.print(BLUE +"Nueva contraseña: " +RESET);
        String nuevaContrasena = scanner.nextLine();
        if (!nuevaContrasena.isEmpty()) {
            u.setContrasena(nuevaContrasena);
        } else {
                System.out.println(">>> La contraseña no cumple los requisitos. <<<");
            }

        System.out.print(BLUE +"Nuevo rol (ADMIN/CLIENTE): "+ RESET);
        String nuevoRol = scanner.nextLine().toUpperCase();
        if (nuevoRol.equals("ADMIN") || nuevoRol.equals("CLIENTE")) {
            u.setRol(nuevoRol);
        } else if (!nuevoRol.isEmpty()) {
            System.out.println(">>> Rol inválido. Debe ser ADMIN o CLIENTE.<<< " );
        }
        System.out.println("Usuario" + u.getNombreUsuario() + " ha sido actualizado correctamente.");
        
    }

    ////////// METODO PARA ELIMINAR USUARIO //////////
    ///
    public void eliminarUsuario(Scanner scanner) {
        System.out.print(BLUE +"Nombre de usuario a eliminar: " + RESET);
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
        listaUsuarios.remove(u);
        System.out.println("Usuario" + u.getNombreUsuario() + " ha sido eliminado correctamente" );
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
