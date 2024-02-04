package br.well.martins.controllers;

import br.well.martins.models.Estado;
import br.well.martins.services.EstadoService;
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
@Named("estadoController")
public class EstadoController implements Serializable {

    private Estado estado;
    private List<Estado> listaEstados;
    private String textoBuscar;
    private Integer id;

    @Inject
    private FacesContext facesContext;

    @Inject
    private EstadoService service;

    public String listar() {
        return "/privado/estado/listar?faces.redirect=true";
    }

    @PostConstruct
    public void init() {
        this.listaEstados = service.listar();
        estado = new Estado();
    }

    public void novo() {
        this.id = null;
        this.estado = new Estado();
        PrimeFaces.current().ajax().update("formEstado");
        PrimeFaces.current().resetInputs("formEstado");
    }


    public void alterar(Estado estado) {
        service.porId(estado.getId()).ifPresent(e -> {
            this.estado = e;
        });
        this.id = estado.getId();
    }

    public void salvar() {
        String mensagem;
        try {
            this.estado.setId(id);
            if (this.id != null && this.id > 0) {
                mensagem = " atualizado com êxito!";
            } else {
                mensagem = " adicionado com êxito!";
            }
            service.salvar(estado);
            listaEstados = service.listar();
            facesContext.addMessage(null, new FacesMessage(estado.getNome() + mensagem));
            estado = new Estado();
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome existente! Não é permitido Estados com o mesmo nome.", "Um estado com o nome '" + this.estado.getNome() + "' já existe na base de dados."));
        }

    }


    public void excluir(Estado estado) {
        service.excluir(estado.getId());
        facesContext.addMessage(null, new FacesMessage(estado.getNome() + " eliminado com êxito!"));
        listaEstados = service.listar();
    }

    public void buscar() {
        this.listaEstados = service.buscarPorNome(this.textoBuscar);
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getListaEstados() {
        return this.listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
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
