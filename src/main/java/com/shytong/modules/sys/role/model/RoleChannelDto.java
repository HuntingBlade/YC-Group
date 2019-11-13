package com.shytong.modules.sys.role.model;

import com.shytong.common.model.CommSqlDto;

import java.io.Serializable;

/**
 * @Auther: HC
 * @Date: 2018/11/20 14:00
 * @Description:
 */
public class RoleChannelDto extends CommSqlDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *角色id
     **/
    private String roleId;

    /**
     *资源
     **/
//    private List<ChannelDo> resources;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

//    public List<ChannelDo> getResources() {
//        return resources;
//    }
//
//    public void setResources(List<ChannelDo> resources) {
//        this.resources = resources;
//    }
}
