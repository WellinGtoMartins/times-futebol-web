package br.well.martins.services;


import br.well.martins.exceptions.JogadorDuplicadoException;
import br.well.martins.models.*;
import br.well.martins.repositories.Repository;
import br.well.martins.repositories.TimeRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class TimeServiceImpl implements TimeService {

    @Inject
    private TimeRepository timeRepository;

    @Inject
    private Repository<Cidade> cidadeRepository;

    @Inject
    private Repository<Pessoa> pessoaRepository;

    @Inject
    private Repository<Posicao> posicaoRepository;

    @Override
    public List<Time> listar() {
        return timeRepository.listar();
    }

    @Override
    public void salvar(Time time) {
        timeRepository.salvar(time);
    }

    @Override
    public Optional<Time> timePorId(Integer id) {
        return Optional.ofNullable(timeRepository.porId(id));
    }

    @Override
    public void excluir(Integer id) {
        timeRepository.excluir(id);
    }

    @Override
    public List<Time> timePorNome(String nome) {
        return timeRepository.buscarPorNome(nome);
    }


    @Override
    public List<Cidade> listarCidades() {
        return cidadeRepository.listar();
    }

    @Override
    public Optional<Cidade> cidadePorId(Integer id) {
        return Optional.ofNullable(cidadeRepository.porId(id));
    }

    @Override
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.listar();
    }

    @Override
    public Optional<Pessoa> pessoaPorId(Integer id) {
        return Optional.ofNullable(pessoaRepository.porId(id));
    }

    @Override
    public List<Posicao> listarPosicoes() {
        return posicaoRepository.listar();
    }

    @Override
    public Optional<Posicao> posicaoPorId(Integer id) {
        return Optional.ofNullable(posicaoRepository.porId(id));
    }

    @Override
    public void adicionarJogador(Jogador jogador, Time time) throws JogadorDuplicadoException {
       timeRepository.adicionarJogador(jogador, time);
    }

    @Override
    public Time excluirJogador(Jogador jogador, Time time) {
       return timeRepository.excluirJogador(jogador, time);
    }

    @Override
    public List<Jogador> jogadorPorNome(String nome, Integer idTime) {
        return timeRepository.jogadorPorNome(nome,idTime);
    }


}
