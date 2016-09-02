/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author asus
 */
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

    public UsuarioFacebook(JSONObject jsonUsuario) throws JSONException {

        id = jsonUsuario.getLong("id");
        firstName = jsonUsuario.getString("first_name");
        timezone = jsonUsuario.getInt("timezone");
        email = jsonUsuario.getString("email");
        verified = jsonUsuario.getBoolean("verified");
        middleName = jsonUsuario.getString("middle_name");
        gender = jsonUsuario.getString("gender");
        lastName = jsonUsuario.getString("last_name");
        link = jsonUsuario.getString("link");
        locale = jsonUsuario.getString("locale");
        name = jsonUsuario.getString("name");
        updatedTime = jsonUsuario.getString("updated_time");
        
        //registrar no banco caso não exista
        //sse possivel aqui armazenar em sessao os dados do usuario

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
