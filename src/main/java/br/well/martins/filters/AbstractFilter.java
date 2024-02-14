package br.well.martins.filters;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class AbstractFilter {


    public AbstractFilter() {

    }

    protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.xhtml");
        dispatcher.forward(request, response);
    }



}
