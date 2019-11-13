package com.shytong.common.service.validator;

/**
 * @author sytong
 * @Package com.shytong.common.service.validator
 * @Description:
 * @date 2018-01-2411:06
 */
public interface IValidatorService {
    /**
     *
     * @author shytong
     * @date 2018-01-24 11:27
     * @param value
     * @param filedName
     * @return
     */
    public  String isNotBlank(String value, String filedName);
    public  String isPhone(String value, String filedName);
    public  String isPhoneRequired(String value, String filedName);
    public  String isIn(String value, String filedName, String... values);
    public  String isInRequired(String value, String filedName, String... values);


}
