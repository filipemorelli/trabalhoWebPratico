/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import javax.servlet.http.HttpServletRequest;
import model.HibernateUtil;
import model.UserModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author asus
 */
@Scope("session")
public class UsuarioFacebook {

    private Long id;
    private String firstName;
    private Integer timezone;
    private String email;
    private Boolean verified;
    private String middleName;
    private String gender;
    private String lastName;
    private String link;
    private String locale;
    private String name;
    private String updatedTime;

    public UsuarioFacebook(JSONObject jsonUsuario, HttpServletRequest request) throws JSONException {
        UserModel user;
        if (UserModel.existUser(jsonUsuario.getString("id"))) {
            //update
            user = UserModel.loadBySocialId(jsonUsuario.getString("id"));
        } else {
            user = new UserModel();
            user.setId_social(jsonUsuario.getString("id"));
        }
        user.setNome(jsonUsuario.getString("name"));
        user.setSexo(jsonUsuario.getString("gender"));
        user.setEmail(jsonUsuario.getString("email"));
        user.setUrl_imagem(jsonUsuario.getJSONObject("picture").getJSONObject("data").getString("url"));
        user.setUrl_social(jsonUsuario.getString("link"));
        user.setTelefone("");
        user.saveOrUpdate();

        //adiciona a sessao
        request.getSession().setAttribute("id_social", jsonUsuario.getString("id"));
        request.getSession().setAttribute("url_social", jsonUsuario.getString("link"));
        request.getSession().setAttribute("name", jsonUsuario.getString("name"));
        request.getSession().setAttribute("gender", jsonUsuario.getString("gender"));
        request.getSession().setAttribute("email", jsonUsuario.getString("email"));
        request.getSession().setAttribute("picture", jsonUsuario.getJSONObject("picture").getJSONObject("data").getString("url"));
        request.getSession().setAttribute("phone", "");
        //informacoes do servidor
        /**
         * {
         * "gender": "male", "name": "Filipe Morelli", "link":
         * "https://www.facebook.com/app_scoped_user_id/940207802754807/", "id":
         * "940207802754807", "email": "morellinrt@outlook.com", "picture": {
         * "data": { "is_silhouette": false, "url":
         * "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xtp1/v/t1.0-1/p200x200/11873749_747502535358669_62512099376069994_n.jpg?oh=9b552f76f946d7567c76cb3cd58f697d&oe=584CD87C&__gda__=1484752827_2ee7ff2ba8374635d1ed9d4e7e9285a7"
         * } } }
         */
    }

    @Override
    public String toString() {
        return "UsuarioFacebook [id=" + id + ", firstName=" + firstName
                + ", timezone=" + timezone + ", email=" + email + ", verified="
                + verified + ", middleName=" + middleName + ", gender="
                + gender + ", lastName=" + lastName + ", link=" + link
                + ", locale=" + locale + ", name=" + name + ", updatedTime="
                + updatedTime + "]";
    }

}
