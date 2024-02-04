package br.well.martins.repositories;

import br.well.martins.models.Jogador;
import br.well.martins.models.Pessoa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

@RequestScoped
public class JogadorRepositoryImpl implements Repository<Jogador>{

    @Inject
    private EntityManager em;

    @Override
    public List<Jogador> listar() {
        return null;
    }

    @Override
    public List<Jogador> buscarPorNome(String nome) {
        return null;
    }

    @Override
    public void salvar(Jogador jogador) {
        if(jogador.getId() != null && jogador.getId() > 0) {
            em.merge(jogador);
        } else {
            em.persist(jogador);
        }
    }

    @Override
    public Jogador porId(Integer id) {
        return em.find(Jogador.class, id);
    }

    @Override
    public void excluir(Integer id) {
        Query query = em.createQuery("SELECT j FROM Jogador j JOIN j.time t WHERE j.id = :jogadorId");
        query.setParameter("jogadorId", id);
        Jogador jogador = (Jogador) query.getSingleResult();
        em.remove(jogador);
    }
}
