package com.shytong.common.web;

/**
 * @author sytong
 * @Package com.shytong.common
 * @Description:
 * @date 2018-01-0418:05
 */
public class ResEntity {
    private  int errCode;
    private  String errInfo;
    private  Object entity;
    public ResEntity(){
     this.errCode=0;
     this.errInfo="Ok";
     }
    public ResEntity(Object entity){
        this.errCode=0;
        this.errInfo="Ok";
        this.entity=entity;

    }
    public ResEntity(int errCode,String errInfo){
        this.errInfo=errInfo;
        this.errCode=errCode;
    }
    public ResEntity(int errCode,String errInfo,Object entity){

        this.errInfo=errInfo;
        this.errCode=errCode;
        this.entity=entity;

    }
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
