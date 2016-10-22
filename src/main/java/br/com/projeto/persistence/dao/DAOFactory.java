package br.com.projeto.persistence.dao;

import br.com.projeto.persistence.daointerfaces.DAO;


import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

/**
 * Created by jaels on 20/10/2016.
 */
public class DAOFactory {

    @Inject
    private EntityManager em;

    @SuppressWarnings({"rawtypes","unchecked"})
    @Produces
    @Dependent
    public <T> DAO<T> createDAO(InjectionPoint point){
        ParameterizedType type=(ParameterizedType)point.getType();
        Class classe = (Class) type.getActualTypeArguments()[0];
        return new DAOImpl<T>(classe,em);
    }
}
