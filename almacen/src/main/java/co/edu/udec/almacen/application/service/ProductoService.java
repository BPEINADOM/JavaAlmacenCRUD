package co.edu.udec.almacen.application.service;

import co.edu.udec.almacen.domain.model.Producto;
import co.edu.udec.almacen.domain.port.in.GestionarProductoUseCase;
import co.edu.udec.almacen.domain.port.out.ProductoRepository;
import java.util.List;

public class ProductoService implements GestionarProductoUseCase {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        // Aquí podrías agregar validaciones de negocio antes de guardar
        return productoRepository.guardar(producto);
    }

    @Override
    public Producto obtenerProducto(int id) {
        return productoRepository.buscarPorId(id);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.listarTodos();
    }

    @Override
    public boolean modificarProducto(Producto producto) {
        return productoRepository.actualizar(producto);
    }

    @Override
    public boolean eliminarProducto(int id) {
        return productoRepository.eliminar(id);
    }
}