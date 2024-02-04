package br.well.martins.services;

import br.well.martins.models.Cidade;
import br.well.martins.models.Estado;
import jakarta.ejb.Local;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Local
public interface CidadeService {

    List<Cidade> listar();
    void salvar(Cidade cidade);
    Optional<Cidade> cidadePorId(Integer id);
    void excluir(Integer id);
    List<Cidade> cidadePorNome(String nome);

    List<Estado> listarEstados();
    Optional<Estado> estadoPorId(Integer id);


}
