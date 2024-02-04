package br.well.martins.configs;

import br.well.martins.models.Cidade;
import br.well.martins.models.Usuario;
import br.well.martins.services.TimeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@RequestScoped
@Named("usuarioConverter")
public class UsuarioConverter implements Converter<Usuario> {

    @Inject
    private TimeService service;

    @Override
    public Usuario getAsObject(FacesContext facesContext, UIComponent uiComponent, String nomeUsuario) {
        if(nomeUsuario == null) {
            return null;
        }
        Optional<Usuario> usuarioOptional = service.usuarioPorNomeUsuario(nomeUsuario);
        return usuarioOptional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Usuario usuario) {
        if(usuario == null) {
            return "0";
        }
        return usuario.getNomeUsuario();
    }
}
