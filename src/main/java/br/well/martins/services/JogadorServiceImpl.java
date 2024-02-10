package br.well.martins.services;

import br.well.martins.models.Jogador;
import br.well.martins.repositories.Repository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class JogadorServiceImpl implements JogadorService{

    @Inject
    private Repository<Jogador> jogadorRepository;

    @Override
    public List<Jogador> listar() {
        return null;
    }

    @Override
    public void salvar(Jogador jogador) {
        try {
            jogadorRepository.salvar(jogador);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Jogador> porId(Integer id) {
        return Optional.ofNullable(jogadorRepository.porId(id));
    }

    @Override
    public void excluir(Integer id) {
        jogadorRepository.excluir(id);
    }

    @Override
    public List<Jogador> buscarPorNome(String nome) {
        return jogadorRepository.buscarPorNome(nome);
    }


}
