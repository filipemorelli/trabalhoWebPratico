/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import model.EnderecoModel;
import model.OferecerCaronaModel;
import model.PedirCaronaModel;
import model.UserModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Filipe
 */
@Controller
public class OferecerCaronaController {

    @RequestMapping("oferecer-carona")
    public String pedirCarona() {
        return "oferecerCarona/oferecerCarona";
    }

    @RequestMapping("add-oferta-carona-ajax")
    public void addCaronaAjax(@Valid OferecerCaronaModel oferecerCaronaModel, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws JSONException {

        this.setHeader(response);

        try {
            try (ServletOutputStream pw = response.getOutputStream()) {
                JSONObject json = new JSONObject();

                if (result.hasFieldErrors("consideracoes")) {
                    json.accumulate("status", false);
                    json.accumulate("msg", "Necessário digitar as considerações");
                } else {
                    EnderecoModel enderecoSaida = this.getIdEnderecoSaida(request);
                    EnderecoModel enderecoChegada = this.getIdEnderecoChegada(request);
                    String consideracoes = request.getParameter("consideracoes");

                    oferecerCaronaModel.setConsideracoes(consideracoes);
                    oferecerCaronaModel.setEndereco_chegada(enderecoChegada);
                    oferecerCaronaModel.setEndereco_saida(enderecoSaida);
                    oferecerCaronaModel.setUser(UserModel.loadBySocialId((String) request.getSession().getAttribute("id_social")));
                    oferecerCaronaModel.save();

                    json.accumulate("status", true);
                    json.accumulate("msg", "Oferta de carona inserida com sucesso");
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
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
    }

    private EnderecoModel setEndereco(String endereco, String numero, String bairro, String cidade, String estado, String latitude, String longitude, String cep) {
        EnderecoModel e = new EnderecoModel();
        e.setEndereco(endereco);
        e.setNumero(numero);
        e.setBairro(bairro);
        e.setCidade(cidade);
        e.setEstado(estado);
        e.setLatitude(latitude);
        e.setLongitude(longitude);
        e.setCep(cep);
        e.save();
        return e;
    }

    private EnderecoModel getIdEnderecoSaida(HttpServletRequest request) {
        String enderecoSaida = request.getParameter("endereco_saida_route");
        String numeroSaida = request.getParameter("endereco_saida_street_number");
        String bairroSaida = request.getParameter("endereco_saida_neighborhood");
        String cidadeSaida = request.getParameter("endereco_saida_locality");
        String estadoSaida = request.getParameter("endereco_saida_administrative_area_level_1");
        String latitudeSaida = request.getParameter("endereco_saida_lat");
        String longitudeSaida = request.getParameter("endereco_saida_lng");
        String cepSaida = request.getParameter("endereco_saida_postal_code");

        return this.setEndereco(enderecoSaida, numeroSaida, bairroSaida, cidadeSaida, estadoSaida, latitudeSaida, longitudeSaida, cepSaida);
    }

    private EnderecoModel getIdEnderecoChegada(HttpServletRequest request) {
        String enderecoSaida = request.getParameter("endereco_chegada_route");
        String numeroSaida = request.getParameter("endereco_chegada_street_number");
        String bairroSaida = request.getParameter("endereco_chegada_neighborhood");
        String cidadeSaida = request.getParameter("endereco_chegada_locality");
        String estadoSaida = request.getParameter("endereco_chegada_administrative_area_level_1");
        String latitudeSaida = request.getParameter("endereco_chegada_lat");
        String longitudeSaida = request.getParameter("endereco_chegada_lng");
        String cepSaida = request.getParameter("endereco_chegada_postal_code");

        return this.setEndereco(enderecoSaida, numeroSaida, bairroSaida, cidadeSaida, estadoSaida, latitudeSaida, longitudeSaida, cepSaida);
    }
}
