package com.shytong.core.auth;

import com.shytong.core.sy.session.SySession;
import com.shytong.sys.authorization.CommResource;

import java.util.*;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0114:05
 */
public class Subject {
    private SySecurityManager sySecurityManager;
    private AuthenticationInfo authenticationInfo;
    private AuthorizationInfo authorizationInfo;
    private SySession sySession;
    private String sysTemCode;

    public String getSysTemCode() {
        return sysTemCode;
    }

    public void setSysTemCode(String sysTemCode) {
        this.sysTemCode = sysTemCode;
    }

    private boolean isAuthented=false;

    public <T> T getUserInfo(){

        return  (T)this.getAuthenticationInfo().getUserInfo();
    }

    public List<Object> getViewResource(){

        AuthorizationInfo authorizationInfo=getAuthorizationInfo();

        Set<String>s=new HashSet<>();
        List<Object> l=new ArrayList<>();

        if(authorizationInfo.getResouceObjects()!=null){
            for(CommResource c:authorizationInfo.getResouceObjects()){

                if("1".equals(c.getType())){

                    if(!s.contains(c.getId())){
                        l.add(c);
                        s.add(c.getId());
                    }
                }
            }
        }

        return l;
    }
    public boolean loginOut(){

      return  sySecurityManager.loginOut(this);

    }
    public AuthenticationInfo getAuthenticationInfo() {
        if(this.authenticationInfo!=null){
            return  this.authenticationInfo;
        }

//        sySecurityManager.getAuthenticingiRealmMap().get()
        this.authenticationInfo=sySecurityManager.getAuthenticationInfo(this) ;


        return authenticationInfo;
    }
    public void setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }

    public  void login(AuthenticationToken authenticationToken){

      sySecurityManager.login(this,authenticationToken);


    }

    public  void login(AuthenticationInfo authenticationInfo){

        sySecurityManager.login(this,authenticationInfo);


    }
    public  Subject(SySecurityManager sySecurityManager){

        this.sySecurityManager=sySecurityManager;
        this.sysTemCode=sySecurityManager.getSysTemCode();

    }
    private AuthorizationInfo getAuthorizationInfo(){
        if(this.authorizationInfo!=null){
            return this.authorizationInfo;
        }
        this.authorizationInfo=  sySecurityManager.getAuthorizationInfo(getAuthenticationInfo().getUserID());
       return    this.authorizationInfo;

    }
    private boolean hasAllResources(AuthorizationInfo authorizationInfo ,Collection<String> resouces){

        if(resouces!=null&&!resouces.isEmpty()){

            for (String resouce:resouces ){
                if(!hasResource(resouce,authorizationInfo)){
                    return false;
                }
            }
        }

        return  true;
    }
    private   boolean hasOneResource(AuthorizationInfo authorizationInfo ,Collection<String> resouces){

        if(resouces!=null&&!resouces.isEmpty()){

            for (String resouce:resouces ){
                if(hasResource(resouce,authorizationInfo)){
                    return true;
                }
            }
        }

        return  false;
    }
    public  boolean hasOneResource(Collection<String> resouces){
        return  this.hasOneResource(this.getAuthorizationInfo(),resouces);
    }
    public  boolean hasAllResources(Collection<String> resouces){
        return  this.hasAllResources(this.getAuthorizationInfo(),resouces);
    }

    private  boolean hasResource(String resource,AuthorizationInfo authorizationInfo){


        return  authorizationInfo!=null&&authorizationInfo.getResouces()!=null&&authorizationInfo.getResouces().contains(resource);



    }
    private   boolean hasRole(String role,AuthorizationInfo authorizationInfo){


        return  authorizationInfo!=null&&authorizationInfo.getRoles()!=null&&authorizationInfo.getRoles().contains(role);



    }
    private  boolean hasAllRoles(AuthorizationInfo authorizationInfo ,Collection<String> roles){

        if(roles!=null&&!roles.isEmpty()){

            for (String role:roles ){
                if(!hasRole(role,authorizationInfo)){
                    return false;
                }
            }
        }

        return  true;
    }
    public  boolean hasAllRoles(Collection<String> roles){

    return this.hasAllRoles(this.getAuthorizationInfo(),roles);

    }


    private    boolean hasOneRole(AuthorizationInfo authorizationInfo ,Collection<String> roles){

        if(roles!=null&&!roles.isEmpty()){

        for (String role:roles ){
            if(hasRole(role,authorizationInfo)){
                return true;
            }
        }
    }

        return  false;
        }
    public  boolean hasOneRole(Collection<String> roles){

        return this.hasOneRole(this.getAuthorizationInfo(),roles);

    }












    public SySession getSySession() {
        return sySession;
    }

    public void setSySession(SySession sySession) {
        this.sySession = sySession;
    }


    public boolean isAuthented() {
        return isAuthented;
    }

    public void setAuthented(boolean authented) {
        isAuthented = authented;
    }
}
