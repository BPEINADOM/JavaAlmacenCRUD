package co.edu.udec.almacen.infrastructure.adapter.out.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.edu.udec.almacen.domain.model.Rol;
import co.edu.udec.almacen.domain.model.Usuario;
import co.edu.udec.almacen.domain.port.out.UsuarioRepository;

public class UsuarioRepositoryPostgres implements UsuarioRepository {

    @Override
    public Usuario login(String username) {
        String sql = """
            SELECT u.id, u.username, u.password,
                   r.id AS rol_id, r.nombre AS rol_nombre
            FROM usuario u
            JOIN rol r ON u.rol_id = r.id
            WHERE u.username = ?
        """;

        try (Connection con = ConexionPostgreSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Rol rol = new Rol(
                        rs.getInt("rol_id"),
                        rs.getString("rol_nombre")
                );

                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rol
                );
            }

        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
        }

        return null;
    }
}
