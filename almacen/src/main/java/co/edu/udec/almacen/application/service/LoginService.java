package co.edu.udec.almacen.application.service;

import co.edu.udec.almacen.domain.model.Usuario;
import co.edu.udec.almacen.domain.port.in.LoginUseCase;
import co.edu.udec.almacen.domain.port.out.UsuarioRepository;

public class LoginService implements LoginUseCase {

    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario login(String username, String password) {
        Usuario u = usuarioRepository.login(username);

        if (u == null) return null;

        if (!u.getPassword().equals(password)) return null;

        return u;
    }
}
