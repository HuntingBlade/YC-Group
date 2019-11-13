package com.shytong.common.dao;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.common.dao
 * @Description:
 * @date 2018-01-1511:59
 */
public interface ICommDao<T> {
    public  int insert(T commDo);
    /**
     * 根据id修改信息
     * */
    public  int updateById(T commDo, String fields);
    /**
     * 根据id修改信息
     * */
    public  int updateById(T commDo);
    /**
     * 根据id获取信息
     * */
    public  T getById(String id, String fields);
    /**
     * 根据id获取信息
     * */
    public  T getById(String id);

    /**
     * 获取列表
     * @param param
     * @param pageNum
     * @param pageSize
     * @return
     */
    public  PageInfo listOfPage(Map<String, Object> param, int pageNum, int pageSize);

    public  int deleteById(String id);
    public  SyDao search();


}
