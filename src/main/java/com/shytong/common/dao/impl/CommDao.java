package com.shytong.common.dao.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.dao.ICommDao;
import com.shytong.common.dao.SyDao;
import com.shytong.common.model.CommSqlDto;
import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.core.database.IIbatisDao;
import com.shytong.core.util.SyStringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.common.dao.impl
 * @Description:
 * @date 2018-01-1512:01
 */
public class CommDao<T extends CommSqlDto> implements ICommDao<T> {
    @Resource
    private BaseDao baseDao;
    @Resource
    private  IIbatisDao ibatisDao;

    private T commDo;
    private String selectFields;
    private Map<String,String> selectFieldMap;
    private String insertXml;
    private String tableName;
    private String updateSelectiveXml;
    private String selectXml;
    private String selectiveXml;
    private String selectListXml;
    private String deleteXml;
    private String primaryKey;
    public CommDao (){

    }



    public CommDao (String insertXml, String updateSelectiveXml, String selectiveXml, String selectXml, String selectListXml, String deleteXml){
        this.insertXml=insertXml;
//        this.updateXml=updateXml;
        this.selectiveXml=selectiveXml;
        this.selectXml=selectXml;
        this.deleteXml=deleteXml;
        this.selectListXml=selectListXml;
        this.updateSelectiveXml=updateSelectiveXml;
        this.primaryKey=primaryKey;
    };


    @Override
    public int insert(T commDo) {

        return  baseDao.insert(this.insertXml,commDo);
    }

    @Override
    public int updateById(T commDo, String fields) {

        if(fields!=null){

            commDo.setFiledsByArray(fields.split(","));
        }

        return baseDao.update(  this.updateSelectiveXml,commDo);
    }

    @Override
    public int updateById(T commDo) {

        return this.updateById(commDo,null);

    }


    private String dealField(String fieldStr){



        String [] fields=fieldStr.split(",");

        StringBuilder s=new StringBuilder();

        for (String field :fields){

            s.append(",").append(SyStringUtils.hump2Unline(field)).append(" as ").append(field);

        }
            return s.substring(1);

    };
    @Override
    public T getById(String id, String fields) {


     if(SyStringUtils.isBlank(fields)){


    return  this.getById(id);
        }


        return baseDao.selectOne(this.selectXml,  SyMap.map("id",id).put("fields",fields));



    }

    @Override
    public T getById(String id) {



        return  baseDao.selectOne(this.selectiveXml,id);
    }

    @Override
    public PageInfo listOfPage(Map<String, Object> param, int pageNum, int pageSize) {
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        PageInfo p=new PageInfo(baseDao.selectList(this.selectListXml,param));
        return p;
    }


    public <E>List<E> commlist(String xmlID,Object param) {
        return baseDao.selectList(xmlID,param);
    }

    public <E>PageInfo commlistOfPage(String xmlID, int pageNum, int pageSize,Object param) {



        PageInfo<E> p=new PageInfo(baseDao.selectList(xmlID,param,pageNum,pageSize));
        return p;
    }

    public <E>List<E> commlist(String xmlID,Object param ,int pageNum, int pageSize) {
        return baseDao.selectList(xmlID,param,pageNum,pageSize);
    }

    public <E> E findByXml(String xmlID,Object param ) {

        return baseDao.selectOne(xmlID,param);
    }
    @Override
    public int deleteById(String id) {
        return baseDao.update(this.deleteXml,id);
    }

    @Override
    public SyDao search() {

        return  new SyDao(ibatisDao,this.selectFields,this.selectFieldMap,commDo.syTableName(),commDo.syPrimaryKey());


    }

    public int commUpdate(String xmlid,Object param){


            return  baseDao.update(xmlid,param);
    }
    public int commDelete(String xmlid,Object param){


        return  baseDao.delete(xmlid,param);
    }
    public boolean isExists(String xmlid,Object param){
        List<String> l=baseDao.selectList(xmlid,param);
        return l!=null&&!l.isEmpty();


    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }





    public T getCommDo() {
        return commDo;
    }

    public void setCommDo(T commDo) {



        if(commDo!=null&&!SyStringUtils.isBlank(commDo.syFields())){
            StringBuilder s=new StringBuilder();
            String[]fields=commDo.syFields().split(",");
            selectFieldMap =new HashMap<>();
            for (String field:
                    fields ) {
                s.append(",").append(field).append(" as ").append(SyStringUtils.unline2Hump(field));
                selectFieldMap.put(SyStringUtils.unline2Hump(field),field);
            }
            this.selectFields=s.substring(1);

        }

        this.commDo = commDo;
    }
}


