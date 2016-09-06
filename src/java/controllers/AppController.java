/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 *
 * @author Filipe
 */
@Controller
public class AppController extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
        String uri = request.getRequestURI();
        if (uri.endsWith("login") || uri.endsWith("termos-de-uso") || uri.endsWith("politicas-de-privacidade") || uri.endsWith("loginfb") || uri.endsWith("loginfbresponse")) {
            return true;
        }
        
        //Liberar a criacao do banco de dados
        
        if(uri.endsWith("db"))
            return true;
        
        //Habilitar o tema
        if (uri.contains("/themes")) {
            return true;
        }

        //habilitar paginas bloqueadas
        if (request.getSession().getAttribute("id_social") != null) {
            return true;
        }
        response.sendRedirect("login");
        return false;
    }

}
