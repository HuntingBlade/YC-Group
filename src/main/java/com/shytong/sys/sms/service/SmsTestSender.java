package com.shytong.sys.sms.service;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.service
 * @Description:
 * @date 2018-01-3018:16
 */
public class SmsTestSender extends SmsSender {
    @Override
    public SmsErrMsg sendMsg(String sendAppId, String type, String phone, Object param) {


        return new SmsErrMsg();
    }
}
