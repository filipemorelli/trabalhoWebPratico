/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussiness.LoginFacebook;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.CaronaModel;
import model.EnderecoModel;
import model.UserModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author asus
 */
@Controller
public class UserController extends AppController {

    private final LoginFacebook loginFacebook = new LoginFacebook();

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
     * @param request
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    @RequestMapping("/loginfbresponse")
    public String respostaFacebook(String code, HttpServletRequest request) throws MalformedURLException, IOException {
        System.out.println("on method respostaFacebook");
        return loginFacebook.obterUsuarioFacebook(code, request);
    }

    /**
     * Entrou no na raiz do site irá ser rediercionado para pagina de login
     *
     * @return
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
     *
     * @param session
     * @return
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

    @RequestMapping("/pesquisar-carona")
    public String pesquisarCarona() {
        System.out.println("on method Pesquisar Carona");
        return "user/pesquisa-carona";
    }

    @RequestMapping("/pesquisar-carona-ajax")
    public void pesquisarCaronaAjax(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        this.setHeader(response);
        JSONObject json = new JSONObject();

        try {
            ServletOutputStream out = response.getOutputStream();
            if (!this.validaEndereco(request)) {
                json.accumulate("status", false);
                json.accumulate("msg", "Digite um endereco valido");
                out.print(json.toString());
                out.close();
            } else {
                String bairro = request.getParameter("bairro");
                String cidade = request.getParameter("cidade");
                String estado = request.getParameter("estado");
                String tipo = request.getParameter("tipo");
                Long idUser = UserModel.loadBySocialId((String) request.getSession().getAttribute("id_social")).getId();
                List<CaronaModel> lista = this.buscaEnderecosCaronas(bairro, cidade, estado, tipo, idUser);
                JSONArray a = new JSONArray(lista);
                json.accumulate("status", true);
                json.accumulate("msg", a.toString());
                out.print(json.toString());
                out.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<CaronaModel> buscaEnderecosCaronas(String bairro, String cidade, String estado, String tipo, Long idUser) {
        return CaronaModel.loadSearch(bairro, cidade, estado, tipo, idUser);
    }

    private void setHeader(HttpServletResponse response) {
        response.setContentType("text/json");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
    }

    private boolean validaEndereco(HttpServletRequest request) {
        //caso tenha que acrescentar endereco ida e chegada
        if (request.getParameter("bairro") == null || request.getParameter("bairro") == null) {
            return false;
        } else if (request.getParameter("cidade") == null || request.getParameter("cidade") == null) {
            return false;
        } else if (request.getParameter("estado") == null || request.getParameter("estado") == null) {
            return false;
        }
        return true;
    }

    /**
     * Termos de uso do sistema ou site por parte do usuário
     *
     * @return
     */
    @RequestMapping("/perfil")
    public String perfil() {
        System.out.println("on method Perfil");
        return "user/perfil";
    }

    @RequestMapping("/atualiza-perfil")
    public String atualizaPerfil(@Valid UserModel userValidation, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        System.out.println("on method Perfil");

        if (result.hasErrors()) {
            System.out.println("Erro: " + result.getFieldError().getField());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            return "redirect:/perfil";
        } else {
            String id_social = (String) request.getSession().getAttribute("id_social");
            UserModel user = UserModel.loadBySocialId(id_social);
            user.setEmail(userValidation.getEmail());
            user.setNome(userValidation.getNome());
            user.setTelefone(userValidation.getTelefone());
            user.setSexo(userValidation.getSexo());
            user.update();

            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("name", user.getNome());
            request.getSession().setAttribute("gender", user.getSexo());
            request.getSession().setAttribute("phone", user.getTelefone());
        }
        return "redirect:/perfil";
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
