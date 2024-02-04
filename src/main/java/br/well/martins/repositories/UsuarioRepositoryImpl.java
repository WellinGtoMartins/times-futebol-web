package br.well.martins.repositories;

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
        return em.createQuery("select c from Usuario c where c.nome like :nome", Usuario.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public void salvar(Usuario usuario) {
        if(usuario.getNomeUsuario() != null) {
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
        Usuario usuario = porId(id);
        em.remove(usuario);
    }

    @Override
    public Usuario porNomeUsuario(String nomeUsuario) {
        return em.find(Usuario.class, nomeUsuario);
    }
}
