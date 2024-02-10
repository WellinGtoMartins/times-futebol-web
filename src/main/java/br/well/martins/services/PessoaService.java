package br.well.martins.services;

import br.well.martins.models.Pessoa;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface PessoaService {

    List<Pessoa> listar();
    void salvar(Pessoa pessoa) throws Exception;
    Optional<Pessoa> porId(Integer id);
    void excluir(Integer id);
    List<Pessoa> buscarPorNome(String nome);

}
