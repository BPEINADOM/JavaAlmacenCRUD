package co.edu.udec.almacen.infrastructure.adapter.out.postgres;

import co.edu.udec.almacen.domain.model.Categoria;
import co.edu.udec.almacen.domain.model.Producto;
import co.edu.udec.almacen.domain.model.Proveedor;
import co.edu.udec.almacen.domain.port.out.ProductoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryPostgres implements ProductoRepository {

    @Override
    public Producto guardar(Producto producto) {
        String sql = "INSERT INTO producto (nombre, stock, categoria_id, proveedor_id) VALUES (?, ?, ?, ?) RETURNING id";
        
        try (Connection con = ConexionPostgreSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getStock());
            ps.setInt(3, producto.getCategoria().getId());
            ps.setInt(4, producto.getProveedor().getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                producto.setId(rs.getInt("id"));
                return producto;
            }
        } catch (Exception e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Producto buscarPorId(int id) {
        String sql = """
            SELECT p.id, p.nombre, p.stock, 
                   c.id AS cat_id, c.nombre AS cat_nombre,
                   pr.id AS prov_id, pr.nombre AS prov_nombre
            FROM producto p
            JOIN categoria c ON p.categoria_id = c.id
            JOIN proveedor pr ON p.proveedor_id = pr.id
            WHERE p.id = ?
        """;

        try (Connection con = ConexionPostgreSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearProducto(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = """
            SELECT p.id, p.nombre, p.stock, 
                   c.id AS cat_id, c.nombre AS cat_nombre,
                   pr.id AS prov_id, pr.nombre AS prov_nombre
            FROM producto p
            JOIN categoria c ON p.categoria_id = c.id
            JOIN proveedor pr ON p.proveedor_id = pr.id
            ORDER BY p.id
        """;

        try (Connection con = ConexionPostgreSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearProducto(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE producto SET nombre=?, stock=?, categoria_id=?, proveedor_id=? WHERE id=?";

        try (Connection con = ConexionPostgreSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getStock());
            ps.setInt(3, producto.getCategoria().getId());
            ps.setInt(4, producto.getProveedor().getId());
            ps.setInt(5, producto.getId());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id=?";

        try (Connection con = ConexionPostgreSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    // Método auxiliar para no repetir código de mapeo
    private Producto mapearProducto(ResultSet rs) throws Exception {
        Categoria cat = new Categoria(rs.getInt("cat_id"), rs.getString("cat_nombre"));
        Proveedor prov = new Proveedor(rs.getInt("prov_id"), rs.getString("prov_nombre"));
        
        return new Producto(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getInt("stock"),
            cat,
            prov
        );
    }
}