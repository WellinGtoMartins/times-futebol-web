package br.well.martins.repositories;

import br.well.martins.models.Estado;
import br.well.martins.models.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;


@RequestScoped
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Usuario> listar() {
        return em.createQuery("select u from Usuario u", Usuario.class).getResultList();
    }

    @Override
    public List<Usuario> buscarPorNome(String nome) {
        return null;
    }

    @Override
    public void salvar(Usuario usuario) {
        if(usuario.getLogin() != null) {
            em.merge(usuario);
        } else {
            em.persist(usuario);
        }
    }

    @Override
    public Usuario porId(Integer id) {
        return null;
    }

    @Override
    public void excluir(Integer id) {
    }

    @Override
    public Usuario usuarioPorLogin(String login) {
        return em.find(Usuario.class, login);
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        em.remove(usuario);
    }
}
