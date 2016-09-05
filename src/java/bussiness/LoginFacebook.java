/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author asus
 */
public class LoginFacebook {

    private static final String client_secret = "b65b3f90b593c1263a5b91742fc19e9b";
    private static final String client_id = "429194707269141";
    //URL de volta
    private static final String redirect_uri = "http://localhost:8080/WebApplication5/loginfbresponse";

    public String obterUsuarioFacebook(String code, HttpServletRequest request) {

        String retorno = null;
        try {
            retorno = readURL(new URL(this.getAuthURL(code)));
        } catch (IOException ex) {
            Logger.getLogger(LoginFacebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        String accessToken = null;
        @SuppressWarnings("unused")
        Integer expires = null;
        String[] pairs = retorno.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            if (kv.length != 2) {
                throw new RuntimeException("Resposta auth inesperada.");
            } else {
                if (kv[0].equals("access_token")) {
                    accessToken = kv[1];
                }
                if (kv[0].equals("expires")) {
                    expires = Integer.valueOf(kv[1]);
                }
            }
        }
        
        JSONObject resp = null;
        try {
            try {
                resp = new JSONObject(readURL(new URL("https://graph.facebook.com/v2.1/me?fields=email%2Cname%2Cgender%2Clink%2Cpicture.type(large)&access_token=" + accessToken)));
            } catch (IOException ex) {
                Logger.getLogger(LoginFacebook.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JSONException ex) {
            Logger.getLogger(LoginFacebook.class.getName()).log(Level.SEVERE, null, ex);
        }

        UsuarioFacebook usuarioFacebook = null;
        try {
            usuarioFacebook = new UsuarioFacebook(resp, request);
        } catch (JSONException ex) {
            Logger.getLogger(LoginFacebook.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(usuarioFacebook.toString());
        return "redirect:/admin";
    }

    private String readURL(URL url) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = url.openStream();
        int r;
        while ((r = is.read()) != -1) {
            baos.write(r);
        }
        return new String(baos.toByteArray());
    }

    public String getLoginRedirectURL() {
        return "https://graph.facebook.com/oauth/authorize?client_id="
                + client_id + "&display=page&redirect_uri=" + redirect_uri
                + "&scope=email,public_profile";
    }

    public String getAuthURL(String authCode) {
        return "https://graph.facebook.com/oauth/access_token?client_id="
                + client_id + "&redirect_uri=" + redirect_uri
                + "&client_secret=" + client_secret + "&code=" + authCode;
    }
}
