package co.edu.udec.almacen.domain.port.out;

import co.edu.udec.almacen.domain.model.Producto;
import java.util.List;

public interface ProductoRepository {
    Producto guardar(Producto producto);
    Producto buscarPorId(int id);
    List<Producto> listarTodos();
    boolean actualizar(Producto producto);
    boolean eliminar(int id);
}