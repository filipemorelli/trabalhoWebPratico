/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Filipe
 */
@Entity(name = "user")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;

    @Column(length = 10)
    private String sexo;
    private String telefone;

    @Column(columnDefinition = "TEXT")
    private String url_imagem;
    private String email;
    private String id_social;

    @Column(columnDefinition = "TEXT")
    private String url_social;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP")
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Calendar modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_social() {
        return id_social;
    }

    public void setId_social(String id_social) {
        this.id_social = id_social;
    }

    public String getUrl_social() {
        return url_social;
    }

    public void setUrl_social(String url_social) {
        this.url_social = url_social;
    }

}
