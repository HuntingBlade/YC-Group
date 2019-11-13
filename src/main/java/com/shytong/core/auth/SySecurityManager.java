package com.shytong.core.auth;

import com.shytong.common.exception.ApiBizRunException;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.sy.session.SySessionUtil;
import com.shytong.core.util.SyStringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0114:06
 */
public class SySecurityManager {


    static String AUTH_TYPE_SESSION="1";
    static String AUTH_TYPE_TOKEN="2";
    private Map<String, AuthenticingiRealm> authenticingiRealmMap;

    private AuthenticingiRealm authenticationInfoDeal;
    private AuthorizingRealm authorizingRealm;
    private  String authIsAuthentedKey;
    private String authenticationInfoKey;
    private String sysTemCode;


    public AuthorizationInfo getAuthorizationInfo(String principal){

        if(authorizingRealm==null){
            throw new ApiBizRunException(-1,"配置异常");

        }
      return   authorizingRealm.doGetAuthorizationInfo(principal);

    }

    public String getAuthIsAuthentedKey() {
        return authIsAuthentedKey;
    }

    public void setAuthIsAuthentedKey(String authIsAuthentedKey) {
        this.authIsAuthentedKey = authIsAuthentedKey;
    }

    public String getAuthenticationInfoKey() {
        return authenticationInfoKey;
    }

    public void setAuthenticationInfoKey(String authenticationInfoKey) {
        this.authenticationInfoKey = authenticationInfoKey;
    }

    public String getSysTemCode() {
        return sysTemCode;
    }


 private boolean checkCertificate(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo){

     String m= DigestUtils.md5Hex(authenticationToken.getCertificate()+authenticationInfo.getSalt());
         return authenticationInfo.getCertificate().equals(m);


 }

 public AuthenticationInfo getAuthenticationInfo(Subject subject ){

     return  this.getAuthenticationInfoDeal().getAuthenticationInfoByString(subject.getSySession().getValueOfString(this.getAuthenticationInfoKey()));

 }



    public boolean loginOut(Subject subject){
        this.del(subject);
        subject.setAuthented(false);
        SyAuthThreadContext.unbindSubject();
        return  true;
    }

    private void del(Subject subject){
        subject.getSySession().removeKey(this.authenticationInfoKey,this.authIsAuthentedKey);
    }
    private void save(Subject subject){
        subject.getSySession().setValue(this.authenticationInfoKey,subject.getAuthenticationInfo());
        subject.getSySession().setValue(this.authIsAuthentedKey,subject.isAuthented());
        subject.getSySession().save();


    }
    public  void login(Subject subject, AuthenticationInfo authenticationInfo){


        if(subject.isAuthented()){

            throw new ApiBizRunException(1,"已登陆");
        }

        if(SyStringUtils.isBlank(authenticationInfo.getLoginType())){

            throw new ApiBizRunException(1,"登录类型不能为空");
        }





        if(authenticationInfo!=null){
//            if(! this.checkCertificate(authenticationToken,a)){
//
//                throw new ApiBizRunException(SyConstant.ERR_AUTH_CERTIFICATE_WRONG,"登陆凭证错误");
//            }
            subject.setAuthented(true);
            subject.setAuthenticationInfo(authenticationInfo);
            this.save(subject);

        }else{

            throw new ApiBizRunException(-1,"登陆失败");
        }

    }
    public void login(Subject subject, AuthenticationToken authenticationToken){

        if(subject.isAuthented()){

            throw new ApiBizRunException(1,"已登陆");
        }
        if(SyStringUtils.isBlank(authenticationToken.getAuthType())){

            throw new ApiBizRunException(1,"登录类型不能为空");
        }

        AuthenticingiRealm authenticingiRealm=this.authenticingiRealmMap.get(authenticationToken.getAuthType());
        if(authenticingiRealm==null){
            throw new ApiBizRunException(-1,"配置异常");

        }

        AuthenticationInfo a= authenticingiRealm.doGetAuthenticationInfo(authenticationToken);

        a.setLoginType(authenticationToken.getAuthType());


        if(a!=null){
            if(! this.checkCertificate(authenticationToken,a)){

                throw new ApiBizRunException(SyConstant.ERR_AUTH_CERTIFICATE_WRONG,"登陆凭证错误");
            }
            subject.setAuthented(true);
            subject.setAuthenticationInfo(a);
            this.save(subject);

        }else{

            throw new ApiBizRunException(-1,"登陆失败");
        }




    }



    public void setSysTemCode(String systemCode){

        this.sysTemCode=systemCode;
        this.authIsAuthentedKey=systemCode+"AUTH_IS_AUTHENTEDKEY";
        this.authenticationInfoKey=systemCode+"AUTH_ENTICATION_INFO_KEY";

    }



    public Subject createSubject(String systemCode, SubjectContext subjectContext){



//         if(AUTH_TYPE_SESSION.equals(authType)){
        HttpServletRequest request=subjectContext.getrequest();
        if(request==null){
            throw new ApiBizRunException(-1,"request不存在");
        }
        HttpSession s=request.getSession(true);
        System.out.println(s.getId());
        Subject subject=new Subject(this);
//         }
         //获取用户信息
        subject.setSySession(SySessionUtil.getSession(s.getId()));

        if(subject.getSySession().getValueOfString(this.authIsAuthentedKey)!=null){

            subject.setAuthented(true);
        }



            return  subject;
    }


    public Map<String, AuthenticingiRealm> getAuthenticingiRealmMap() {
        return authenticingiRealmMap;
    }

    public void setAuthenticingiRealm(String loginType, AuthenticingiRealm authenticingiRealm) {

        if(this.authenticingiRealmMap==null){

            this.authenticingiRealmMap=new HashMap<>();
        }


        this.authenticingiRealmMap.put(loginType,authenticingiRealm);
    }

    public AuthorizingRealm getAuthorizingRealm() {
        return authorizingRealm;
    }

    public void setAuthorizingRealm(AuthorizingRealm authorizingRealm) {
        this.authorizingRealm = authorizingRealm;
    }

    public AuthenticingiRealm getAuthenticationInfoDeal() {
        return authenticationInfoDeal;
    }

    public void setAuthenticationInfoDeal(AuthenticingiRealm authenticationInfoDeal) {
        this.authenticationInfoDeal = authenticationInfoDeal;
    }
}
