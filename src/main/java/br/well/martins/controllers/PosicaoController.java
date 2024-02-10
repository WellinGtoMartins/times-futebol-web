package br.well.martins.controllers;

import br.well.martins.models.Pessoa;
import br.well.martins.models.Posicao;
import br.well.martins.services.PosicaoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("posicaoController")
public class PosicaoController implements Serializable {

    private Posicao posicao;
    private List<Posicao> listaPosicoes;
    private String textoBuscar;
    private Integer id;

    @Inject
    private FacesContext facesContext;

    @Inject
    private PosicaoService service;

    public String listar() {
        return "/privado/posicao/listar?faces.redirect=true";
    }

    @PostConstruct
    public void init() {
        this.listaPosicoes = service.listar();
        posicao = new Posicao();
    }

    public void novo() {
        this.id = null;
        this.posicao = new Posicao();
        PrimeFaces.current().ajax().update("formPosicao");
        PrimeFaces.current().resetInputs("formPosicao");
    }


    public void alterar(Posicao posicao) {
        service.porId(posicao.getId()).ifPresent(p -> {
            this.posicao = p;
        });
        this.id = posicao.getId();
    }

    public void salvar() {
        String mensagem;
        this.posicao.setId(id);
        if (this.id != null && this.id > 0) {
            mensagem = " atualizado com êxito!";
        } else {
            mensagem = " adicionado com êxito!";
        }
        try {
            service.salvar(posicao);
            listaPosicoes = service.listar();
            facesContext.addMessage(null, new FacesMessage(posicao.getNome() + mensagem));
            posicao = new Posicao();
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF " + posicao.getNome() + " duplicado", null));
        }
    }


    public void excluir(Pessoa pessoa) {
        service.excluir(pessoa.getId());
        facesContext.addMessage(null, new FacesMessage(pessoa.getNome() + " eliminado com êxito!"));
        listaPosicoes = service.listar();
    }

    public void buscar() {
        this.listaPosicoes = service.buscarPorNome(this.textoBuscar);
    }


    public List<Posicao> getListaPosicoes() {
        return listaPosicoes;
    }

    public void setListaPosicoes(List<Posicao> listaPosicoes) {
        this.listaPosicoes = listaPosicoes;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
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
}
