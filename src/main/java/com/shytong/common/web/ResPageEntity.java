package com.shytong.common.web;

/**
 * @author sytong
 * @Package com.shytong.common
 * @Description:
 * @date 2018-01-0418:06
 */
public class ResPageEntity {
    private  int code;
    private  String msg;
    private  Object data;
    private  int pageNum;
    private  int pageSize;
    private  long count;

    public ResPageEntity() {
    }
    public ResPageEntity( Object data) {
        this.data = data;
    }
    public ResPageEntity( Object data,long count) {
        this.data = data;
        this.count = count;
    }


    public ResPageEntity( Object data, int pageNum, int pageSize, long count) {
        this.data = data;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
    }

    public ResPageEntity(int code, String msg, Object data, int pageNum, int pageSize, long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
