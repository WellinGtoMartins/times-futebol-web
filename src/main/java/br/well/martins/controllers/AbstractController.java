package br.well.martins.controllers;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import org.primefaces.context.PrimeRequestContext;

public class AbstractController {

    public AbstractController() {
    }

    protected PrimeRequestContext getRequestContext(){
        return PrimeRequestContext.getCurrentInstance();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

}
