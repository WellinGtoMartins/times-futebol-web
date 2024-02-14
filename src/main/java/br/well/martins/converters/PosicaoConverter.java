package br.well.martins.converters;

import br.well.martins.models.Posicao;
import br.well.martins.services.TimeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@RequestScoped
@Named("posicaoConverter")
public class PosicaoConverter implements Converter<Posicao> {

    @Inject
    private TimeService service;

    @Override
    public Posicao getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id == null) {
            return null;
        }
        Optional<Posicao> posicaoOptional = service.posicaoPorId(Integer.valueOf(id));
        return posicaoOptional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Posicao posicao) {
        if(posicao == null) {
            return "0";
        }
        return posicao.getId().toString();
    }
}
