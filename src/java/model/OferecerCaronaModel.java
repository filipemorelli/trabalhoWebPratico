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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Filipe
 */
@Entity(name = "OferecerCarona")
public class OferecerCaronaModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String considerecoes;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private EnderecoModel endereco;

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

    public String getConsiderecoes() {
        return considerecoes;
    }

    public void setConsiderecoes(String considerecoes) {
        this.considerecoes = considerecoes;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
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

    public OferecerCaronaModel load(long id) {
        HibernateUtil.getSession().beginTransaction();
        OferecerCaronaModel oferecerCaronaModel = (OferecerCaronaModel) HibernateUtil.getSession().load(OferecerCaronaModel.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return oferecerCaronaModel;
    }

    public static List<OferecerCaronaModel> loadAll(long id) {
        HibernateUtil.getSession().beginTransaction();
        List<OferecerCaronaModel> lista = HibernateUtil.getSession().createQuery("select oc from oferecercarona as oc").list();
        HibernateUtil.getSession().getTransaction().commit();
        return lista;
    }
}
