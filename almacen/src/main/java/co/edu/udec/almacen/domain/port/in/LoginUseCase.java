package co.edu.udec.almacen.domain.port.in;

import co.edu.udec.almacen.domain.model.Usuario;

public interface LoginUseCase {
    Usuario login(String username, String password);
}
