package br.well.martins.services;

import br.well.martins.models.Jogador;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface JogadorService {

    List<Jogador> listar();
    void salvar(Jogador jogador);
    Optional<Jogador> porId(Integer id);
    void excluir(Integer id);
    List<Jogador> buscarPorNome(String nome);

}
