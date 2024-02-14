package br.well.martins.repositories;

import br.well.martins.configs.JogadorDuplicadoException;
import br.well.martins.models.Jogador;
import br.well.martins.models.Time;

import java.util.List;

public interface TimeRepository extends Repository<Time>{

    void adicionarJogador(Jogador jogador, Time time) throws JogadorDuplicadoException;
    Time excluirJogador(Jogador jogador, Time time);
    List<Jogador> jogadorPorNome(String nome, Integer idTime);
    boolean existeJogadorComCPF(String cpf);

}
