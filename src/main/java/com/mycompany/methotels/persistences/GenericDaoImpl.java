/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistences;

import com.mycompany.methotels.entities.AbstractEntity;
import java.util.Collections;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author NenadS
 * @param <T>
 */
public class GenericDaoImpl<T extends AbstractEntity> implements GenericDao<T> {

    @Inject
    private Session hibernate;

    @Override
    public AbstractEntity merge(AbstractEntity obj) {
        return (T) hibernate.merge(obj);
    }

    @Override
    public T delete(Integer idOfObj, Class klasa) {
        AbstractEntity tmpEntity = (AbstractEntity) hibernate.createCriteria(klasa).add(Restrictions.eq("id",
                idOfObj)).list().get(0);
        hibernate.delete((T) tmpEntity);
        hibernate.flush();
        return (T) tmpEntity;
    }

    @Override
    public T getElementById(Integer id, Class klasa) {
        AbstractEntity tmpEntity = (AbstractEntity) hibernate.createCriteria(klasa).add(Restrictions.eq("id",
                id)).list().get(0);
        return (T) tmpEntity;
    }

    @Override
    public List loadAllActive(Class klasa) {
        List<T> lista = hibernate.createCriteria(klasa).setResultTransformer(
                Criteria.DISTINCT_ROOT_ENTITY).list();
        Collections.sort(lista);
        return lista;
    }
}
