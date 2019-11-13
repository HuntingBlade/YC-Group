package com.shytong.sys.sms.service;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.service
 * @Description:
 * @date 2018-01-1711:37
 */
public abstract  class SmsSender {

    public abstract SmsErrMsg sendMsg(String sendAppId, String type, String phone, Object param);
}
