package com.jiker.bcp.user.dao;

import com.jiker.bcp.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
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
     *
     * @param t
     * @return
     */
    T selectByCondition(T t);

    /**
     * select by id
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * update By Primary Key Selective
     *
     * @param t
     * @return
     */
    int updateByPrimaryKeySelective(T t);
}
