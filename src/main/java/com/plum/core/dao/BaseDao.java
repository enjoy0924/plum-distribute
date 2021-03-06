package com.plum.core.dao;

import com.plum.core.filter.PageSortFilter;

import java.io.Serializable;
import java.util.List;

/**
 * 所有基础Dao类的统一接口，如果有其他的DAO接口，可以继承自这个类，然后
 * 继续进行接口扩展
 *
 * Created by G_dragon on 2015/7/21.
 */
public interface BaseDao<T,PK extends Serializable> {

    T create(T entity);
    void batchSave(List<T> entities);
    T update(T entity);
    void delete(T entityWithId);

    T findOne(PK Id, Class<T> classname);
    List<T> findAll(String className);

    List<T> findAllByPage(String className, PageSortFilter page);

}
