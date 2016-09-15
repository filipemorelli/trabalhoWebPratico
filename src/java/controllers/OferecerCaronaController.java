/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sun.org.apache.bcel.internal.generic.L2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import model.CaronaModel;
import model.EnderecoModel;
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
    public void addCaronaAjax(@Valid CaronaModel caronaModel, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws JSONException {

        this.setHeader(response);

        try {
            try (ServletOutputStream pw = response.getOutputStream()) {
                JSONObject json = new JSONObject();

                if (result.hasFieldErrors("consideracoes") || !this.validaEndereco(request)) {
                    json.accumulate("status", false);
                    json.accumulate("msg", "Necessário corretamente os dados");
                } else {
                    EnderecoModel enderecoSaida = this.getIdEnderecoSaida(request);
                    EnderecoModel enderecoChegada = this.getIdEnderecoChegada(request);
                    String consideracoes = request.getParameter("consideracoes");

                    caronaModel.setConsideracoes(consideracoes);
                    caronaModel.setTipo_carona("oferecer");
                    caronaModel.setEndereco_chegada(enderecoChegada);
                    caronaModel.setEndereco_saida(enderecoSaida);
                    caronaModel.setUser(UserModel.loadBySocialId((String) request.getSession().getAttribute("id_social")));
                    caronaModel.save();

                    json.accumulate("status", true);
                    json.accumulate("msg", "Oferta de carona inserida com sucesso");
                }
                pw.print(json.toString());
                pw.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(OferecerCaronaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping("excluir-oferta-carona")
    public void excluirOfertaCarona(HttpServletRequest request, HttpServletResponse response) {

        this.setHeader(response);

        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        try {
            try (ServletOutputStream pw = response.getOutputStream()) {
                CaronaModel c = new CaronaModel();
                c = c.load(Long.parseLong(id));
                c.delete();
                json.accumulate("status", true);
                json.accumulate("msg", "Carona excluida com sucesso!");
                pw.print(json.toString());
                pw.flush();
            }
        } catch (Exception e) {
            try {
                json.accumulate("status", false);
                json.accumulate("msg", "Ocorreu um erro ao excluir oferta de carona");
            } catch (JSONException ex) {
                Logger.getLogger(OferecerCaronaController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private EnderecoModel setEndereco(String endereco, String endereco_completo, String numero, String bairro, String cidade, String estado, String latitude, String longitude, String cep) {
        EnderecoModel e = new EnderecoModel();
        e.setEndereco(endereco);
        e.setEndereco_completo(endereco_completo);
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
        String enderecoSaidaCompleto = request.getParameter("endereco_saida_completo");
        String numeroSaida = request.getParameter("endereco_saida_street_number");
        String bairroSaida = request.getParameter("endereco_saida_neighborhood");
        String cidadeSaida = request.getParameter("endereco_saida_locality");
        String estadoSaida = request.getParameter("endereco_saida_administrative_area_level_1");
        String latitudeSaida = request.getParameter("endereco_saida_lat");
        String longitudeSaida = request.getParameter("endereco_saida_lng");
        String cepSaida = request.getParameter("endereco_saida_postal_code");

        return this.setEndereco(enderecoSaida, enderecoSaidaCompleto,numeroSaida, bairroSaida, cidadeSaida, estadoSaida, latitudeSaida, longitudeSaida, cepSaida);
    }

    private EnderecoModel getIdEnderecoChegada(HttpServletRequest request) {
        String enderecoChegada = request.getParameter("endereco_chegada_route");
        String enderecoChegadaCompleto = request.getParameter("endereco_chegada_completo");
        String numeroChegada = request.getParameter("endereco_chegada_street_number");
        String bairroChegada = request.getParameter("endereco_chegada_neighborhood");
        String cidadeChegada = request.getParameter("endereco_chegada_locality");
        String estadoChegada = request.getParameter("endereco_chegada_administrative_area_level_1");
        String latitudeChegada = request.getParameter("endereco_chegada_lat");
        String longitudeChegada = request.getParameter("endereco_chegada_lng");
        String cepChegada = request.getParameter("endereco_chegada_postal_code");

        return this.setEndereco(enderecoChegada, enderecoChegadaCompleto,numeroChegada, bairroChegada, cidadeChegada, estadoChegada, latitudeChegada, longitudeChegada, cepChegada);
    }

    private boolean validaEndereco(HttpServletRequest request) {

        if (request.getParameter("endereco_saida_route") == null || request.getParameter("endereco_chegada_route") == null) {
            return false;
        } else if (request.getParameter("endereco_saida_locality") == null || request.getParameter("endereco_chegada_locality") == null) {
            return false;
        } else if (request.getParameter("endereco_saida_country") == null || request.getParameter("endereco_chegada_country") == null) {
            return false;
        } else if (request.getParameter("endereco_saida_administrative_area_level_1") == null || request.getParameter("endereco_chegada_administrative_area_level_1") == null) {
            return false;
        }
        return true;
    }
}
