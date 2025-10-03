package controller;

import Model.Proveedor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controlador encargado de gestionar las operaciones CRUD (Crear, Leer, 
 * Actualizar y Eliminar) de los proveedores en el sistema.
 *
 * Permite registrar, listar, buscar, editar y eliminar proveedores.
 * Además, incluye un menú interactivo para la administración.
 * 
 *
 * @author 
 * Katherin Yesenia Monroy Echeverry  
 * Mariana Salgado Lopez
 * Jaime Andres Rodriguez 
 * @version 1.0
 */
public class ProveedorController {

    /** Lista que almacena todos los proveedores registrados. */
    private ArrayList<Proveedor> listaProveedores = new ArrayList<>();

    /** Contador autoincremental para asignar ID único a cada proveedor. */
    private int nextId = 1; // ID autoincremental

 // ====== Códigos ANSI para colores en consola ======
    final String RESET   = "\u001B[0m";
    final String RED     = "\u001B[31m";
    final String GREEN   = "\u001B[32m";
    final String YELLOW  = "\u001B[33m";
    final String BLUE    = "\u001B[34m";
    final String PURPLE  = "\u001B[35m";
    final String CYAN    = "\u001B[36m";
    final String WHITE   = "\u001B[37m";

   /**
     * Registra un nuevo proveedor en el sistema.
     *
     * @param scanner Scanner para capturar la entrada del usuario
     * @return el proveedor creado si el registro fue exitoso, 
     *         o {@code null} si hubo algún error (nombre/contacto vacío o repetido).
     */
    public Proveedor registrarProveedor(Scanner scanner) {
        System.out.print(BLUE + "Nombre del proveedor: " + RESET);
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println(RED + ">>> El nombre no puede estar vacío. <<<" + RESET);
            return null;
        }

        System.out.print(BLUE + "Contacto: " + RESET);
        String contacto = scanner.nextLine().trim();
        if (contacto.isEmpty()) {
            System.out.println(RED + ">>> El contacto no puede estar vacío. <<<" + RESET);
            return null;
        }

        for (Proveedor p : listaProveedores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(RED + ">>> Ya existe un proveedor con ese nombre. <<<" + RESET);
                return null;
            }
        }

        // Crear nuevo proveedor con ID automático
        Proveedor nuevo = new Proveedor(nextId++, nombre, contacto);
        listaProveedores.add(nuevo);

        System.out.println(GREEN + "Proveedor registrado correctamente: " + nuevo + RESET);
        return nuevo;
    }

