package br.well.martins.repositories;

import br.well.martins.models.Jogador;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@RequestScoped
public class JogadorRepositoryImpl implements Repository<Jogador>{

    @Inject
    private EntityManager em;

    @Override
    public List<Jogador> listar() {
        return em.createQuery("select j from Jogador j", Jogador.class).getResultList();
    }

    @Override
    public List<Jogador> buscarPorNome(String nome) {
        return em.createQuery("select j from Jogador j where j.nome like :nome", Jogador.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public void salvar(Jogador jogador) {
        em.merge(jogador);
    }

    @Override
    public Jogador porId(Integer id) {
        return em.find(Jogador.class, id);
    }

    @Override
    public void excluir(Integer id) {
        em.remove(porId(id));
    }

}
