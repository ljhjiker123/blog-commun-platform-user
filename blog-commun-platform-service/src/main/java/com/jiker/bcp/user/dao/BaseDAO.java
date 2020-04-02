package com.jiker.bcp.user.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
@Mapper
public interface BaseDAO<T> {

    /**
     * Insert
     *
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * select by condition
     * @param t
     * @return
     */
    T selectByCondition(T t);

}
