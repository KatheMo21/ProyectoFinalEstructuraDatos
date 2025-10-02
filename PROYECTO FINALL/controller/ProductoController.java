package controller;

import Model.Producto;
import Model.Proveedor;
import java.util.LinkedList;
import java.util.Scanner;

public class ProductoController {

    private LinkedList<Producto> listaProductos = new LinkedList<>();
    private int nextId = 1;

    private ProveedorController proveedorController;

    //Agregue instacia de proveedory cree uno nuevo para pasarlo al metodo subirProductos
    // y asociarla el producto a un proveedor
    Proveedor proveedorOne = new Proveedor(1, "World Meditation", "18768463846");

    public ProductoController(ProveedorController proveedorController) {
        this.proveedorController = proveedorController;
    }

    public void subirProductos() {

        listaProductos.add(new Producto(1, "Jardín meditación", 128000, 10,
                "Trae: Base redonda en concreto, figura en yeso, arena, piedras, rastrillo, vela y palo santo.", proveedorOne));
        listaProductos.add(new Producto(2, "Jardín Protección", 65200, 15,
                "Trae: Base caracol en concreto, figura en yeso, arena, piedras, rastrillo, vela y palo santo.", proveedorOne));
        listaProductos.add(new Producto(3, "Porta incienso mandala", 24500, 20,
                "El aroma del incienso puede ayudar a relajar el sistema nervioso y reducir el estrés, además de favorecer la concentración.",
                proveedorOne));
        listaProductos.add(new Producto(4, "Porta vela luna", 27300, 12,
                "La luz de una vela simboliza la purificación, es capaz de llenar de calma el ambiente, y entrar en contacto con nuestra luz interior.",
                proveedorOne));

    }

    // METODOS PARA VER LA LISTA DE PRODUCTOS
    public void listarProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("Productos disponibles:");
        for (Producto P : listaProductos) {
            System.out.println(P);
        }
    }

    // METODO PARA CREAR PRODUCTOS
    //modifique este metodo para asociarle el stock cuando se cree y al igual llame una instancia
    //de proveedor y llame el metodo registrar proveedor para asociarlo un proveedor siempre que se 
    // cree un nuevoProducto
    public void crearProductos(Scanner scanner, ProveedorController proveedorController) {
        int id = nextId++; // ID autoincremental

        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        if (buscarProductoPorNombre(nombre) != null) {
            System.out.println("Ya existe un producto con ese nombre.");
            return;
        }

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        double precio = -1;
        while (precio < 0) {
            System.out.print("Precio: ");
            try {
                precio = Double.parseDouble(scanner.nextLine());
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido. Debe ser un número.");
            }
        }

        int stock = -1;
        while (stock < 0) {
            System.out.print("Stock: ");
            try {
                stock = Integer.parseInt(scanner.nextLine());
                if (stock < 0) {
                    System.out.println("El stock no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Stock inválido. Debe ser un número entero.");
            }
        }

        // Seleccionar o crear proveedor
        Proveedor proveedor = proveedorController.registrarProveedor(scanner);

        listaProductos.add(new Producto(id, nombre, precio, stock, descripcion, proveedor));
        System.out.println("El producto" + nombre + " ha sido creado exitosamente.");
    }

    // METODO PARA BUSCAR PODUCTOS POR ID
    public Producto buscarProductos(int id) {
        for (Producto p : listaProductos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /// METODO PARA BUSCAR PRODUCTO POR NOMBRE
    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto p : listaProductos) {
            if (p.getNombreProducto().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // METODO PARA EDITAR PRODUCTOS
    //Modifique  este metodo para actualizarle el  nuevo stock y la nueva descripcion
    public void editarProductos(Scanner scanner) {
        System.out.print("ID del producto a editar: ");
        int id;
        String idStr = scanner.nextLine();
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Debe ser un número.");
            return;
        }
        Producto p = buscarProductos(id);
        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.print("Nuevo nombre (actual: " + p.getNombreProducto() + "): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            p.setNombreProducto(nuevoNombre);
        }
        System.out.print("Nuevo precio: ");
        String nuevoPrecioStr = scanner.nextLine();
        if (!nuevoPrecioStr.isEmpty()) {
            try {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                if (nuevoPrecio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                } else {
                    p.setPrecio(nuevoPrecio);
                }
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido. Debe ser un número.");
            }
        }
        System.out.print("Nuevo Stock: ");
        int nuevoStock = scanner.nextInt();
        scanner.nextLine();
        if (nuevoStock < 0) {
            System.out.println("El stock no puede ser menor que cero");
        } else {
            p.setStock(nuevoStock);
        }
        System.out.print("Nuevo descripción: ");
        String nuevaDescripcion = scanner.nextLine();
        if (!nuevaDescripcion.isEmpty()) {
            p.setDescricpion(nuevaDescripcion);
        }

        System.out.println("Producto actualizado.");
    }

    // METODO PARA ELIMINAR PRODUCTOS
    public void eliminarProductos(Scanner scanner) {
        System.out.print("ID del producto a eliminar: ");
        int id;
        String idStr = scanner.nextLine();
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Debe ser un número.");
            return;
        }
        Producto p = buscarProductos(id);
        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        listaProductos.remove(p);
        System.out.println("Producto eliminado.");
    }

    public void menuCrudProductos(Scanner scanner) {
        while (true) {
            System.out.println("\n--- CRUD PRODUCTOS ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Crear productos");
            System.out.println("3. Editar productos");
            System.out.println("4. Eliminar productos");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    listarProductos();
                    break;
                case "2":
                    crearProductos(scanner, proveedorController);
                    break;
                case "3":
                    editarProductos(scanner);
                    break;
                case "4":
                    eliminarProductos(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

}
