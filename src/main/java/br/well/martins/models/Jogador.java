package br.well.martins.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_jogador")
public class Jogador extends Pessoa {

    @NotNull(message = "O peso deve ser informado")
    @Column(name = "peso", nullable = false, columnDefinition = "numeric(6,3)")
    private Double peso;

    @NotNull(message = "A altura deve ser informada")
    @Column(name = "altura", nullable = false, columnDefinition = "numeric(4,2)")
    private Double altura;

    @NotNull(message = "A posição deve ser informada")
    @ManyToOne
    @JoinColumn(name = "posicao", referencedColumnName = "id", nullable = false)
    private Posicao posicao;

    @ManyToOne
    @JoinColumn(name = "time", referencedColumnName = "id")
    private Time time;

    public Jogador() {

    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
