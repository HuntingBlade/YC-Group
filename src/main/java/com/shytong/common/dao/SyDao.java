package com.shytong.common.dao;

import com.shytong.core.database.IIbatisDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.common.dao
 * @Description:
 * @date 2018-04-0621:09
 */
public class SyDao {
    private IIbatisDao baseDao;
    private String allFields;
    private Map selectFieldMap;
    private String fields;
    private String tableName;
    private String primaryKey;
    private StringBuilder whereSql=new StringBuilder(" 1=1 ");
    private StringBuilder setSql;
    private boolean isHasCondition=false;
    private Map<String,Object> params;
    public SyDao(IIbatisDao baseDao,  String fields,Map selectFieldMap, String tableName, String primaryKey){
        this.baseDao=baseDao;
        this.allFields=fields;
        this.tableName=tableName;
        this.primaryKey=primaryKey;
        this.selectFieldMap=selectFieldMap;

    }

    public SyDao where(String sql, Map<String,Object> params){

        whereSql.append(sql);
        if(this.params==null){
            this.params=new HashMap<>();
        }
        params.putAll(params);
        return  this;
    };

    private void setParam(String key,Object param){

        if(this.params==null){
            this.params=new HashMap<>();
        }
        params.put(key,param);
    }
    public SyDao eq(String field, Object value){
        isHasCondition=true;
        whereSql.append(" and ").append(selectFieldMap.get(field)).append("=#{").append(field).append("}");
        setParam(field,value);
        return  this;
    };
    public SyDao neq(String field, String value){

        isHasCondition=true;
        whereSql.append(" and ").append(selectFieldMap.get(field)).append("!=#{").append(field).append("}");
        setParam(field,value);
        return  this;
    };

    private String getSelectSql(){
        StringBuilder s=new StringBuilder();

        if(fields==null){
            s.append("select ").append(allFields);

        }
       return s.append(" from ")
                .append(this.tableName)
                .append(" where ")
                .append(whereSql).toString();

    }

    public SyDao set(String field, String value){

        if(setSql==null){
            setSql=new StringBuilder(" set ");
        }
        setSql.append(selectFieldMap.get(field)).append("=").append("#{").append(field).append("},");
        setParam(field,value);
        return  this;
    }
   private  String getUpdateSql(){

       StringBuilder s=new StringBuilder();
       return s.append("update ")
               .append(this.tableName).append(" ")
               .append(this.setSql.substring(0,this.setSql.length()-1))
               .append(" where ")
               .append(whereSql).toString();

   }
    private  String getDelSql(){

        StringBuilder s=new StringBuilder();
        return s.append("delete from  ")
                .append(this.tableName).append(" ")
                .append(" where ")
                .append(whereSql).toString();

    }


    public boolean isExists(){
       this.eq("isDeleted","0");
        params.put("sql",this.getSelectSql());
        List l=    baseDao.selctList(params);

        return l!=null&&!l.isEmpty();
    };
    public int update(){


         params.put("sql",this.getUpdateSql());
        return baseDao.update(params);

    };


    public int logicDelete(){
          this.set("isDeleted","1");
         return  this.update();
    };


    public int physicalDelete(){
        params.put("sql",this.getDelSql());


        return  baseDao.del(params);
    };

}
