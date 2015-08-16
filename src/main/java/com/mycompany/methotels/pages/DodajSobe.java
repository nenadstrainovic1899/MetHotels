/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Soba;
import com.mycompany.methotels.entities.TipSobe;
import com.mycompany.methotels.persistences.SobaDao;
import com.mycompany.methotels.persistences.TipSobeDao;
import com.mycompany.methotels.services.ProtectedPage;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author NenadS
 */
@ProtectedPage
@RolesAllowed(value={"Admin"})
public class DodajSobe {

    @Property
    private Soba soba;
    @Property
    private Soba jednaSoba;
    @Property
    private List<Soba> listaSoba;
    @Property
    private List<TipSobe> listaTipovaSoba;
    @Property
    private TipSobe tipSobeId;

    @Inject
    private SobaDao sobaDao;
    @Inject
    private TipSobeDao tipSobeDao;

    public String getTipSobe() {
        if (jednaSoba.getTipSobeId() != null) {
            return jednaSoba.getTipSobeId().getTipSobe();
        }

        return "";
    }

    public String getTv() {
        if (jednaSoba.getTv()) {
            return "Ima";
        }

        return "Nema";
    }

    public String getInternet() {
        if (jednaSoba.getInternet()) {
            return "Ima";
        }

        return "Nema";
    }

    public String getDjakuzi() {
        if (jednaSoba.getDjakuzi()) {
            return "Ima";
        }

        return "Nema";
    }

    public ValueEncoder getEncoder() {
        return new ValueEncoder<TipSobe>() {

            @Override
            public String toClient(TipSobe v) {
                return String.valueOf(v.getId());
            }

            @Override
            public TipSobe toValue(String string) {
                TipSobe tipSobe = tipSobeDao.getTipoSobeById(Integer.parseInt(string));
                return tipSobe;
            }
        };
    }

    void onActivate() {
        soba = new Soba();
//        if (listaSoba == null) {
//            listaSoba = new ArrayList<Soba>();
//        }

        listaSoba = sobaDao.getListaSvihSoba();
        listaTipovaSoba = tipSobeDao.getListaSvihTipovaSoba();
    }

    @CommitAfter
    Object onSuccess() {
        soba.setTipSobeId(tipSobeId);
        sobaDao.dodajSobu(soba);
        return this;
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        sobaDao.obrisiSobu(id);
        return this;
    }
}
