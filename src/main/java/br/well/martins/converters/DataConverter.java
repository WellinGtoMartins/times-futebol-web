package br.well.martins.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesConverter(value = "dataConverter")
public class DataConverter implements Serializable, Converter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.trim().isEmpty()) {
            return null;
        }

        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Erro ao converter data.", e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return "";
        }

        if (o instanceof Date) {
            return sdf.format((Date) o);
        } else {
            throw new IllegalArgumentException("Objeto não é do tipo Date: " + o.getClass().getName());
        }
    }
}
