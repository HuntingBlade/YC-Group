package com.shytong.common.model;

/**
 * @author sytong
 * @Package com.shytong.common.model
 * @Description:
 * @date 2018-04-1715:52
 */
public class Msg {
    public int errCode;
    public String errInfo;

    public Msg(int errCode) {
        this.errCode = errCode;
    }
}
