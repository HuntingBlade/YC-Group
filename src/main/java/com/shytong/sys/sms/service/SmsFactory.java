package com.shytong.sys.sms.service;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.service
 * @Description:
 * @date 2018-01-1711:24
 */
public class SmsFactory {


 public static ISmsService getObject(SmsConfig smsConfig, AliConfig aliConfig){

     SmsService s=new SmsService(smsConfig,new AliSmsSender(aliConfig));
     return  s;
 }
    public static ISmsService getSmsTest(SmsConfig smsConfig){

        SmsService s=new SmsService(smsConfig,new SmsTestSender());
        return  s;
    }
}
