/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.entities;

import com.mycompany.methotels.data.Rola;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author NenadS
 */
@Entity
@Table(name = "korisnik")
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")})
public class Korisnik extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Validate("required")
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @Validate("required")
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @Validate("required")
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Validate("required")
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "rola")
    private Rola rola;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korisnikId")
    private List<Rezervacija> rezervacijaList;

    @Column(name = "FACEBOOK_ID")
    private String facebookId;

    public String getFacebookId() {
        return facebookId;
    }

    @NonVisual
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    @Inject
    public Korisnik() {
    }

    public Korisnik(Integer id) {
        this.id = id;
    }

    public Korisnik(String username, String password, String ime, String prezime, Rola rola, String facebookId) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.rola = rola;
        this.facebookId = facebookId;
    }

    public Korisnik(Integer id, String ime, String prezime, String username, String password, Rola rola, String facebookId) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.rola = rola;
        this.facebookId = facebookId;
    }

    public Integer getId() {
        return id;
    }

    @NonVisual
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.methotels.entities.Korisnik[ id=" + id + " ]";
    }

    @NonVisual
    public Rola getRola() {
        return rola;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
    }

    public List<Rezervacija> getRezervacijaList() {
        return rezervacijaList;
    }

    public void setRezervacijaList(List<Rezervacija> rezervacijaList) {
        this.rezervacijaList = rezervacijaList;
    }

}
