package com.plum.core.dao;

/**
 * 共享缓存(Redis or MemoryCached)的交互接口
 *
 * Created by Andy on 2015/9/9.
 */
public interface ShareCacheDao {

    Object getValueByKey(String key);
}
