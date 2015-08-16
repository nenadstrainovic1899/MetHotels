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
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author NenadS
 */
public class PaginationGrid {

    @Inject
    private TipSobeDao tipSobeDao;
    private int size = 20;

    Object onActivate(@RequestParameter("page") int page) {
        Class<?> act = null;
        int sizeOfAll = tipSobeDao.allActiveSizeTipoviSoba();

        List<TipSobe> lista = new ArrayList<TipSobe>();

        String response = "<table class=\"navigation\" > <th>\n"
                + " Tip sobe\n"
                + " </th>\n"
                + " ";

        lista = tipSobeDao.loadActiveFromTo(page);

        for (TipSobe d : lista) {
            response += (" <tr>\n"
                    + " <td> " + d.getTipSobe() + "</td>\n"
                    + " </tr>");
        }

        response += "</table>";
        float ceil = (float) sizeOfAll / (float) size;
        int totalPageSizes = (int) Math.ceil(ceil);
        response += "<ul class=\"pagination\">";
        
        for (int i = 1; i <= totalPageSizes; i++) {
            if (page == i) {
                response += ("<li class=\"callservice active\"><a href=\"#\">" + i + "</a></li>\n");
            } else {
                response += ("<li class=\"callservice\"><a href=\"#\">" + i + "</a></li>\n");
            }
        }
        
        response += "</ul>";
        return new TextStreamResponse("text/plain", response);
    }
}
