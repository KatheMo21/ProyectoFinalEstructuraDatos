package controller;

import Model.Proveedor;
import java.util.ArrayList;
import java.util.Scanner;

public class ProveedorController {
     private ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    private int nextId = 1; // ID autoincremental


    // MÉTODO REGISTRAR PROVEEDOR
    public Proveedor registrarProveedor(Scanner scanner) {
        System.out.print("Ingrese nombre del proveedor: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return null;
        }

        System.out.print("Ingrese contacto: ");
        String contacto = scanner.nextLine().trim();
        if (contacto.isEmpty()) {
            System.out.println("El contacto no puede estar vacío.");
            return null;
        }

        for (Proveedor p : listaProveedores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Ya existe un proveedor con ese nombre.");
                return null;
            }
        }

        // Crear nuevo proveedor con ID automático
        Proveedor nuevo = new Proveedor(nextId++, nombre, contacto);
        listaProveedores.add(nuevo);

        System.out.println("Proveedor registrado: " + nuevo);
        return nuevo;
    }

    // MÉTODO BUSCAR PROVEEDOR POR ID

    public Proveedor buscarProveedor(int id) {
        for (Proveedor p : listaProveedores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // MÉTODO BUSCAR PROVEEDOR POR NOMBRE

    public Proveedor buscarPorNombre(String nombre) {
        for (Proveedor p : listaProveedores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }


    // MÉTODO PARA LISTAR PROVEEDORES

    public void listarProveedores() {
        if (listaProveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }

        System.out.printf("%-5s %-20s %-20s%n", "ID", "Nombre", "Contacto");
        System.out.println("-------------------------------------------------");
        for (Proveedor p : listaProveedores) {
            System.out.printf("%-5d %-20s %-20s%n",
                    p.getId(), p.getNombre(), p.getContacto());
        }
    }


    // MÉTODO PARA EDITAR PROVEEDOR

    public void editarProveedor(Scanner scanner) {
        System.out.print("Ingrese ID del proveedor a editar: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Debe ser un número.");
            return;
        }

        Proveedor p = buscarProveedor(id);
        if (p == null) {
            System.out.println("Proveedor no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (actual: " + p.getNombre() + "): ");
        String nuevoNombre = scanner.nextLine().trim();
        if (!nuevoNombre.isEmpty()) {
            p.setNombre(nuevoNombre);
        }

        System.out.print("Nuevo contacto (actual: " + p.getContacto() + "): ");
        String nuevoContacto = scanner.nextLine().trim();
        if (!nuevoContacto.isEmpty()) {
            p.setContacto(nuevoContacto);
        }

        System.out.println("Proveedor actualizado.");
    }

    
    // MÉTODO PARA ELIMINAR PROVEEDOR
 
    public void eliminarProveedor(Scanner scanner) {
        System.out.print("Ingrese ID del proveedor a eliminar: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Debe ser un número.");
            return;
        }

        Proveedor p = buscarProveedor(id);
        if (p == null) {
            System.out.println("Proveedor no encontrado.");
            return;
        }

        listaProveedores.remove(p);
        System.out.println("Proveedor eliminado correctamente.");
    }

   
    //  lISTA DE PROVEEDORES

    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }


    // MENÚ CRUD PROVEEDORES

    public void menuCrudProveedores(Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENU PROVEEDORES ---");
            System.out.println("1) Listar proveedores");
            System.out.println("2) Registrar proveedor");
            System.out.println("3) Editar proveedor");
            System.out.println("4) Eliminar proveedor");
            System.out.println("5) Buscar proveedor por nombre");
            System.out.println("6) Volver");
            System.out.print("Opción: ");
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
                    System.out.print("Ingrese nombre del proveedor a buscar: ");
                    String nombre = scanner.nextLine();
                    Proveedor encontrado = buscarPorNombre(nombre);
                    if (encontrado != null) {
                        System.out.println("Proveedor encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró un proveedor con ese nombre.");
                    }
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}