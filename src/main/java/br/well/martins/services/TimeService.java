package br.well.martins.services;

import br.well.martins.configs.JogadorDuplicadoException;
import br.well.martins.models.*;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface TimeService {

    List<Time> listar();
    void salvar(Time time);
    Optional<Time> timePorId(Integer id);
    void excluir(Integer id);
    List<Time> timePorNome(String nome);

    List<Cidade> listarCidades();
    Optional<Cidade> cidadePorId(Integer id);

    List<Pessoa> listarPessoas();
    Optional<Pessoa> pessoaPorId(Integer id);

    List<Posicao> listarPosicoes();
    Optional<Posicao> posicaoPorId(Integer id);

    void adicionarJogador(Jogador jogador, Time time) throws JogadorDuplicadoException;
    Time excluirJogador(Jogador jogador, Time time);
    List<Jogador> jogadorPorNome(String nome, Integer idTime);


}
