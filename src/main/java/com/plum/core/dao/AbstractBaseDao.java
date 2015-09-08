package com.plum.core.dao;

import com.plum.core.queryfilter.PageSortFilter;
import com.plum.core.utils.ParameterCheck;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * Created by G_dragon on 2015/7/23.
 */
public abstract class AbstractBaseDao< T, PK extends Serializable> implements BaseDao<T, PK> {

    @Resource
    private SessionFactory sessionFactory;
    private SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public T create(T entity){
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen())
            session.save(entity);
        return entity;
    }

    public T update(T entity){
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen())
            session.saveOrUpdate(entity);
        return entity;
    }

    public void delete(T entityWithId){
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen())
            session.delete(entityWithId);
    }

    public T findOne(PK Id, Class<T> classname) {
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen())
            return (T) session.get(classname, Id);
        return null;
    }

    public List<T> findAll(String className) {
        StringBuffer hql = new StringBuffer();
        hql.append("from ").append(className);
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()) {
            Query query = session.createQuery(hql.toString());
            return query.list();
        }
        return null;
    }

    /**
     * 携带查询参数的查询
     *
     * @param query
     * @param parameters
     * @return
     */
    private boolean fillParameter(Query query, Map<String, Object> parameters){
        ParameterCheck.mandatory("query", query);
        ParameterCheck.mandatory("parameters", parameters);

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String parameterName = entry.getKey();
            Object parameterValueObj = entry.getValue();
            if(parameterValueObj instanceof Collection){
                query.setParameterList(parameterName, (Collection) parameterValueObj);
            }else if(parameterValueObj instanceof Date){
                query.setDate(parameterName, (Date) parameterValueObj);
            }else {
                query.setParameter(parameterName, parameterValueObj);
            }
        }

        return true;
    }
    public List<T> findMultiByParameters(String hql, Map<String, Object> parameters, boolean cache){

        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()) {
            Query query = session.createQuery(hql.toString());
            fillParameter(query, parameters);
            query.setCacheable(cache);

            return query.list();
        }

        return null;
    }

    public T findSingleByParameters(String hql, Map<String, Object> parameters, boolean cache){

        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()) {
            Query query = session.createQuery(hql.toString());
            fillParameter(query, parameters);
            query.setCacheable(cache);

            List<T> ls = query.list();
            if(null != ls && !ls.isEmpty()){
                return ls.get(0);
            }
        }

        return null;
    }

    public List<T> findPageByParameters(String hql, PageSortFilter pageUtils , Map<String, Object> parameters, boolean cache){
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()) {
            Query query = session.createQuery(hql.toString());
            fillParameter(query, parameters);
            query.setCacheable(cache);


            return query.list();
        }

        return null;
    }

    public List<T> findAllByPage(PageSortFilter page){
        return null;
    }

}
