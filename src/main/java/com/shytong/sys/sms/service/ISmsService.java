package com.shytong.sys.sms.service;

import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.service
 * @Description:
 * @date 2018-01-1710:54
 */
public interface ISmsService {

    SmsErrMsg sendMsg(String type, String phone, Map<String, String> param);
    void sendMsg(String type, String phone, String param);
    boolean checkCode(String type, String phone, Map<String, String> param, String code, int time);
    boolean sendCode(String type, String phone, String code);
}
