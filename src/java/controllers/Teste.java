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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@Controller
public class Teste {
    /*
    @RequestMapping("/")
    public String execute() {
        System.out.println("on method");
        return "redirect:/login";
    }
    @RequestMapping("/template")
    public String template() {
        System.out.println("on method Template");
        return "redirect:/login";
    }
    @RequestMapping("/login")
    public String login() {
        System.out.println("on method Login");
        return "user/login";
    }
    @RequestMapping("/admin")
    public String admin() {
        System.out.println("on method Admin");
        return "admin";
    }
    
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order") // 404
    public String error(){
        System.out.println("");
        return "404";
    }
    */
}