package br.well.martins.services;

import br.well.martins.models.Pessoa;
import br.well.martins.models.Posicao;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface PosicaoService {

    List<Posicao> listar();
    void salvar(Posicao posicao);
    Optional<Posicao> porId(Integer id);
    void excluir(Integer id);
    List<Posicao> buscarPorNome(String nome);

}
