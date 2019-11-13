package com.shytong.common.service.validator.impl;

import com.shytong.common.service.validator.IValidatorService;
import com.shytong.core.util.SyStringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author sytong
 * @Package com.shytong.common.service.validator
 * @Description:
 * @date 2018-01-2411:13
 */

 @Service("validatorService")
public class ValidatorService implements IValidatorService {


    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1\\d{10}$";
    @Override
    public String isNotBlank(String value, String filedName) {
        if(SyStringUtils.isNotBlank(value)){
            return  null;
        }
        return filedName+"不能为空";
    }


    private static String checkPhone(String value, String filedName,boolean isRequired){
        if(SyStringUtils.isBlank(value)){

            if(isRequired){
                return filedName+"不能为空";
            }
            return null;
        }
        if(value.length()!=11){
            return filedName+"格式错误";
        }

        if(!Pattern.matches(REGEX_MOBILE,value)){

            return filedName+"格式错误";
        }
        return null;


    }

    private String checkIn(String value, String filedName,boolean isRequired,String... values){
        if(SyStringUtils.isBlank(value)){

            if(isRequired){
                return filedName+"不能为空";
            }
            return null;
        }
        if(values.length==0){
            return filedName+"格式错误";
        }
        for (String str:
                values) {
            if(value.equals(str)){
                return null;
            }

        }
        return filedName+"格式错误";


    }
    @Override
    public String isPhone(String value, String filedName) {


        return checkPhone(value,filedName,false );
    }

    @Override
    public String isPhoneRequired(String value, String filedName) {

        return checkPhone(value,filedName,true );
    }



    @Override
    public String isIn(String value, String filedName, String... values) {


        return checkIn(value,filedName,false,values);
    }

    @Override
    public String isInRequired(String value, String filedName, String... values) {


        return checkIn(value,filedName,true,values);
    }
}
