/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistences;

import com.mycompany.methotels.data.Rola;
import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.entities.Rezervacija;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author NenadS
 */
public class RezervacijaDaoImpl implements RezervacijaDao {

    @Inject
    private Session session;

    @Override
    public List<Rezervacija> getListaRezervacija(Korisnik korisnik) {
        List<Rezervacija> listaSvihRezervacija;
        
        if (korisnik.getRola().equals(Rola.Admin)) {
            listaSvihRezervacija = session.createCriteria(Rezervacija.class).list();
        } else {
            listaSvihRezervacija = session.createCriteria(Rezervacija.class).add(Restrictions.eq("korisnikId", korisnik)).list();
        }
        
        if (listaSvihRezervacija == null) {
            return new ArrayList<Rezervacija>();
        }

        return listaSvihRezervacija;
    }

    @Override
    public Rezervacija getRezervacijaById(Integer id) {
        return (Rezervacija) session.createCriteria(Rezervacija.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajRezervaciju(Rezervacija rezervacija) {
        session.persist(rezervacija);
    }

    @Override
    public void obrisiRezervaciju(Integer id) {
        Rezervacija rezervacija = (Rezervacija) session.createCriteria(Rezervacija.class).add(Restrictions.eq("id", id)).uniqueResult();
        session.delete(rezervacija);
    }

}
