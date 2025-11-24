package co.edu.udec.almacen.domain.model;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private Categoria categoria; // Asumiendo que crearás esta clase
    private Proveedor proveedor; // Asumiendo que crearás esta clase

    public Producto(int id, String nombre, int stock, Categoria categoria, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public Producto() {
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
}