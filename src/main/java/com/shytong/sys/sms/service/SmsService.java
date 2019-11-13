package com.shytong.sys.sms.service;

import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.service
 * @Description:
 * @date 2018-01-1711:22
 */
public class SmsService  implements  ISmsService{

    private SmsSender smsSender;
    private SmsConfig smsConfig;
     SmsService(SmsConfig smsConfig, SmsSender smsSender){
        this.smsConfig=smsConfig;
        this.smsSender=smsSender;
     }

    @Override
    public SmsErrMsg sendMsg(String type, String phone, Map<String, String> param) {
     SmsErrMsg s=   smsSender.sendMsg(smsConfig.getSendAppId(),type,phone,param);

        return s;
    }

    @Override
    public void sendMsg(String type, String phone, String param) {
        SmsErrMsg s=   smsSender.sendMsg(smsConfig.getSendAppId(),type,phone,param);
    }

    @Override
    public boolean checkCode(String type, String phone,Map<String,String> param,String code,int time) {

        SmsErrMsg s=   smsSender.sendMsg(smsConfig.getSendAppId(),type,phone,param);
        return false;
    }

    @Override
    public boolean sendCode(String type, String phone, String code) {
        return false;
    }
}
