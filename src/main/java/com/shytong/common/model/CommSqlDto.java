package com.shytong.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.common.dao.Model
 * @Description:
 * @date 2018-01-1514:10
 */
public class CommSqlDto  {


    @JsonIgnore
    private  transient Map<String,String> fields;
    @JsonIgnore
    private  transient int isAll=1;
    @JsonIgnore
    private  transient int pageNum;
    @JsonIgnore
    private   transient int pageSize;
    public  void setFiledsByArray(String[] fileds){

        if(fileds==null||fileds.length==0){

            return;
        }

        if(this.fields==null){
            this.fields=new HashMap<>();

        }

        for (String filed:
                fileds ) {
            this.fields.put(filed,"1")  ;
        }
        this.isAll=0;

    }
    public String syFields(){
        return "";
    }
    public String syTableName(){
        return "";
    }
    public String syPrimaryKey(){
        return "";
    }
    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public int getIsAll() {
        return isAll;
    }

    public void setIsAll(int isAll) {
        this.isAll = isAll;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
