/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.rest;

import com.mycompany.methotels.entities.TipSobe;
import com.mycompany.methotels.persistences.GenericDao;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;

public class TipSobeWebService implements TipSobeServiceInterface {
    
    @Inject
    private GenericDao<TipSobe> genericDao;

    @Override
    public List<TipSobe> getAll() {
        return (List<TipSobe>) genericDao.loadAllActive(TipSobe.class);
    }

    @Override
    public TipSobe getById(Integer id) {
        return (TipSobe) genericDao.getElementById(id, TipSobe.class);
    }

    @Override
    public Response post(TipSobe tipSobe) {
        return Response.ok().entity(genericDao.merge(tipSobe)).build();
    }
    
}
