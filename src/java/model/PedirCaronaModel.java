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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Filipe
 */
@Entity(name = "pedircarona")
public class PedirCaronaModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String consideracoes;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "PedirCarona_User"))
    private UserModel user;

    //saida da carona
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "PedirCarona_EnderecoSaida"))
    private EnderecoModel endereco_saida;
    
    //chegada da carona
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "PedirCarona_EnderecoChegada"))
    private EnderecoModel endereco_chegada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar modified;

    public Long getId() {
        return id;
    }

    public String getConsideracoes() {
        return consideracoes;
    }

    public void setConsideracoes(String consideracoes) {
        this.consideracoes = consideracoes;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public EnderecoModel getEndereco_saida() {
        return endereco_saida;
    }

    public void setEndereco_saida(EnderecoModel endereco_saida) {
        this.endereco_saida = endereco_saida;
    }

    public EnderecoModel getEndereco_chegada() {
        return endereco_chegada;
    }

    public void setEndereco_chegada(EnderecoModel endereco_chegada) {
        this.endereco_chegada = endereco_chegada;
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
