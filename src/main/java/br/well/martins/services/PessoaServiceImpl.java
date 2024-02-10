package br.well.martins.services;

import br.well.martins.models.Pessoa;
import br.well.martins.repositories.Repository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class PessoaServiceImpl implements PessoaService {

    @Inject
    private Repository<Pessoa> pessoaRepository;


    @Override
    public List<Pessoa> listar() {
        return pessoaRepository.listar();
    }

    @Override
    public void salvar(Pessoa pessoa) {
        pessoaRepository.salvar(pessoa);
    }

    @Override
    public Optional<Pessoa> porId(Integer id) {
        return Optional.ofNullable(pessoaRepository.porId(id));
    }

    @Override
    public void excluir(Integer id) {
        pessoaRepository.excluir(id);

    }

    @Override
    public List<Pessoa> buscarPorNome(String nome) {
        return pessoaRepository.buscarPorNome(nome);
    }

}
