package co.edu.udec.almacen.domain.port.in;

import co.edu.udec.almacen.domain.model.Producto;
import java.util.List;

public interface GestionarProductoUseCase {
    Producto crearProducto(Producto producto);
    Producto obtenerProducto(int id);
    List<Producto> listarProductos();
    boolean modificarProducto(Producto producto);
    boolean eliminarProducto(int id);
}