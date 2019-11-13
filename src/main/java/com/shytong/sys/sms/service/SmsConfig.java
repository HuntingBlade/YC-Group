package com.shytong.sys.sms.service;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.service
 * @Description:
 * @date 2018-01-1711:27
 */
public class SmsConfig {
    private  int defaultType=0;
    private  String sendAppId;

    public int getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(int defaultType) {
        this.defaultType = defaultType;
    }

    public String getSendAppId() {
        return sendAppId;
    }

    public void setSendAppId(String sendAppId) {
        this.sendAppId = sendAppId;
    }
}
