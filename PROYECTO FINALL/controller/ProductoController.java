package controller;

import Model.Producto;
import Model.Proveedor;
import java.util.LinkedList;
import java.util.Scanner;

public class ProductoController {

    private LinkedList<Producto> listaProductos = new LinkedList<>();
    private int nextId = 1;

    private ProveedorController proveedorController;

    // Proveedor inicial para productos por defecto
    Proveedor proveedorOne = new Proveedor(1, "World Meditation", "18768463846");

    // ===== Colores ANSI =====
    final String RESET   = "\u001B[0m";   // reset
    final String RED     = "\u001B[31m";  // rojo
    final String GREEN   = "\u001B[32m";  // verde
    final String YELLOW  = "\u001B[33m";  // amarillo
    final String BLUE    = "\u001B[34m";  // azul
    final String PURPLE  = "\u001B[35m";  // morado
    final String CYAN    = "\u001B[36m";  // cian
    final String WHITE   = "\u001B[37m";  // blanco

    public ProductoController(ProveedorController proveedorController) {
        this.proveedorController = proveedorController;
    }

    // ====== SUBIR PRODUCTOS PREDEFINIDOS ======
    public void subirProductos() {
        listaProductos.add(new Producto(1, "Jardín meditación", 128000, 10,
                "Base en concreto, figura en yeso, arena, piedras, rastrillo, vela y palo santo.", proveedorOne));
        listaProductos.add(new Producto(2, "Jardín Protección", 65200, 15,
                "Base caracol en concreto, figura en yeso, arena, piedras, rastrillo, vela y palo santo.", proveedorOne));
        listaProductos.add(new Producto(3, "Porta incienso mandala", 24500, 20,
                "Ayuda a relajar el sistema nervioso y favorecer la concentración.", proveedorOne));
        listaProductos.add(new Producto(4, "Porta vela luna", 27300, 12,
                "Simboliza purificación, calma y conexión interior.", proveedorOne));
    }

