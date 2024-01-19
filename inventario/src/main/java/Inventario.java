//No consigo que se ejecute el código por algo del paquete com.mycompany.inventario y no sé como arreglarlo, asi que no se si funciona.
import java.util.ArrayList;
import java.util.Scanner;

// Clase del producto con sus características
class Producto {
    String nombre;
    double precio;
    int cantidadEnStock;

    // Constructor para inicializar el producto
    public Producto(String nombre, double precio, int cantidadEnStock) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
    }
}

public class Inventario {

    // ArrayList para almacenar los productos en el inventario
    private static final ArrayList<Producto> inventario = new ArrayList<>();
    
    //Creamos un metodo para agregar los productos
    public static void agregarProducto(Scanner scanner) {
        System.out.print("nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("precio: ");
        double precio = scanner.nextDouble();

        System.out.print("cantidad: ");
        int cantidad = scanner.nextInt();

        Producto nuevoProducto = new Producto(nombre, precio, cantidad);
        inventario.add(nuevoProducto);

        System.out.println("Hecho.");
    }
    //otro método, pero esta vez para actualizar el inventario
    public static void actualizarInventario(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a actualizar: ");
        String nombreBuscado = scanner.nextLine();

        for (Producto producto : inventario) {
            if (producto.nombre.equalsIgnoreCase(nombreBuscado)) {
                System.out.print("Ingrese la nueva cantidad en stock: ");
                int nuevaCantidad = scanner.nextInt();

                producto.cantidadEnStock = nuevaCantidad;
                System.out.println("Hecho.");
                return;
            }
        }
        System.out.println("Producto no encontrado en el inventario.");
    }
    
    //método para buscar un producto 
    public static void buscarProducto(Scanner scanner) {
        System.out.print("nombre: ");
        String nombreBuscado = scanner.nextLine();
        
        //bucle para recorrer cada producto del inventario
        for (Producto producto : inventario) {
            if (producto.nombre.equalsIgnoreCase(nombreBuscado)) {
                System.out.println("\nProducto encontrado:");
                System.out.println("Nombre: " + producto.nombre);
                System.out.println("Precio: $" + producto.precio);
                System.out.println("Cantidad en stock: " + producto.cantidadEnStock);
                return;
            }
            else
            {
                System.out.println("Producto no encontrado en el inventario.");
            }
        }

        
    }
    //Último método para mostrar el inventario al usuario
    public static void mostrarInventario() {
        System.out.println("\n---- Inventario Actual ----");
        //Otra vez recorremos el inventario con este for
        for (Producto producto : inventario) {
            System.out.println("Nombre: " + producto.nombre);
            System.out.println("Precio: " + producto.precio + "€");
            System.out.println("Cantidad en stock: " + producto.cantidadEnStock);
            System.out.println("--------------------------");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hacemos un bucle con el menú y las opciones. Se repetirá hasta que elijamos la opción salir
        while (true) {
            // Imprimimos el menú de opciones
            System.out.println("\n---- Menú de Inventario ----");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Actualizar Inventario");
            System.out.println("3. Buscar Producto");
            System.out.println("4. Mostrar Inventario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            // el usuario elige una opción
            int opcion = scanner.nextInt();
            scanner.nextLine();

            // switch con todas las opciones, llamando a los métodos de arriba
            switch (opcion) {
                case 1:
                    agregarProducto(scanner);
                    break;
                case 2:
                    actualizarInventario(scanner);
                    break;
                case 3:
                    buscarProducto(scanner);
                    break;
                case 4:
                    mostrarInventario();
                    break;
                case 5:
                    System.out.println("¡Hasta pronto!");
                    System.exit(0);
                default:
                    System.out.println("Error. Inténtelo de nuevo.");
            }
        }
    }
}
