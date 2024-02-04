package br.well.martins.repositories;

import br.well.martins.models.Cidade;
import br.well.martins.models.Posicao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class PosicaoRepositoryImpl implements Repository<Posicao> {

    @Inject
    private EntityManager em;

    @Override
    public List<Posicao> listar() {
        return em.createQuery("select c from Posicao c", Posicao.class).getResultList();
    }

    @Override
    public List<Posicao> buscarPorNome(String nome) {
        return em.createQuery("select c from Posicao c where c.nome like :nome", Posicao.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public void salvar(Posicao posicao) {
        if(posicao.getId() != null && posicao.getId() > 0) {
            em.merge(posicao);
        } else {
            em.persist(posicao);
        }
    }

    @Override
    public Posicao porId(Integer id) {
        return em.find(Posicao.class, id);
    }

    @Override
    public void excluir(Integer id) {
        Posicao posicao = porId(id);
        em.remove(posicao);
    }


}
