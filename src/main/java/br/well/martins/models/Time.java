package br.well.martins.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "time")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 50, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data de fundação deve ser informada")
    @Column(name = "data_fundacao", nullable = false)
    private Date dataFundacao;

    @Column(name = "historia", columnDefinition = "text")
    private String historia;

    @NotNull(message = "A cidade deve ser informada")
    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false)
    private Cidade cidade;

    @NotNull(message = "O usuario deve ser informado")
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "nome_usuario", nullable = false)
    private Usuario usuario;

    @NotNull(message = "O técnico deve ser informado")
    @ManyToOne
    @JoinColumn(name = "tecnico", referencedColumnName = "id", nullable = false)
    private Pessoa tecnico;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Jogador> jogadores = new ArrayList<>();

    public Time() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pessoa getTecnico() {
        return tecnico;
    }

    public void setTecnico(Pessoa tecnico) {
        this.tecnico = tecnico;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(id, time.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
