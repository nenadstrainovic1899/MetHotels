/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistences;

import com.mycompany.methotels.entities.Rezervacija;
import java.util.List;

/**
 *
 * @author NenadS
 */
public interface RezervacijaDao {

    public List<Rezervacija> getListaRezervacija();

    public Rezervacija getRezervacijaById(Integer id);

    public void dodajRezervaciju(Rezervacija rezervacija);

    public void obrisiRezervaciju(Integer id);
}
