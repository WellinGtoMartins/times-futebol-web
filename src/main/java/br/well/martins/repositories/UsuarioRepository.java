package br.well.martins.repositories;

import br.well.martins.models.Usuario;

public interface UsuarioRepository extends Repository<Usuario> {

    Usuario porNomeUsuario(String nomeUsuario);

}
