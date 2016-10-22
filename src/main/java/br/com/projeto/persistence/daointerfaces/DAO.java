package br.com.projeto.persistence.daointerfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jaels on 20/10/2016.
 */
public interface DAO<T> extends Serializable {
    T save(T entity);
    T update(T entity);
    List<T> getAll();
    List<T> findByHQLQueryNoParamaters(String queryId, int maxResults);
    List<T> findByHQLQuery(String queryId, List<Object> value, int maxResults);
    void remove(T entity);
    T finById(Serializable id);
}
