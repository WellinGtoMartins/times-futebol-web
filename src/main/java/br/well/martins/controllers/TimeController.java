package br.well.martins.controllers;

import br.well.martins.models.*;
import br.well.martins.services.JogadorService;
import br.well.martins.services.TimeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("timeController")
public class TimeController implements Serializable {

    private Time time;
    private List<Time> listaTimes;
    private String textoBuscar;
    private Integer id;
    private Boolean novoJogador;
    private Jogador jogador;

    @Inject
    private FacesContext facesContext;

    @Inject
    private TimeService timeService;

    @Inject
    private JogadorService jogadorService;

    @PostConstruct
    public void init() {
        this.listaTimes = timeService.listar();
        this.time = new Time();
    }

    public String listar() {
        return "/privado/time/listar?faces.redirect=true";
    }

    public void novoTime() {
        this.id = null;
        this.time = new Time();
        this.jogador = new Jogador();
        PrimeFaces.current().ajax().update("formTime");
        PrimeFaces.current().resetInputs("formTime");
        PrimeFaces.current().ajax().update("formJogador");
        PrimeFaces.current().resetInputs("formJogador");
    }

    public void alterarTime(Time time) {
        timeService.timePorId(time.getId()).ifPresent(t -> {
            this.time = t;
        });
        this.id = time.getId();
    }

    public void salvarTime() {
        String mensagem = null;
        this.time.setId(id);
        if (this.id != null && this.id > 0) {
            mensagem = " atualizado com êxito!";
        } else {
            mensagem = " adicionado com êxito!";
        }
        if (!novoJogador) {
            timeService.salvar(time);
        }
        listaTimes = timeService.listar();
        facesContext.addMessage(null, new FacesMessage(time.getNome() + mensagem));
        this.time = new Time();
    }

    public void excluirTime(Time time) {
        timeService.excluir(time.getId());
        facesContext.addMessage(null, new FacesMessage(time.getNome() + " eliminado com êxito!"));
        listaTimes = timeService.listar();
    }

    public void buscarTime() {
        this.listaTimes = timeService.timePorNome(this.textoBuscar);
    }

    public void novoJogador() {
        jogador = new Jogador();
        novoJogador = true;
        PrimeFaces.current().ajax().update("formJogador");
        PrimeFaces.current().resetInputs("formJogador");
    }

    public void salvarJogador() {
        String mensagem;
        if (novoJogador) {
            timeService.adicionarJogador(jogador, time);
            jogadorService.salvar(jogador);
            mensagem = " adicionado com êxito!";
        } else {
            jogadorService.salvar(jogador);
            mensagem = " atualizado com êxito!";
        }
        facesContext.addMessage(null, new FacesMessage(jogador.getNome() + mensagem));
        jogador = new Jogador();
    }

    public void alterarJogador(Integer rowIndex) {
        this.jogador = time.getJogadores().get(rowIndex);
        novoJogador = false;
    }

    public void excluirJogador(Integer rowIndex) {
        this.jogador = time.getJogadores().get(rowIndex);
        jogadorService.excluir(jogador.getId());
        this.novoJogador = true;
    }

    @Produces
    @Model
    public List<Cidade> cidades() {
        return timeService.listarCidades();
    }

    @Produces
    @Model
    public List<Usuario> usuarios() {
        return timeService.listarUsuarios();
    }

    @Produces
    @Model
    public List<Pessoa> pessoas() {
        return timeService.listarPessoas();
    }

    @Produces
    @Model
    public List<Posicao> posicoes() {
        return timeService.listarPosicoes();
    }


    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public List<Time> getListaTimes() {
        return listaTimes;
    }

    public void setListaTimes(List<Time> listaTimes) {
        this.listaTimes = listaTimes;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Boolean getNovoJogador() {
        return novoJogador;
    }

    public void setNovoJogador(Boolean novoJogador) {
        this.novoJogador = novoJogador;
    }
}
