package br.well.martins.repositories;

import br.well.martins.models.Jogador;
import br.well.martins.models.Time;

public interface TimeRepository extends Repository<Time>{

    void adicionarJogador(Jogador jogador, Time time);
    void excluirJogador(Jogador jogador, Time time);

}
