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
import org.hibernate.Query;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Filipe
 */
@Entity(name = "carona")
public class CaronaModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String consideracoes;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "OferecerCarona_User"))
    private UserModel user;

    //saida da carona
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "OferecerCarona_EnderecoSaida"))
    private EnderecoModel endereco_saida;

    //chegada da carona
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "OferecerCarona_EnderecoChegada"))
    private EnderecoModel endereco_chegada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar modified;

    private String tipo;

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

    public String setTipo_carona(String tipo) {
        return this.tipo = tipo;
    }

    public String getTipo_carona() {
        return this.tipo;
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

    public CaronaModel load(long id) {
        HibernateUtil.getSession().beginTransaction();
        CaronaModel carona = (CaronaModel) HibernateUtil.getSession().load(CaronaModel.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return carona;
    }

    public static List<CaronaModel> loadAll() {
        HibernateUtil.getSession().beginTransaction();
        List<CaronaModel> lista = HibernateUtil.getSession().createQuery("select c from carona as c").list();
        HibernateUtil.getSession().getTransaction().commit();
        return lista;
    }

    public static List<CaronaModel> loadSearch(String bairro, String cidade, String estado, String tipo, Long idUser) {
        HibernateUtil.getSession().beginTransaction();
        Query query = HibernateUtil.getSession().createQuery("SELECT c FROM carona as c WHERE c.tipo = :tipo AND (c.endereco_chegada.bairro = :bairro AND c.endereco_chegada.cidade = :cidade AND c.endereco_chegada.estado = :estado) AND c.user.id != :idUser");
        query.setParameter("tipo", tipo);
        query.setParameter("bairro", bairro);
        query.setParameter("cidade", cidade);
        query.setParameter("estado", estado);
        query.setParameter("idUser", idUser);
        HibernateUtil.getSession().getTransaction().commit();
        return query.list();
    }
}
