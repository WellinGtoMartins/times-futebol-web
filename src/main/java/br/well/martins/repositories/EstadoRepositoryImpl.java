package br.well.martins.repositories;

import br.well.martins.models.Estado;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class EstadoRepositoryImpl implements Repository<Estado> {

    @Inject
    private EntityManager em;

    @Override
    public List<Estado> listar() {
        return em.createQuery("select e from Estado e", Estado.class).getResultList();
    }

    @Override
    public List<Estado> buscarPorNome(String nome) {
        return em.createQuery("select e from Estado e where e.nome like :nome", Estado.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public void salvar(Estado estado) {
       if(estado.getId() != null && estado.getId() > 0) {
           em.merge(estado);
       } else {
           em.persist(estado);
       }
    }

    @Override
    public Estado porId(Integer id) {
        return em.find(Estado.class, id);
    }

    @Override
    public void excluir(Integer id) {
        Estado estado = porId(id);
        em.remove(estado);
    }

}
