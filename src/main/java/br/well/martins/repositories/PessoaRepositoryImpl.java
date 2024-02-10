package br.well.martins.repositories;

import br.well.martins.models.Pessoa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@RequestScoped
public class PessoaRepositoryImpl implements Repository<Pessoa> {

    @Inject
    private EntityManager em;

    @Override
    public List<Pessoa> listar() {
        return em.createQuery("select p from Pessoa p", Pessoa.class).getResultList();
    }

    @Override
    public List<Pessoa> buscarPorNome(String nome) {
        return em.createQuery("select p from Pessoa p where p.nome like :nome", Pessoa.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public void salvar(Pessoa pessoa) {
        if(pessoa.getId() != null && pessoa.getId() > 0) {
            em.merge(pessoa);
        } else {
            em.persist(pessoa);
        }
    }

    @Override
    public Pessoa porId(Integer id) {
        return em.find(Pessoa.class, id);
    }

    @Override
    public void excluir(Integer id) {
        Pessoa pessoa = porId(id);
        em.remove(pessoa);
    }
}
