/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tip_sobe")
@NamedQueries({
    @NamedQuery(name = "TipSobe.findAll", query = "SELECT t FROM TipSobe t")})
public class TipSobe extends AbstractEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Validate("required, minLength=5, maxLength=60")
    @Column(name = "tip_sobe")
    private String tipSobe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipSobeId")
    private List<Soba> sobaList;

    @Inject
    public TipSobe() {
    }

    public TipSobe(Integer id) {
        this.id = id;
    }

    public TipSobe(Integer id, String tipSobe) {
        this.id = id;
        this.tipSobe = tipSobe;
    }

    public Integer getId() {
        return id;
    }

    @NonVisual
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(String tipSobe) {
        this.tipSobe = tipSobe;
    }

    public List<Soba> getSobaList() {
        return sobaList;
    }

    public void setSobaList(List<Soba> sobaList) {
        this.sobaList = sobaList;
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
        if (!(object instanceof TipSobe)) {
            return false;
        }
        TipSobe other = (TipSobe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getTipSobe();
//        return "com.mycompany.methotels.entities.TipSobe[ id=" + id + " ]";
    }
    
}
