package com.shytong.modules.user.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;

/**
 * @author 
 * @Package com.shytong.modules.user.model
 * @Description:
 * @date 2018-04-16 18:08:30
 */
public class UserDto extends UserDo  implements Serializable {
    private static final long serialVersionUID = 1L;


   /**
    *旧密码
     **/
    private String oldPwd;

   /**
    *标识
     **/
    private String identifier;

   /**
    *系统标识
     **/
    private String sysIdentifier;

   /**
    *类型
     **/
    private String type;



    public void setOldPwd(String oldPwd){
        this.oldPwd = oldPwd;
        }

    public String getOldPwd(){
        return   oldPwd;
        }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
        }

    public String getIdentifier(){
        return   identifier;
        }

    public void setSysIdentifier(String sysIdentifier){
        this.sysIdentifier = sysIdentifier;
        }

    public String getSysIdentifier(){
        return   sysIdentifier;
        }

    public void setType(String type){
        this.type = type;
        }

    public String getType(){
        return   type;
        }


}
