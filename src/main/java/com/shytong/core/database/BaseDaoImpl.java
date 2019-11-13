package com.shytong.core.database;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 *
 * @author shytong
 * @date 2017-12-28 18:10
 * @description <>
 *

 */

public class BaseDaoImpl  implements BaseDao {

    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    public int insert(String xmlID, Object param) {
        return  sqlSessionTemplate.insert(xmlID,param);
    }

    @Override
    public int update(String xmlID, Object param) {
        return sqlSessionTemplate.update(xmlID,param);
    }

    @Override
    public int delete(String xmlID, Object param) {
        return sqlSessionTemplate.delete(xmlID,param);
    }

    @Override
    public <T> T selectOne(String xmlID, Object param) {
        return sqlSessionTemplate.selectOne(xmlID,param);
    }

    @Override
    public <E> List<E> selectList(String xmlID, Object param) {


        return sqlSessionTemplate.selectList(xmlID,param);
    }

    @Override
    public <E> List<E> selectList(String xmlID, Object param, int pageNum,int pageSize ) {


        return    sqlSessionTemplate.selectList(xmlID,param,  new PageRowBounds(pageNum,pageSize) );
    }
    @Override
    public <E> PageInfo commlistOfPage(String xmlID, int pageNum, int pageSize, Object param)
    {



        PageInfo<E> p=new PageInfo(this.selectList(xmlID,param,pageNum,pageSize));
        return p;
    }

    /**
     *
     * @author shytong
     * @date 2017-12-28 18:11
     * @param  sqlSessionTemplate;
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
  /**  
   *      
   * @author shytong  
   * @date 2017-12-28 18:27  
   * @param sqlSessionTemplate  
   * @return void  
   */  

        this.sqlSessionTemplate = sqlSessionTemplate;
    }


}
