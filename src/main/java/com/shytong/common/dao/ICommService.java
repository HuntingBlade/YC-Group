package com.shytong.common.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;

import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.common.dao
 * @Description:
 * @date 2018-03-2612:52
 */
public interface ICommService <T> {
    int insert(T t) throws ApiBizException;
    int updateById(T t) throws ApiBizException;
    int updateById(T t, String fields) throws ApiBizException;
    T  getById(String id)throws ApiBizException;
    T  getById(String id, String fields)throws ApiBizException;

    int deleteById(String id) throws ApiBizException;
    PageInfo listOfPage(Map<String, Object> params, int pageNum, int pageSize)throws ApiBizException;

}
