package br.well.martins.services;

import br.well.martins.models.Estado;
import br.well.martins.models.Usuario;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface UsuarioService {

    List<Usuario> listar();
    void salvar(Usuario usuario);
    Optional<Usuario> porId(String login);
    Optional<Usuario> login(String login, String password);
    void excluir(Usuario usuario);

}
