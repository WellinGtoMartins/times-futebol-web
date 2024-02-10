package br.well.martins.controllers;

import br.well.martins.models.Estado;
import br.well.martins.models.Pessoa;
import br.well.martins.services.EstadoService;
import br.well.martins.services.PessoaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ConstraintViolationException;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("pessoaController")
public class PessoaController implements Serializable {

    private Pessoa pessoa;
    private List<Pessoa> listaPessoas;
    private String textoBuscar;
    private Integer id;

    @Inject
    private FacesContext facesContext;

    @Inject
    private PessoaService service;

    public String listar() {
        return "/privado/pessoa/listar?faces.redirect=true";
    }

    @PostConstruct
    public void init() {
        this.listaPessoas = service.listar();
        pessoa = new Pessoa();
    }

    public void novo() {
        this.id = null;
        this.pessoa = new Pessoa();
        PrimeFaces.current().ajax().update("formPessoa");
        PrimeFaces.current().resetInputs("formPessoa");
    }


    public void alterar(Pessoa pessoa) {
        service.porId(pessoa.getId()).ifPresent(p -> {
            this.pessoa = p;
        });
        this.id = pessoa.getId();
    }

    public void salvar() {
        String mensagem;
        this.pessoa.setId(id);
        if (this.id != null && this.id > 0) {
            mensagem = " atualizado com êxito!";
        } else {
            mensagem = " adicionado com êxito!";
        }
        try {
            service.salvar(pessoa);
            listaPessoas = service.listar();
            facesContext.addMessage(null, new FacesMessage(pessoa.getNome() + mensagem));
            pessoa = new Pessoa();
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF " + pessoa.getCpf() + " duplicado", null));
        }
    }


    public void excluir(Pessoa pessoa) {
        service.excluir(pessoa.getId());
        facesContext.addMessage(null, new FacesMessage(pessoa.getNome() + " eliminado com êxito!"));
        listaPessoas = service.listar();
    }

    public void buscar() {
        this.listaPessoas = service.buscarPorNome(this.textoBuscar);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
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
