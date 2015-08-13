/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Soba;
import java.util.ArrayList;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 *
 * @author NenadS
 */
public class DodajSobe {

    @Property
    private Soba soba;

    @Property
    private ArrayList<Soba> listaSoba;

    @Inject
    private Session session;

    void onActivate() {
        if (listaSoba == null) {
            listaSoba = new ArrayList<Soba>();
        }

        listaSoba = (ArrayList<Soba>) session.createCriteria(Soba.class).list();
    }

    @CommitAfter
    Object onSuccess() {
        session.persist(soba);
        return this;
    }
}
