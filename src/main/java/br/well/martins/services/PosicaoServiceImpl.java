package br.well.martins.services;

import br.well.martins.models.Posicao;
import br.well.martins.repositories.Repository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class PosicaoServiceImpl implements PosicaoService {

    @Inject
    private Repository<Posicao> posicaoRepository;


    @Override
    public List<Posicao> listar() {
        return posicaoRepository.listar();
    }

    @Override
    public void salvar(Posicao posicao) {
        posicaoRepository.salvar(posicao);
    }

    @Override
    public Optional<Posicao> porId(Integer id) {
        return Optional.ofNullable(posicaoRepository.porId(id));
    }

    @Override
    public void excluir(Integer id) {
        posicaoRepository.excluir(id);

    }

    @Override
    public List<Posicao> buscarPorNome(String nome) {
        return posicaoRepository.buscarPorNome(nome);
    }

}
