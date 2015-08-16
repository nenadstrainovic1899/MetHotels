/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages.services;

import com.mycompany.methotels.entities.TipSobe;
import com.mycompany.methotels.persistences.TipSobeDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author NenadS
 */
public class NadjiTipSobe {

    @Inject
    private Request request;

    @Property
    private List<TipSobe> listaTipovaSoba;

    @Property
    private TipSobe tipSobe;

    @Inject
    private TipSobeDao tipSobeDao;

    Object onActivate(@RequestParameter("tipSobe") String tipSobe) {
        if (listaTipovaSoba == null) {
            listaTipovaSoba = new ArrayList<TipSobe>();
        }

        String response = "<table class=\"navigation\" > <th>\n"
                + " Tip sobe\n"
                + " </th>\n"
                + " ";

        listaTipovaSoba = tipSobeDao.getListaTipovaSobaPoImenu(tipSobe);

        for (TipSobe d : listaTipovaSoba) {
            response += (" <tr>\n"
                    + " <td> " + d.getTipSobe() + "</td>\n"
                    + " </tr>");
        }

        response += "</table>";
        return new TextStreamResponse("text/plain", response);
    }

}
