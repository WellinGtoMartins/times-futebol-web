package br.well.martins.repositories;

import br.well.martins.models.Jogador;
import br.well.martins.models.Time;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class TimeRepositoryImpl implements TimeRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Time> listar() {
        return em.createQuery("select t from Time t left outer join fetch t.jogadores", Time.class).getResultList();
    }

    @Override
    public List<Time> buscarPorNome(String nome) {
        return em.createQuery("select t from Time t where t.nome like :nome", Time.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public void salvar(Time time) {
        em.merge(time);
    }

    @Override
    public Time porId(Integer id) {
       return em.createQuery("select t from Time t left outer join fetch t.jogadores where t.id=:id", Time.class)
               .setParameter("id", id)
               .getSingleResult();
    }

    @Override
    public void excluir(Integer id) {
        Time time = porId(id);
        em.remove(time);
    }

    @Override
    public void adicionarJogador(Jogador jogador, Time time) {
        jogador.setTime(time);
        time.getJogadores().add(jogador);
    }

    @Override
    public void excluirJogador(Jogador jogador, Time time) {
        time.getJogadores().remove(jogador);
        jogador.setTime(null);
    }
}
