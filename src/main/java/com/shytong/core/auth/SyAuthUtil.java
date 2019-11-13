package com.shytong.core.auth;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0315:20
 */
public class SyAuthUtil {



   public static Map<String, SySecurityManager> sySecurityManagerMap=new HashMap<>();

    public  static String DEFAULT_SYSTEM_CODE="SHYTONG";
    public static Subject getSubject() {
        Subject s=   SyAuthThreadContext.getSubject();


      return  s;

    }
    public static <T>T getUser() {
        Subject s=   SyAuthThreadContext.getSubject();
        if(s==null){
            return  null;
        }
        return  (T)s.getAuthenticationInfo().getUserInfo();
    }



    public static String getCertificate(String certificate,String salt ){


        return DigestUtils.md5Hex(certificate+salt) ;

    }
    public static Subject getSubject(String systemCode) {


        //Subject subject = SyAuthThreadContext.getSubject(systemCode);

        Subject subject = SyAuthThreadContext.getSubject();
        if (subject == null) {
            SubjectContext subjectContext = SyAuthThreadContext.getSubjectContext();
            SySecurityManager sySecurityManager = SyAuthUtil.getSySecurityManager(systemCode);
            if (sySecurityManager == null) {

                return  null;
//                throw new ApiBizRunException(-1, "获取对象失败");
            }
            subject =  sySecurityManager.createSubject(systemCode,subjectContext);


            if(subject!=null){

                SyAuthThreadContext.bind(subject);

            }
        }
        return subject;

    }

    public static SySecurityManager getSySecurityManagerBycode(String code) {

        return sySecurityManagerMap.get(code);
    }

    public static SySecurityManager getSySecurityManager(String code) {
        SySecurityManager sySecurityManager = SyAuthThreadContext.getSySecurityManager(code);
        if (sySecurityManager == null) {


            sySecurityManager = SyAuthUtil.getSySecurityManagerBycode(code);
        }

        return sySecurityManager;
    }


}
