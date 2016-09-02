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
        System.out.println("------------------------------------------------"
                + "CHEGAMOS AQUI"
                + "-------------------------------------------------------");
        System.err.println(jsonUsuario.toString());
        //informacoes do servidor
        /**
            {
             "gender": "male",
             "name": "Filipe Morelli",
             "link": "https://www.facebook.com/app_scoped_user_id/940207802754807/",
             "id": "940207802754807",
             "email": "morellinrt@outlook.com",
             "picture": {
              "data": {
               "is_silhouette": false,
               "url": "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xtp1/v/t1.0-1/p200x200/11873749_747502535358669_62512099376069994_n.jpg?oh=9b552f76f946d7567c76cb3cd58f697d&oe=584CD87C&__gda__=1484752827_2ee7ff2ba8374635d1ed9d4e7e9285a7"
              }
             }
            }
         */
        /*
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
*/
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
