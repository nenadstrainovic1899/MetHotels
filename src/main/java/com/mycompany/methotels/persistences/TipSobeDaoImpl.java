/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistences;

import com.mycompany.methotels.entities.TipSobe;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author NenadS
 */
public class TipSobeDaoImpl implements TipSobeDao {
    
    @Inject
    private Session session;

    @Override
    public List<TipSobe> getListaSvihTipovaSoba() {
        List<TipSobe> listaSvihTipovaSoba = session.createCriteria(TipSobe.class).list();
        if (listaSvihTipovaSoba == null) {
            return new ArrayList<TipSobe>();
        }
        
        return listaSvihTipovaSoba;
    }

    @Override
    public TipSobe getTipoSobeById(Integer id) {
        return (TipSobe) session.createCriteria(TipSobe.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajTipSobe(TipSobe tipSobe) {
        session.persist(tipSobe);
    }

    @Override
    public void obrisiTipSobe(Integer id) {
        TipSobe tipSobe = (TipSobe) session.createCriteria(TipSobe.class).add(Restrictions.eq("id", id)).uniqueResult();
        session.delete(tipSobe);
    }
    
}
