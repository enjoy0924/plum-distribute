package com.plum.core.dao;

import com.plum.core.filter.PageSortFilter;
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

    public void batchSave(List<T> entities){
        ParameterCheck.mandatory("entities", entities);
        int saveCount = 0;
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()){
            for(T entity : entities) {
                session.save(entity);
                if(++saveCount % 100 == 0)
                {
                    session.flush();
                    session.clear();
                    saveCount = 0;
                }
            }

        }
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
        String hql = "from " + className;
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()) {
            Query query = session.createQuery(hql);
            return query.list();
        }
        return null;
    }

    public int updateOrDelete(String hql, Map<String, Object> parameters){
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()) {
            Query query = session.createQuery(hql.toString());
            fillParameter(query, parameters);

            return query.executeUpdate();
        }

        return -1;
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

    public Long findCount(String hql, Map<String, Object> parameters){
        return (Long) findSingleByParameters(hql, parameters, false);
    }

    public List<T> findPageByParameters(String hql, PageSortFilter pageSortFilter , Map<String, Object> parameters, boolean cache){

        ParameterCheck.mandatory("pageSortFilter", pageSortFilter);

        /** 组装查询记录总数 **/
        String countHql = "select count(0) ";
        int fromIndex = hql.toLowerCase().indexOf("from");
        if(fromIndex < 0)
            return null;
        countHql = countHql + hql.substring(fromIndex);
        Long total = findCount(countHql, parameters);
        pageSortFilter.setTotalCount(total);

        /** 组装排序参数 **/
        StringBuilder hqlBuilder = new StringBuilder(hql);
        String sortField = pageSortFilter.getSortField();
        if(null != sortField && !sortField.isEmpty()){
            hqlBuilder.append(" order by "+sortField + " " + pageSortFilter.getSortType());
            hql = hqlBuilder.toString();
        }

        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()) {
            Query query = session.createQuery(hql.toString());
            fillParameter(query, parameters);
            query.setCacheable(cache);

            /** 添加分页参数 **/
            query.setFirstResult(pageSortFilter.getBeginIndex());
            query.setMaxResults(pageSortFilter.getSizePerPage());

            return query.list();
        }

        return null;
    }

    @Override
    public List<T> findAllByPage(String className, PageSortFilter page) {

        String hql = "from " + className;
        return findPageByParameters(hql, page, new HashMap<>(), false);
    }

}
