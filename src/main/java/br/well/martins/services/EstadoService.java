package br.well.martins.services;

import br.well.martins.models.Estado;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface EstadoService {

    List<Estado> listar();
    void salvar(Estado estado);
    Optional<Estado> porId(Integer id);
    void excluir(Integer id);
    List<Estado> buscarPorNome(String nome);

}
