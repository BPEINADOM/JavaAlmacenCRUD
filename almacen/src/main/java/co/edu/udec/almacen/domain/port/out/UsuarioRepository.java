package co.edu.udec.almacen.domain.port.out;

import co.edu.udec.almacen.domain.model.Usuario;

public interface UsuarioRepository {
    Usuario login(String username);
}
