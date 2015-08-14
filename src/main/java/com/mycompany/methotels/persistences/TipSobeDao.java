/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistences;

import com.mycompany.methotels.entities.TipSobe;
import java.util.List;

/**
 *
 * @author NenadS
 */
public interface TipSobeDao {

    public List<TipSobe> getListaSvihTipovaSoba();

    public TipSobe getTipoSobeById(Integer id);

    public void dodajTipSobe(TipSobe tipSobe);

    public void obrisiTipSobe(Integer id);
}
