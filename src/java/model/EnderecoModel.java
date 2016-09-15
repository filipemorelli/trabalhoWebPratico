/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
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
@Entity(name = "endereco")
public class EnderecoModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String endereco;
    private String endereco_completo;
    private String numero; //numero, A, B, Apartamento etc.
    @Column(length = 9)
    private String cep;
    private String bairro;
    private String cidade;
    @Column(length = 2)
    private String estado;
    private String latitude;
    private String longitude;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getEndereco_completo() {
        return endereco_completo;
    }

    public void setEndereco_completo(String endereco_completo) {
        this.endereco_completo = endereco_completo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void save() {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(this);
        HibernateUtil.getSession().getTransaction().commit();
    }

    public void update() {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(this);
        HibernateUtil.getSession().getTransaction().commit();
    }

    public void delete() {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(this);
        HibernateUtil.getSession().getTransaction().commit();
    }

    public EnderecoModel load(long id) {
        HibernateUtil.getSession().beginTransaction();
        EnderecoModel enderecoModel = (EnderecoModel) HibernateUtil.getSession().load(EnderecoModel.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return enderecoModel;
    }

    public static List<EnderecoModel> loadAll(long id) {
        HibernateUtil.getSession().beginTransaction();
        List<EnderecoModel> lista = HibernateUtil.getSession().createQuery("select e from endereco as e").list();
        HibernateUtil.getSession().getTransaction().commit();
        return lista;
    }
}
