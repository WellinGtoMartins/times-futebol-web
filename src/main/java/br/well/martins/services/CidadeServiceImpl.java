package br.well.martins.services;

import br.well.martins.models.Cidade;
import br.well.martins.models.Estado;
import br.well.martins.repositories.Repository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class CidadeServiceImpl implements CidadeService{

    @Inject
    private Repository<Cidade> cidadeRepository;

    @Inject
    private Repository<Estado> estadoRepository;

    @Override
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @Override
    public void salvar(Cidade cidade) {
        try {
            cidadeRepository.salvar(cidade);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cidade> cidadePorId(Integer id) {
        return Optional.ofNullable(cidadeRepository.porId(id));
    }

    @Override
    public void excluir(Integer id) {
        cidadeRepository.excluir(id);
    }

    @Override
    public List<Cidade> cidadePorNome(String nome) {
        return cidadeRepository.buscarPorNome(nome);
    }

    @Override
    public List<Estado> listarEstados() {
        return estadoRepository.listar();
    }

    @Override
    public Optional<Estado> estadoPorId(Integer id) {
        return Optional.ofNullable(estadoRepository.porId(id));
    }



}
