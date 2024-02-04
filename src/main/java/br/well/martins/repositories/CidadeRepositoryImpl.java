package br.well.martins.repositories;

import br.well.martins.models.Cidade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class CidadeRepositoryImpl implements Repository<Cidade> {

    @Inject
    private EntityManager em;

    @Override
    public List<Cidade> listar() {
        return em.createQuery("select c from Cidade c", Cidade.class).getResultList();
    }

    @Override
    public List<Cidade> buscarPorNome(String nome) {
        return em.createQuery("select c from Cidade c where c.nome like :nome", Cidade.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public void salvar(Cidade cidade) {
        if(cidade.getId() != null && cidade.getId() > 0) {
            em.merge(cidade);
        } else {
            em.persist(cidade);
        }
    }

    @Override
    public Cidade porId(Integer id) {
        return em.find(Cidade.class, id);
    }

    @Override
    public void excluir(Integer id) {
        Cidade cidade = porId(id);
        em.remove(cidade);
    }


}