    // ===== LISTAR PRODUCTOS (EN TABLA) =====
    public void listarProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println(">>> No hay productos registrados. <<<");
            return;
        }

        System.out.println(CYAN + "\n+----+----------------------+------------+--------+-----------------------------+-------------------+" + RESET);
        System.out.printf("| %-2s | %-30s | %-10s | %-6s | %-50s | %-17s |%n",
                "ID", "Nombre", "Precio", "Stock", "Descripción", "Proveedor");
        System.out.println(CYAN + "+----+----------------------+------------+--------+-----------------------------+-------------------+" + RESET);

        for (Producto p : listaProductos) {
            System.out.printf("| %-2d | %-20s | %-10.2f | %-6d | %-27s | %-17s |%n",
                    p.getId(),
                    p.getNombreProducto(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getDescripcion().length() > 25 ? p.getDescripcion().substring(0, 24) + "…" : p.getDescripcion(),
                    p.getProveedor().getNombre());
        }

        System.out.println(CYAN + "+----+----------------------+------------+--------+-----------------------------+-------------------+" + RESET);
    }

    // ===== CREAR PRODUCTO =====
    public void crearProductos(Scanner scanner, ProveedorController proveedorController) {
        int id = nextId++;

        System.out.print(BLUE + "Nombre del Producto: " + RESET);
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println(">>> El nombre no puede estar vacío. <<<");
            return;
        }
        if (buscarProductoPorNombre(nombre) != null) {
            System.out.println(">>> Ya existe un producto con ese nombre. <<<");
            return;
        }

        System.out.print(BLUE + "Descripción: " + RESET);
        String descripcion = scanner.nextLine();

        double precio = -1;
        while (precio < 0) {
            System.out.print(BLUE + "Precio: " + RESET);
            try {
                precio = Double.parseDouble(scanner.nextLine());
                if (precio < 0) System.out.println(">>> El precio no puede ser negativo. <<<");
            } catch (NumberFormatException e) {
                System.out.println(">>> Precio inválido. Debe ser un número. <<<");
            }
        }

        int stock = -1;
        while (stock < 0) {
            System.out.print(BLUE + "Stock: " + RESET);
            try {
                stock = Integer.parseInt(scanner.nextLine());
                if (stock < 0) System.out.println(">>> El stock no puede ser negativo. <<<");
            } catch (NumberFormatException e) {
                System.out.println(">>> Stock inválido. Debe ser un número entero. <<<");
            }
        }

        // Asociar proveedor
        Proveedor proveedor = proveedorController.registrarProveedor(scanner);

        listaProductos.add(new Producto(id, nombre, precio, stock, descripcion, proveedor));
        System.out.println(">>> El producto '" + nombre + "' ha sido creado exitosamente. <<<");
    }

    // ===== BUSCAR PRODUCTOS =====
    public Producto buscarProductos(int id) {
        for (Producto p : listaProductos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto p : listaProductos) {
            if (p.getNombreProducto().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }

    // ===== EDITAR PRODUCTO =====
    public void editarProductos(Scanner scanner) {
        System.out.print(BLUE + "ID del producto a editar: " + RESET);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(">>> ID inválido. <<<");
            return;
        }

        Producto p = buscarProductos(id);
        if (p == null) {
            System.out.println(">>> Producto no encontrado. <<<");
            return;
        }

        System.out.print(BLUE + "Nuevo nombre (actual: " + p.getNombreProducto() + "): " + RESET);
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) p.setNombreProducto(nuevoNombre);

        System.out.print(BLUE + "Nuevo precio: " + RESET);
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) {
            try {
                double nuevoPrecio = Double.parseDouble(precioStr);
                if (nuevoPrecio >= 0) p.setPrecio(nuevoPrecio);
                else System.out.println(">>> El precio no puede ser negativo. <<<" + RESET);
            } catch (NumberFormatException e) {
                System.out.println(">>> Precio inválido. <<<");
            }
        }

        System.out.print(BLUE + "Nuevo stock: " + RESET);
        try {
            int nuevoStock = Integer.parseInt(scanner.nextLine());
            if (nuevoStock >= 0) p.setStock(nuevoStock);
            else System.out.println(">>> El stock no puede ser menor que cero. <<<");
        } catch (NumberFormatException e) {
            System.out.println(">>> Stock inválido. <<<");
        }

        System.out.print(BLUE + "Nueva descripción: " + RESET);
        String nuevaDescripcion = scanner.nextLine();
        if (!nuevaDescripcion.isEmpty()) p.setDescricpion(nuevaDescripcion);

        System.out.println(">>> Producto actualizado correctamente. <<<");
    }

    // ===== ELIMINAR PRODUCTO =====
    public void eliminarProductos(Scanner scanner) {
        System.out.print(BLUE + "ID del producto a eliminar: " + RESET);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(">>> ID inválido. <<<");
            return;
        }
        Producto p = buscarProductos(id);
        if (p == null) {
            System.out.println(">>> Producto no encontrado. <<<");
            return;
        }
        listaProductos.remove(p);
        System.out.println(">>> Producto eliminado exitosamente. <<<");
    }

    // ===== MENÚ CRUD PRODUCTOS =====
    public void menuCrudProductos(Scanner scanner) {
        while (true) {
            System.out.println(CYAN + "  ╔═══════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "  ║                CRUD DE PRODUCTOS              ║" + RESET);
            System.out.println("  ║                                               ║");
            System.out.println(GREEN + "  ║  -1. Listar productos                         ║" + RESET);
            System.out.println(GREEN + "  ║  -2. Crear productos                          ║" + RESET);
            System.out.println(GREEN + "  ║  -3. Editar productos                         ║" + RESET);
            System.out.println(GREEN + "  ║  -4. Eliminar productos                       ║" + RESET);
            System.out.println(GREEN + "  ║  -5. Volver                                   ║" + RESET);
            System.out.println("  ║                                               ║");
            System.out.println(PURPLE + "  ║  -> Seleccione una opción:                    ║" + RESET);
            System.out.println(CYAN + "   ╚═══════════════════════════════════════════════╝" + RESET);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1": listarProductos(); break;
                case "2": crearProductos(scanner, proveedorController); break;
                case "3": editarProductos(scanner); break;
                case "4": eliminarProductos(scanner); break;
                case "5": return;
                default: System.out.println(">>> Opción inválida. <<<");
            }
        }
    }
}
