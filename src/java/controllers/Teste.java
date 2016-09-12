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
import model.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
