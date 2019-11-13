package com.shytong.sys.authorization;

import com.shytong.common.exception.ApiBizException;
import com.shytong.core.auth.Subject;
import com.shytong.core.auth.SyAuthUtil;

/**
 * @author sytong
 * @Package com.shytong.sys.authorization
 * @Description:
 * @date 2018-04-0519:01
 */
public class SyAuthSystemUtil {

   public static  <T> T getUserInfo(){

       Subject s= SyAuthUtil.getSubject();

       if(s==null||!s.isAuthented()){
           return null;

       }

       return s.getUserInfo();


    }
    public static  <T> T checkUserInfo() throws ApiBizException {

        Subject s= SyAuthUtil.getSubject();

        if(s==null||!s.isAuthented()){


            throw new ApiBizException(98,"无此用户信息");


        }

        return s.getUserInfo();


    }
}
