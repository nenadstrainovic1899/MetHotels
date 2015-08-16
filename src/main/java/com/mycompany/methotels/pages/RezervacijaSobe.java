/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.entities.Rezervacija;
import com.mycompany.methotels.entities.Soba;
import com.mycompany.methotels.persistences.RezervacijaDao;
import com.mycompany.methotels.persistences.SobaDao;
import com.mycompany.methotels.services.ProtectedPage;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author NenadS
 */
@ProtectedPage
@RolesAllowed(value={"Admin", "Korisnik"})
public class RezervacijaSobe {

    @Property
    private Rezervacija rezervacija;

    @Property
    private Rezervacija jednaRezervacija;

    @Property
    private List<Rezervacija> listaRezervacija;

    @Property
    private List<Soba> listaSoba;

    @Property
    private Soba sobaId;

    @SessionState
    private Korisnik ulogovanKorisnik;

    @Inject
    private RezervacijaDao rezervacijaDao;
    
    @Inject
    private SobaDao sobaDao;

    public String getSoba() {
        if (jednaRezervacija.getSobaId() != null) {
            return jednaRezervacija.getSobaId().getIme();
        }

        return "";
    }

    void onActivate() {
        rezervacija = new Rezervacija();

        listaRezervacija = rezervacijaDao.getListaRezervacija();
        listaSoba = sobaDao.getListaSvihSoba();
    }

    public ValueEncoder getEncoder() {
        return new ValueEncoder<Soba>() {

            @Override
            public String toClient(Soba v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Soba toValue(String string) {
                Soba soba = sobaDao.getSobaById(Integer.parseInt(string));
                return soba;
            }
        };
    }

    @CommitAfter
    Object onSuccess() {
        rezervacija.setSobaId(sobaId);
        rezervacija.setKorisnikId(ulogovanKorisnik);
        rezervacijaDao.dodajRezervaciju(rezervacija);
        return this;
    }

//    @CommitAfter
//    Object onActionFromDelete(int id) {
//        rezervacijaDao.obrisiRezervaciju(id);
//        return this;
//    }

}
