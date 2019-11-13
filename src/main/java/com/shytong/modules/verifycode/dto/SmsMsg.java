package com.shytong.modules.verifycode.dto;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.dto
 * @Description:
 * @date 2018-01-1715:01
 */
public class SmsMsg {
    private  String phone;
    private  String verifyCode;
    private  String type;
    private  String userType;
    private  int time;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
