package com.shytong.core.database;

import com.github.pagehelper.PageInfo;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;


public interface BaseDao {
    public SqlSessionTemplate getSqlSessionTemplate();
    public int insert(String xmlID, Object param);
    public int update(String xmlID, Object param);
    public int delete(String xmlID, Object param);
    public <T>T selectOne(String xmlID, Object param);
    <E> List<E> selectList(String xmlID, Object param);
    <E> List<E> selectList(String xmlID, Object param, int pageNum, int pageSize);

    /**
     * 分页list
     * @param xmlID
     * @param pageNum 页数
     * @param pageSize 每页显示条数
     * @param param 传入参数
     * @param <E>
     * @return
     */
    public <E> PageInfo commlistOfPage(String xmlID, int pageNum, int pageSize, Object param);

}
