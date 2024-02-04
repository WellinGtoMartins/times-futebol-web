package br.well.martins.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "permissao")
public class Permissao {

    @Id
    @NotBlank(message = "O nome da permissão deve ser informado")
    @Length(max = 30, message = "A permissão não deve ter mais que {max} caracteres")
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotBlank(message = "O descrição deve ser informada")
    @Length(max = 40, message = "A descrição não deve ter mais que {max} caracteres")
    @Column(name = "descricao", length = 40, nullable = false)
    private String descricao;

    public Permissao() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissao permissao = (Permissao) o;
        return Objects.equals(nome, permissao.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
