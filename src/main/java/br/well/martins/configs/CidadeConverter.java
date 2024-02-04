package br.well.martins.configs;

import br.well.martins.models.Cidade;
import br.well.martins.services.TimeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@RequestScoped
@Named("cidadeConverter")
public class CidadeConverter implements Converter<Cidade> {

    @Inject
    private TimeService service;

    @Override
    public Cidade getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id == null) {
            return null;
        }
        Optional<Cidade> cidadeOptional = service.cidadePorId(Integer.valueOf(id));
        return cidadeOptional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Cidade cidade) {
        if(cidade == null) {
            return "0";
        }
        return cidade.getId().toString();
    }
}
