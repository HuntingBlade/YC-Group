package com.shytong.sys.authorization;

import com.shytong.modules.sys.user.model.SysUserDo;

import java.util.List;

/**
 * @author sytong
 * @Package com.shytong.sys.authorization
 * @Description:
 * @date 2018-05-2215:19
 */
public class AuthSysUser extends SysUserDo implements User {

    private String sysTemCode;
    private List<String> linkOrgIds;

    public List<String> getLinkOrgIds() {
        return linkOrgIds;
    }

    public void setLinkOrgIds(List<String> linkOrgIds) {
        this.linkOrgIds = linkOrgIds;
    }

    public String getSysTemCode() {
        return sysTemCode;
    }

    public void setSysTemCode(String sysTemCode) {
        this.sysTemCode = sysTemCode;
    }

    @Override
    public String getUserId() {
        return this.getId();
    }
}
