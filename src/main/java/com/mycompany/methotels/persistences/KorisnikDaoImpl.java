/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistences;

import com.mycompany.methotels.entities.Korisnik;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author NenadS
 */
public class KorisnikDaoImpl implements KorisnikDao {

    @Inject
    private Session session;

    @Override
    public List<Korisnik> getListaSvihKorisnika() {
        List<Korisnik> listaSvihKorisnika = session.createCriteria(Korisnik.class).list();
        if (listaSvihKorisnika == null) {
            return new ArrayList<Korisnik>();
        }

        return listaSvihKorisnika;
    }

    @Override
    public Korisnik getKorisnikById(Integer id) {
        return (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public Korisnik getKorisnikByUsername(String username) {
        return (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("username", username)).uniqueResult();
    }

    @Override
    public void dodajKorisnika(Korisnik korisnik) {
        session.persist(korisnik);
    }

    @Override
    public void obrisiKorisnika(Integer id) {
        Korisnik korisnik = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("id", id)).uniqueResult();
        session.delete(korisnik);
    }

    @Override
    public Korisnik proveriKorisnika(String username, String password) {
        try {
            Korisnik u = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("username",
                    username)).add(Restrictions.eq("password", password)).uniqueResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Korisnik registrujKorisnika(Korisnik korisnik) {
        return (Korisnik) session.merge(korisnik);
    }

    @Override
    public boolean korisnikPostoji(String username) {
        Long rows = (Long) session.createCriteria(Korisnik.class).add(Restrictions.eq("username",
                username)).setProjection(Projections.rowCount()).uniqueResult();
        return (rows != 0);
    }

}
