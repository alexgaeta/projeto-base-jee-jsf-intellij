package br.com.projeto.persistence.dao;

import br.com.projeto.persistence.daointerfaces.DAO;
import br.com.projeto.persistence.fileservice.FileXMLService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jaels on 20/10/2016.
 */
public class DAOImpl<T> implements DAO<T> {
    @PersistenceContext
    private EntityManager em;

    private final Class<T> classe;

    private static final FileXMLService hqlQuery;
    private static final FileXMLService sqlQuery;

    static {
         hqlQuery = new FileXMLService("hql.xml");
        sqlQuery = new FileXMLService("sql.xml");
    }

    public  DAOImpl(Class<T> classe, EntityManager em){
           this.classe=classe;
           this.em=em;
    }

    @Override
    public T save(T entity) {
        em.merge(entity);
        em.flush();
        return  entity;
    }

    @Override
    public T update(T entity) {
        entity= em.merge(entity);
        em.flush();
        return entity;
    }

    @Override
    public List<T> getAll() {
        String hql="Select e from "+this.classe.getSimpleName() +" e";
        TypedQuery<T> query = em.createQuery(hql, this.classe);
        return query.getResultList();
    }

    @Override
    public List<T> findByHQLQueryNoParamaters(String queryId,int maxResults){
        String hql = hqlQuery.findValue(queryId);
        TypedQuery<T> query = em.createQuery(hql,this.classe);
        return maxResults == 0 ? query.getResultList() : query.setMaxResults(maxResults).getResultList();

    }


    @Override
    public List<T> findByHQLQuery(String queryId, List<Object> value, int maxResults){
        String hql = hqlQuery.findValue(queryId);
        Pattern pattern = Pattern.compile("(:\\w+)");
        Matcher matcher = pattern.matcher(hql);
        List<String> params = new ArrayList<>();
        while (matcher.find()){
            params.add(matcher.group().replace( ":",  ""));
        }
        System.out.print(hql);
        TypedQuery<T> query = em.createQuery(hql,this.classe);
        for (int i = 0; i < params.size() ; i++) {
            System.out.print(params.get(i)+ " - " +  value.get(i));
            query.setParameter(params.get(i), value.get(i));
        }
        return maxResults == 0 ? query.getResultList() : query.setMaxResults(maxResults).getResultList();
    //    return  null;
    }

    @Override
    public void remove(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.flush();
    }

    @Override
    public T finById(Serializable id) {
        return em.find(this.classe, id);
    }
}
