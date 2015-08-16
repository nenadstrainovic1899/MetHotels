/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.components.GenericEditor;
import com.mycompany.methotels.entities.TipSobe;
import com.mycompany.methotels.persistences.TipSobeDao;
import com.mycompany.methotels.services.ProtectedPage;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

/**
 *
 * @author NenadS
 */
@ProtectedPage
@RolesAllowed(value = {"Admin"})
public class DodajTipSobe extends GenericEditor<TipSobe>{
//
//    @Property
//    @Persist
//    private TipSobe tipSobe;
//    @Property
//    private TipSobe jedanTipSobe;
//    @Property
//    private List<TipSobe> listaTipovaSoba;
//
//    @Inject
//    private TipSobeDao tipSobeDao;
//
//    void onActivate() {
////        if (listaTipovaSoba == null) {
////            listaTipovaSoba = new ArrayList<TipSobe>();
////        }
//
//        listaTipovaSoba = tipSobeDao.getListaSvihTipovaSoba();
//    }
//
//    @CommitAfter
//    Object onSuccess() {
//        tipSobeDao.dodajIliUpdatujTipSobe(tipSobe);
//        tipSobe = new TipSobe();
//        return this;
//    }
//
//    @CommitAfter
//    Object onActionFromDelete(int id) {
//        tipSobeDao.obrisiTipSobe(id);
//        tipSobe = new TipSobe();
//        return this;
//    }
//
//    @CommitAfter
//    Object onActionFromEdit(TipSobe tipSobe) {
//        this.tipSobe = tipSobe;
//        return this;
//    }
//
//    public JSONObject getOptions() {
//        JSONObject json = new JSONObject();
//        json.put("bJQueryUI", "true");
//        json.put("bStateSave", true);
//        json.put("bAutoWidth", true);
//        return json;
//    }
}
