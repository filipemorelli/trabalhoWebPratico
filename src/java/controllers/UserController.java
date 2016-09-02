/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussiness.LoginFacebook;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author asus
 */
@Controller
public class UserController extends AppController {

    private LoginFacebook loginFacebook = new LoginFacebook();

    /**
     * Método que chama URL do facebook onde o usuário poderá autorizar a
     * aplicação a acessar seus recursos privados.
     *
     * @return
     */
    @RequestMapping("/loginfb")
    public String logarComFacebook() {
        System.out.println("on method Logar");
        return "redirect:" + loginFacebook.getLoginRedirectURL();
    }

    /**
     * Executado quando o Servidor de Autorização fizer o redirect.
     *
     * @param code
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    @RequestMapping("/loginfbresponse")
    public String respostaFacebook(String code, HttpServletRequest request) throws MalformedURLException, IOException {
        System.out.println("on method respostaFacebook");
        loginFacebook.obterUsuarioFacebook(code, request);

        return "redirect:/admin";
    }

    /**
     * Entrou no na raiz do site irá ser rediercionado para pagina de login
     */
    @RequestMapping("/")
    public String index() {
        System.out.println("on method");
        return "redirect:/login";
    }

    /**
     * A se fazer de forma mais adequada
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        System.out.println("on method Login");
        return "user/login";
    }

    /**
     * Entrou no na raiz do site irá ser rediercionado para pagina de login
     * @param request
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("on method Logout");
        session.invalidate(); // excluir a 
        return "redirect:/login";
    }

    /**
     * Painel do usuário na utilização do sistema
     *
     * @return
     */
    @RequestMapping("/admin")
    public String admin() {
        System.out.println("on method Admin");
        return "user/admin";
    }

    /**
     * Termos de uso do sistema ou site por parte do usuário
     *
     * @return
     */
    @RequestMapping("/perfil")
    public String perfil() {
        System.out.println("on method Perfil");
        return "user/admin";
    }

    /**
     * Termos de uso do sistema ou site por parte do usuário
     *
     * @return
     */
    @RequestMapping("/termos-de-uso")
    public String termosDeUso() {
        System.out.println("on method Termos");
        return "user/termosDeUso";
    }

    /**
     * Politicas de privacidade do sistema ou site por parte do usuário
     *
     * @return
     */
    @RequestMapping("/politicas-de-privacidade")
    public String politicasDePrivacidade() {
        System.out.println("on method Politicas");
        return "user/politicaDePrivacidade";
    }

    /**
     * Politicas de privacidade do sistema ou site por parte do usuário
     *
     * @return
     */
    @RequestMapping("/sobre")
    public String sobre() {
        System.out.println("on method Sobre");
        return "user/admin";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order") // 404
    public String error() {
        System.out.println("Erro 404 acho que é improvavel ser aqui! mas...");
        return "404";
    }
}
