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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

    @Override
    public void dodajIliUpdatujTipSobe(TipSobe tipSobe) {
        session.merge(tipSobe);
    }

    @Override
    public List<TipSobe> getListaTipovaSobaPoImenu(String tipSobe) {
        return session.createCriteria(TipSobe.class).add(Restrictions.ilike("tipSobe", tipSobe + "%")).list();
    }

    @Override
    public List<TipSobe> loadActiveFromTo(int from) {
        int page = (from - 1) * 20;
        List<TipSobe> lista
                = session.createCriteria(TipSobe.class).setFirstResult(page).setMaxResults(20).addOrder(Order.asc("id"))
                        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return lista;
    }

    @Override
    public int allActiveSizeTipoviSoba() {
        Long l = (Long) session.createCriteria(TipSobe.class).setProjection(Projections.rowCount()).uniqueResult();
        return l.intValue();
    }

}
