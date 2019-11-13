package com.shytong.core.auth;



import com.shytong.core.util.SyStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0316:51
 */
public class SubjectContext extends HashMap<String,Object> {
    private HttpServletRequest request;
    public void setRequest(HttpServletRequest request){
        this.request=request;
    }
    public HttpServletRequest getrequest(){
        return  this.request;
    }
    public String getAuthType(){
        return SyStringUtils.getString(this.get("authType")) ;
    };
    public String getSessionID(){
        return SyStringUtils.getString(this.get("sessionID")) ;

    };
    public void setSessionID(String sessionID){
         this.put("sessionID",sessionID);
    };
    public void setAuthType(String authType){
        this.put("authType",authType);
    };

}
