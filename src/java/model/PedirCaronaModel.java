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
@Entity(name = "PedirCarona")
public class PedirCaronaModel implements Serializable {

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

    public PedirCaronaModel load(long id) {
        HibernateUtil.getSession().beginTransaction();
        PedirCaronaModel pedirCaronaModel = (PedirCaronaModel) HibernateUtil.getSession().load(PedirCaronaModel.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return pedirCaronaModel;
    }

    public static List<PedirCaronaModel> loadAll(long id) {
        HibernateUtil.getSession().beginTransaction();
        List<PedirCaronaModel> lista = HibernateUtil.getSession().createQuery("select pc from pedircarona as pc").list();
        HibernateUtil.getSession().getTransaction().commit();
        return lista;
    }
}
