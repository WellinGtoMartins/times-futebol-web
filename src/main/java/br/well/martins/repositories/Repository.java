package br.well.martins.repositories;

import java.util.List;

public interface Repository<T>  {

    List<T> listar();
    List<T> buscarPorNome(String nome);
    void salvar(T t);
    T porId(Integer id);
    void excluir(Integer id);

}
