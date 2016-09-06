/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import model.PedirCaronaModel;
import model.UserModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Filipe
 */
@Controller
public class PedirCaronaController {

    @RequestMapping("pedir-carona")
    public String pedirCarona() {
        return "pedirCarona/pedirCarona";
    }

    @RequestMapping("add-carona")
    public void addCarona(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        this.setHeader(response);

        try {
            try (ServletOutputStream pw = response.getOutputStream()) {
                JSONObject json = new JSONObject();
                json.accumulate("msg", "ok");
                json.accumulate("id", request.getParameter("id"));
                pw.print(json.toString());
                pw.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(PedirCaronaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping("add-carona-ajax")
    @ModelAttribute(value="myData")
    public void addCaronaAjax(@Valid PedirCaronaModel pedirCaronaModel, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws JSONException {

        this.setHeader(response);

        try {
            try (ServletOutputStream pw = response.getOutputStream()) {
                JSONObject json = new JSONObject();

                if (result.hasFieldErrors("consideracoes")) {
                    json.accumulate("status", false);
                    json.accumulate("msg", result.toString());
                } else {
                    json.accumulate("status", true);
                    //json.accumulate("msg", result.getFieldValue("endereco_saida"));
                }

                pw.print(json.toString());
                pw.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(PedirCaronaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setHeader(HttpServletResponse response) {
        response.setContentType("text/json");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
    }

}
