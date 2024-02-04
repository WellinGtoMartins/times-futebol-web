package br.well.martins.configs;

import br.well.martins.models.Pessoa;
import br.well.martins.services.TimeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@RequestScoped
@Named("pessoaConverter")
public class PessoaConverter implements Converter<Pessoa> {

    @Inject
    private TimeService service;

    @Override
    public Pessoa getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id == null) {
            return null;
        }
        Optional<Pessoa> pessoaOptional = service.pessoaPorId(Integer.valueOf(id));
        return pessoaOptional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Pessoa pessoa) {
        if(pessoa == null) {
            return "0";
        }
        return pessoa.getId().toString();
    }
}
