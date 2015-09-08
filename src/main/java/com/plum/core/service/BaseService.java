package com.plum.core.service;

import com.plum.core.queryfilter.PageSortFilter;

import java.io.Serializable;
import java.util.List;

/**
 * 基础服务类
 *
 * Created by G_dragon on 2015/7/21.
 */
public interface BaseService<DTOType, Index extends Serializable> {

    DTOType create(DTOType cube);

    DTOType modify(DTOType cube);

    DTOType delete(Index index);

    DTOType findByIndex(Index index);

    DTOType findOne(Index index);

    List<DTOType> findAll();

    List<DTOType> findAllByPage(PageSortFilter page);

}
