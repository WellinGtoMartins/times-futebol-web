package br.well.martins.services;

import br.well.martins.models.Usuario;
import br.well.martins.repositories.UsuarioRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.listar();
    }

    @Override
    public void salvar(Usuario usuario) {
        usuarioRepository.salvar(usuario);
    }

    @Override
    public Optional<Usuario> porId(String login) {
        return Optional.ofNullable(usuarioRepository.usuarioPorLogin(login));
    }

    @Override
    public Optional<Usuario> login(String login, String password) {
        return Optional.ofNullable(usuarioRepository.usuarioPorLogin(login))
                .filter(u -> u.getSenha().equals(password));
    }

    @Override
    public void excluir(Usuario usuario) {
        usuarioRepository.excluirUsuario(usuario);
    }
}
