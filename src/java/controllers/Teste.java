/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author asus
 */
import model.EnderecoModel;
import model.HibernateUtil;
import model.OferecerCaronaModel;
import model.PedirCaronaModel;
import model.UserModel;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class Teste {

    /**
     * Metodo criado com o unico proposito de gerar o banco. Assim que for
     * gerado ele deve ser comentado ou excluido
     *
     * @return
     */
    @RequestMapping("/db")
    public String createDB() {
        //Colocando configuracoes
        HibernateUtil.createDB();
        return "template";
    }
}
