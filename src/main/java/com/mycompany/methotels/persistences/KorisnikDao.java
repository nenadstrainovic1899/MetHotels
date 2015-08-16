/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistences;

import com.mycompany.methotels.entities.Korisnik;
import java.util.List;

/**
 *
 * @author NenadS
 */
public interface KorisnikDao {

    public List<Korisnik> getListaSvihKorisnika();

    public Korisnik getKorisnikById(Integer id);

    public void dodajKorisnika(Korisnik korisnik);

    public void obrisiKorisnika(Integer id);

    public Korisnik proveriKorisnika(String username, String password);

    public Korisnik registrujKorisnika(Korisnik korisnik);

    public boolean korisnikPostoji(String username);
}
