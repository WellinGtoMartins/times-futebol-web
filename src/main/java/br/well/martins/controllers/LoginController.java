package br.well.martins.controllers;

import br.well.martins.models.Usuario;
import br.well.martins.services.UsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

@Model
@Named(value = "loginController")
public class LoginController {

    private Optional<Usuario> usuario;
    private String login;
    private String senha;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private UsuarioController usuarioController;

    @Inject
    private FacesContext facesContext;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Optional<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Optional<Usuario> usuario) {
        this.usuario = usuario;
    }

    private Usuario isValidLogin(String login, String senha) {
        usuario = usuarioService.login(login, senha);
        if(usuario.isEmpty()) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "login ou senha incorretos!.", null));
            return null;
        }
        return usuario.get();
    }

    public String entrar() {
        Usuario user = isValidLogin(login, senha);
        if(user != null) {
            usuarioController.setUsuario(user);
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.getSession().setAttribute("user", user);
            return "index.xhtml";
        }
        return null;
    }

}
