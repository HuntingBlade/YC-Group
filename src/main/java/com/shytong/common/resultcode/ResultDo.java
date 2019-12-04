package com.shytong.common.resultcode;


/**
 * @Author: CL
 * @Date: 2019/11/29 21:58
 */
public class ResultDo{
    private String errCode;
    private String errInfo;
    private Object entity;

    public ResultDo() {
    }

    public ResultDo(String errCode) {
        this.errCode = errCode;
        this.errInfo = ResultCodeInfo.getCodeInfoByErrCode(errCode);
    }

    public ResultDo(String errCode, String errInfo, Object entity) {
        this.errCode = errCode;
        this.errInfo = ResultCodeInfo.getCodeInfoByErrCode(errCode);
        this.entity = entity;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
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
