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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class Teste {
 
    @RequestMapping("/")
    public String execute() {
        System.out.println("on method");
        return "index";
    }
    @RequestMapping("/template")
    public String template() {
        System.out.println("on method Template");
        return "template";
    }
}