package com.plum.core.dao;

import com.plum.core.page.PageUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by G_dragon on 2015/7/23.
 */
public abstract class AbstractBaseDao< T, PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK> {

    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
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

    public List<T> findAllByPage(PageUtils page){
        return null;
    }

}
