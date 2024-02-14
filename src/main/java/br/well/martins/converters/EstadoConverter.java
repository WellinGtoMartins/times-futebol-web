package br.well.martins.converters;

import br.well.martins.models.Estado;
import br.well.martins.services.CidadeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@RequestScoped
@Named("estadoConverter")
public class EstadoConverter implements Converter<Estado> {

    @Inject
    private CidadeService service;

    @Override
    public Estado getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id == null) {
            return null;
        }
        Optional<Estado> estadoOptional = service.estadoPorId(Integer.valueOf(id));
        return estadoOptional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Estado estado) {
        if(estado == null) {
            return "0";
        }
        return estado.getId().toString();
    }
}
