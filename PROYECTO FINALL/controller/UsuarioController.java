package controller;

import Model.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioController {

    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    // METODO PARA CREAR USUARIO ADMIN Y CLIENTE DEMOSTRACION
    public void crearUsuarioAdmin_Cliente() {
        Usuario admin = new Usuario("admin", "admin123", "ADMIN");
        Usuario cliente = new Usuario("cliente", "cliente123", "CLIENTE");
        cliente.setSaldo(200000.0); // Dinero inicial para el cliente
        listaUsuarios.add(admin);
        listaUsuarios.add(cliente);
    }

    // METODO PARA INICIAR SESION
    public Usuario iniciarSesion(Scanner scanner) {
        System.out.print("Usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equals(nombre) && u.getContrasena().equals(contrasena)) {
                System.out.println("Login exitoso como " + u.getRol());
                System.out.println("Dinero disponible: " + u.getSaldo());
                return u;
            }
        }
        System.out.println("Los datos ingresados son incorrectas.");
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

    /// METODO PARA REGISTRAR UN NUEVO USUARIO
    public void registrarNuevoUsuario(Scanner scanner) {
        System.out.print("Nombre de usuario: ");
        String nombre = scanner.nextLine();
        if (buscarUsuario(nombre) != null) {
            System.out.println("El usuario ya existe. No se puede registrar.");
            return;
        }
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Elija el Rol (ADMIN/CLIENTE): ");
        String rol = scanner.nextLine().toUpperCase();
        if (!rol.equals("ADMIN") && !rol.equals("CLIENTE")) {
            System.out.println("Rol inválido. Debe ser ADMIN o CLIENTE.");
            return;
        }
        System.out.print("Saldo inicial: ");
        double saldo = 0.0;
        try {
            saldo = Double.parseDouble(scanner.nextLine());
            if (saldo < 0) {
                System.out.println("El saldo inicial no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Saldo inválido. Se asigna 0 por defecto.");
        }
        Usuario nuevoUsuario = new Usuario(nombre, contrasena, rol);
        nuevoUsuario.setSaldo(saldo);
        listaUsuarios.add(nuevoUsuario);
        System.out.println("Usuario" + nombre + " ha sido registrado exitosamente con un saldo inicial de: " + nuevoUsuario.getSaldo());
    }

    // METODO PARA MOSTRAR USUARIOS EXISTENTES
    public void mostrarUsuarios() {
        System.out.println("\nUsuarios:");
        for (Usuario u : listaUsuarios) {
            System.out.println("Usuario: " + u.getNombreUsuario() + " | Clave: " + u.getContrasena() + " | Rol: " + u.getRol() + " | Dinero: " + u.getSaldo());
        }
    }

    ///////// METODO PARA ACTUALIZAR USUARIO //////////
    /// El método .trim() elimina los espacios en blanco al inicio y al final de la cadena. es ideal usarlo para asegurarnos que no se ingresen 
    /// nombres vacíos o con espacios.
    public void actualizarUsuario(Scanner scanner) {
        System.out.print("Nombre de usuario a editar: ");
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        Usuario u = buscarUsuario(nombre);
        if (u == null) {
            System.out.println("Usuario no encontrado. Verifica el nombre ingresado.");
            return;
        }
        System.out.print("Nuevo nombre (actual: " + u.getNombreUsuario() + "): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            u.setNombreUsuario(nuevoNombre);
        }
        System.out.print("Nueva contraseña: ");
        String nuevaContrasena = scanner.nextLine();
        if (!nuevaContrasena.isEmpty()) {
            u.setContrasena(nuevaContrasena);
        }
        System.out.print("Nuevo rol (ADMIN/CLIENTE): ");
        String nuevoRol = scanner.nextLine().toUpperCase();
        if (nuevoRol.equals("ADMIN") || nuevoRol.equals("CLIENTE")) {
            u.setRol(nuevoRol);
        } else if (!nuevoRol.isEmpty()) {
            System.out.println("Rol inválido. Debe ser ADMIN o CLIENTE.");
        }
        System.out.println("Usuario actualizado.");
    }

    ////////// METODO PARA ELIMINAR USUARIO //////////
   /// 
    public void eliminarUsuario(Scanner scanner) {
        System.out.print("Nombre de usuario a eliminar: ");
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        Usuario u = buscarUsuario(nombre);
        if (u == null) {
            System.out.println("Usuario no encontrado. Verifica el nombre ingresado.");
            return;
        }
        listaUsuarios.remove(u);
        System.out.println("Usuario eliminado.");
    }

    //////////// MENU CRUD USUARIOS ////////////
   public void menuCRUD(Scanner scanner) {
        while (true) {
            System.out.println("\n==============================");
            System.out.println(" |      📋 CRUD DE USUARIOS    |");
            System.out.println(" ===============================");
            System.out.println(" 1) 👥 Listar usuarios");
            System.out.println(" 2) ➕ Crear usuario");
            System.out.println(" 3) ✏️  Editar usuario");
            System.out.println(" 4) 🗑️  Eliminar usuario");
            System.out.println(" 5) ↩️  Volver");
            System.out.println("==============================");
            System.out.print("Seleccione una opción: ");
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
