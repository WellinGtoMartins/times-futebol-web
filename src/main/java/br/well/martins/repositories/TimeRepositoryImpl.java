package br.well.martins.repositories;

import br.well.martins.configs.JogadorDuplicadoException;
import br.well.martins.models.Jogador;
import br.well.martins.models.Time;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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
    public void salvar(Time time){
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
    public void adicionarJogador(Jogador jogador, Time time) throws JogadorDuplicadoException {
        if (existeJogadorComCPF(jogador.getCpf())) {
            throw new JogadorDuplicadoException();
        } else {
            jogador.setTime(time);
            time.getJogadores().add(jogador);
        }
    }

    @Override
    public Time excluirJogador(Jogador jogador, Time time) {
        Time t = porId(time.getId());
        t.getJogadores().remove(jogador);
        jogador.setTime(null);
        em.merge(jogador);
        return t;
    }

    @Override
    public List<Jogador> jogadorPorNome(String nome, Integer idTime) {
        return em.createQuery("SELECT j FROM Time t JOIN t.jogadores j WHERE j.nome LIKE :nome AND t.id = :idTime", Jogador.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("idTime", idTime)
                .getResultList();
    }

    @Override
    public boolean existeJogadorComCPF(String cpf) {
        String jpql = "SELECT COUNT(j) FROM Jogador j WHERE j.cpf = :cpf";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("cpf", cpf);
        Long count = query.getSingleResult();
        return count > 0;
    }
}
