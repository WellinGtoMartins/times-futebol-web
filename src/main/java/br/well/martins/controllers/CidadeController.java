package br.well.martins.controllers;

import br.well.martins.models.Cidade;
import br.well.martins.models.Estado;
import br.well.martins.services.CidadeService;
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
import java.util.List;

@ViewScoped
@Named("cidadeController")
public class CidadeController implements Serializable {

    private Cidade cidade;
    private List<Cidade> listaCidades;
    private String textoBuscar;
    private Integer id;

    @Inject
    private FacesContext facesContext;

    @Inject
    private CidadeService service;

    public String listar() {
        return "/privado/cidade/listar?faces.redirect=true";
    }

    @PostConstruct
    public void init() {
        this.listaCidades = service.listar();
        cidade = new Cidade();
    }

    public void novo() {
        this.id = null;
        this.cidade = new Cidade();
        PrimeFaces.current().ajax().update("formCidade");
        PrimeFaces.current().resetInputs("formCidade");
    }

    public void alterar(Cidade cidade) {
        service.cidadePorId(cidade.getId()).ifPresent(e -> {
            this.cidade = e;
        });
        this.id = cidade.getId();
    }

    @Produces
    @Model
    public List<Estado> estados() {
        return service.listarEstados();
    }

    public void salvar() {
        String mensagem;
        this.cidade.setId(id);
        if (this.id != null && this.id > 0) {
            mensagem = " atualizado com êxito!";
        } else {
            mensagem = " adicionado com êxito!";
        }
        service.salvar(cidade);
        listaCidades = service.listar();
        facesContext.addMessage(null, new FacesMessage(cidade.getNome() + mensagem));
        this.cidade = new Cidade();
    }


    public void excluir(Cidade cidade) {
        service.excluir(cidade.getId());
        facesContext.addMessage(null, new FacesMessage(cidade.getNome() + " eliminado com êxito!"));
        listaCidades = service.listar();
    }


    public void buscar() {
        this.listaCidades = service.cidadePorNome(this.textoBuscar);
    }


    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getListaCidades() {
        return this.listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
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
