package br.well.martins.services;

import br.well.martins.models.Estado;
import br.well.martins.repositories.Repository;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class EstadoServiceImpl implements EstadoService {

    @Inject
    private Repository<Estado> repository;

    @Override
    public List<Estado> listar() {
        return repository.listar();
    }

    @Override
    public void salvar(Estado estado) {
        repository.salvar(estado);
    }

    @Override
    public Optional<Estado> porId(Integer id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void excluir(Integer id) {
        repository.excluir(id);
    }

    @Override
    public List<Estado> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }


}