/**
     * Busca un proveedor en la lista por su ID.
     *
     * @param id identificador del proveedor
     * @return el proveedor encontrado o {@code null} si no existe
     */
    public Proveedor buscarProveedor(int id) {
        for (Proveedor p : listaProveedores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

      /**
     * Busca un proveedor en la lista por su nombre.
     *
     * @param nombre nombre del proveedor a buscar
     * @return el proveedor encontrado o {@code null} si no existe
     */
    public Proveedor buscarPorNombre(String nombre) {
        for (Proveedor p : listaProveedores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
   /**
     * Muestra en consola todos los proveedores registrados en formato tabla.
     * Si no existen proveedores, se muestra un mensaje de advertencia.
     */
    public void listarProveedores() {
        if (listaProveedores.isEmpty()) {
            System.out.println(YELLOW + ">>> No hay proveedores registrados. <<<" + RESET);
            return;
        }

        // Encabezado con estilo tabla
        System.out.println(BLUE + "+-----+----------------------+----------------------+" + RESET);
        System.out.printf("| %-3s | %-20s | %-20s |%n", "ID", "Nombre", "Contacto");
        System.out.println(BLUE + "+-----+----------------------+----------------------+" + RESET);

        // Filas
        for (Proveedor p : listaProveedores) {
            System.out.printf("| %-3d | %-20s | %-20s |%n",
                    p.getId(), p.getNombre(), p.getContacto());
        }
        System.out.println(BLUE + "+-----+----------------------+----------------------+" + RESET);
    }

      /**
     * Permite editar los datos de un proveedor existente.
     * El usuario debe ingresar el ID del proveedor y luego los nuevos valores.
     *
     * @param scanner Scanner para capturar la entrada del usuario
     */
    public void editarProveedor(Scanner scanner) {
        System.out.print(BLUE + "Ingrese ID del proveedor a editar: " + RESET);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(RED + ">>> ID inválido. Debe ser un número. <<<" + RESET);
            return;
        }

        Proveedor p = buscarProveedor(id);
        if (p == null) {
            System.out.println(RED + ">>> Proveedor no encontrado. <<<" + RESET);
            return;
        }

        System.out.print(BLUE + "Nuevo nombre (actual: " + p.getNombre() + "): " + RESET);
        String nuevoNombre = scanner.nextLine().trim();
        if (!nuevoNombre.isEmpty()) {
            p.setNombre(nuevoNombre);
        }

        System.out.print(BLUE + "Nuevo contacto (actual: " + p.getContacto() + "): " + RESET);
        String nuevoContacto = scanner.nextLine().trim();
        if (!nuevoContacto.isEmpty()) {
            p.setContacto(nuevoContacto);
        }

        System.out.println(GREEN + ">>> Proveedor actualizado correctamente. <<<" + RESET);
    }

      /**
     * Elimina un proveedor del sistema a partir de su ID.
     *
     * @param scanner Scanner para capturar la entrada del usuario
     */
    public void eliminarProveedor(Scanner scanner) {
        System.out.print(BLUE + "Ingrese ID del proveedor a eliminar: " + RESET);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(RED + ">>> ID inválido. Debe ser un número. <<<" + RESET);
            return;
        }

        Proveedor p = buscarProveedor(id);
        if (p == null) {
            System.out.println(RED + ">>> Proveedor no encontrado. <<<" + RESET);
            return;
        }

        listaProveedores.remove(p);
        System.out.println(GREEN + ">>> Proveedor eliminado correctamente. <<<" + RESET);
    }

/**
     * Devuelve la lista de todos los proveedores registrados.
     *
     * @return lista de proveedores
     */
    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

     /**
     * Muestra el menú CRUD de proveedores en consola y permite al usuario
     * interactuar para listar, registrar, editar, eliminar o buscar proveedores.
     *
     * @param scanner Scanner para capturar la entrada del usuario
     */
    public void menuCrudProveedores(Scanner scanner) {
        while (true) {
            System.out.println(CYAN + "  ╔═══════════════════════════════════════════════╗" + RESET);
            System.out.println(PURPLE + "  ║              CRUD DE PROVEEDORES              ║" + RESET);
            System.out.println("  ║                                               ║");
            System.out.println(GREEN + "  ║  -1. Listar proveedores                       ║" + RESET);
            System.out.println(GREEN + "  ║  -2. Registrar proveedor                      ║" + RESET);
            System.out.println(GREEN + "  ║  -3. Editar proveedor                         ║" + RESET);
            System.out.println(GREEN + "  ║  -4. Eliminar proveedor                       ║" + RESET);
            System.out.println(GREEN + "  ║  -5. Buscar proveedor por nombre              ║" + RESET);
            System.out.println(GREEN + "  ║  -6. Volver                                   ║" + RESET);
            System.out.println("  ║                                               ║");
            System.out.println(PURPLE + "  ║  -> Seleccione una opción:                    ║" + RESET);
            System.out.println(CYAN + "  ╚═══════════════════════════════════════════════╝" + RESET);

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    listarProveedores();
                    break;
                case "2":
                    registrarProveedor(scanner);
                    break;
                case "3":
                    editarProveedor(scanner);
                    break;
                case "4":
                    eliminarProveedor(scanner);
                    break;
                case "5":
                    System.out.print(BLUE + "Ingrese nombre del proveedor a buscar: " + RESET);
                    String nombre = scanner.nextLine();
                    Proveedor encontrado = buscarPorNombre(nombre);
                    if (encontrado != null) {
                        System.out.println(GREEN + "Proveedor encontrado: " + encontrado + RESET);
                    } else {
                        System.out.println(RED + ">>> No se encontró un proveedor con ese nombre. <<<" + RESET);
                    }
                    break;
                case "6":
                    return;
                default:
                    System.out.println(RED + ">>> Opción inválida. <<<" + RESET);
            }
        }
    }
}
