package com.plum.core.dao;

import com.plum.core.page.PageSortFilter;

import java.io.Serializable;
import java.util.List;

/**
 * 所有基础Dao类的统一接口，如果有其他的DAO接口，可以继承自这个类，然后
 * 继续进行接口扩展
 *
 * Created by G_dragon on 2015/7/21.
 */
public interface BaseDao<T,PK extends Serializable> {

    public T create(T entity);
    public T update(T entity);
    public void delete(T entityWithId);

    public T findOne(PK Id, Class<T> classname);
    public List<T> findAll(String className);

    public List<T> findAllByPage(PageSortFilter page);

}
