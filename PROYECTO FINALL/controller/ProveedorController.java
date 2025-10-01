package controller;

import Model.Proveedor;
import java.util.ArrayList;
import java.util.Scanner;

public class ProveedorController {
    private ArrayList<Proveedor> listaProveedores = new ArrayList<>();

    public Proveedor registrarProveedor(Scanner scanner) {
        System.out.print("Ingrese ID del proveedor: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese nombre del proveedor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese contacto: ");
        String contacto = scanner.nextLine();

        Proveedor nuevo = new Proveedor(id, nombre, contacto);
        listaProveedores.add(nuevo);
        System.out.println("Proveedor registrado: " + nuevo);
        return nuevo;
    }

    public Proveedor buscarProveedor(int id) {
        for (Proveedor p : listaProveedores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void listarProveedores() {
        if (listaProveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("\n--- Lista de proveedores ---");
            for (Proveedor p : listaProveedores) {
                System.out.println(p);
            }
        }
    }

    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void menuCRUD(Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENU PROVEEDORES ---");
            System.out.println("1) Listar proveedores");
            System.out.println("2) Registrar proveedor");
            System.out.println("3) Volver");
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
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}