package com.shytong.modules.sys.role.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.constant.SyConstantInfo;
import com.shytong.core.util.SyIdUtils;
import com.shytong.modules.sys.resource.model.SysResourceDo;
import com.shytong.modules.sys.role.dao.ISysChannelRoleDao;
import com.shytong.modules.sys.role.dao.ISysResourceRoleDao;
import com.shytong.modules.sys.role.dao.ISysRoleDao;
import com.shytong.modules.sys.role.dao.IUserRoleDao;
import com.shytong.modules.sys.role.model.*;
import com.shytong.modules.sys.role.service.ISysRoleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author 
 * @Package com.shytong.modules.sys.role.service.impl
 * @Description:
 * @date 2018-04-03 10:28:13
 */
    @Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService  {
    @Resource(name ="sysRoleDao")
    private ISysRoleDao sysRoleDao;
    @Resource(name ="userRoleDao")
    private IUserRoleDao userRoleDao;
    @Resource(name ="sysResourceRoleDao")
    private ISysResourceRoleDao sysResourceRoleDao;
    @Resource(name ="sysChannelRoleDao")
    private ISysChannelRoleDao sysChannelRoleDao;
    @Override
    public int insert(SysRoleDo sysRoleDo) throws ApiBizException {
         sysRoleDo.setId(SyIdUtils.uuid());
        //角色是否存在
        int n=0;
        synchronized (this){
            if(sysRoleDao.checkExistsByName(sysRoleDo.getName(),sysRoleDo.getOrgId(),null)){

                throw new ApiBizException(98,"该机构下该角色已存在");
            }
            n= sysRoleDao.insert(sysRoleDo);
        }


        return  n;
    }

    @Override
    public int updateById(SysRoleDo  sysRoleDo) throws ApiBizException {



         return  this.updateById(sysRoleDo,null);


    }

    @Override
    public int updateById(SysRoleDo sysRoleDo, String fields) throws ApiBizException {


        int n=0;
        synchronized (this){
            if(sysRoleDao.checkExistsByName(sysRoleDo.getName(),sysRoleDo.getOrgId(),sysRoleDo.getId())){

                throw new ApiBizException(98,"该机构下该角色已存在");
            }
            n= sysRoleDao.updateById(sysRoleDo,fields);
        }

        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"更新失败");

        }
        return n;

    }

    @Override
    public SysRoleDo getById(String id) throws ApiBizException {

        return this.getById(id,null);
    }

    @Override
    public SysRoleDo getById(String id, String fields) throws ApiBizException {

        SysRoleDo syDo=sysRoleDao.getById(id,fields);
        if(syDo==null){
        throw new ApiBizException(SyConstant.ERR_DATA_NULL, SyConstantInfo.ERR_DATA_NULL);
        }
        return syDo;
    }

    @Override
    public PageInfo listOfPage(Map<String,Object> params, int pageNum, int pageSize) throws ApiBizException {

        return sysRoleDao.listOfPage(params ,  pageNum,  pageSize);
    }
    @Override
    public int deleteById(String id) throws ApiBizException {
        int n=sysRoleDao.deleteById(id);

        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"删除失败");

        }

        return n;
    }


    @Override
    @Transactional(rollbackFor = { Exception.class,RuntimeException.class })
    public int addUsers(RoleUserDto roleUserDto) throws ApiBizException {

          //删除旧的角色

          userRoleDao.deleteByRoleId(roleUserDto.getRoleId());
        UserRoleDo userRoleDo=new UserRoleDo();
        userRoleDo.setRoleId(roleUserDto.getRoleId());
          for(String userId:roleUserDto.getUserIds()){
              userRoleDo.setUserId(userId);
              userRoleDo.setId(SyIdUtils.uuid());
              userRoleDo.setIsDeleted(0);
              userRoleDao.insert(userRoleDo);
          }


        return 1;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class,RuntimeException.class })
    public int addRoles(RoleUserDto roleUserDto) throws ApiBizException {

        userRoleDao.deleteByUserId(roleUserDto.getUserId());
        UserRoleDo userRoleDo=new UserRoleDo();
        userRoleDo.setUserId(roleUserDto.getUserId());
        for(String roleId:roleUserDto.getRoleIds()){
            userRoleDo.setRoleId(roleId);
            userRoleDo.setId(SyIdUtils.uuid());
            userRoleDo.setIsDeleted(0);
            userRoleDao.insert(userRoleDo);
        }


        return 1;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class,RuntimeException.class })
    public int addChannelResources(RoleChannelDto roleChannelDto) throws ApiBizException {
        sysChannelRoleDao.update(roleChannelDto);


        SysChannelRoleDo sysChannelRoleDo=new SysChannelRoleDo();
        sysChannelRoleDo.setRoleId(roleChannelDto.getRoleId());
//        for(ChannelDo channelDo:roleChannelDto.getResources()){
//            if(channelDo.getChaId()==null){
//                throw  new ApiBizException(-1,"栏目id不能为空"); }
//            sysChannelRoleDo.setScrId(SyIdUtils.uuid());
//            sysChannelRoleDo.setChannelId(channelDo.getChaId().toString());
//            sysChannelRoleDo.setGmtCreate(new Date());
//            sysChannelRoleDao.insertChannel(sysChannelRoleDo);
//        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class,RuntimeException.class })
    @CacheEvict(value="roleResource",allEntries=true)
    public int addResources(RoleResourceDto roleResourceDto) throws ApiBizException {
        sysResourceRoleDao.search().eq("roleId",roleResourceDto.getRoleId()).logicDelete();


        SysResourceRoleDo sysResourceRoleDo=new SysResourceRoleDo();
        sysResourceRoleDo.setRoleId(roleResourceDto.getRoleId());
        for(SysResourceDo sysResourceDo:roleResourceDto.getResources()){
            if(sysResourceDo.getId()==null){
                throw  new ApiBizException(-1,"资源id不能为空"); }
            sysResourceRoleDo.setId(SyIdUtils.uuid());
            sysResourceRoleDo.setResourceId(sysResourceDo.getId());
            sysResourceRoleDo.setGmtCreate(new Date());
            sysResourceRoleDao.insert(sysResourceRoleDo);
        }
        return 0;
    }

    @Override
    public PageInfo getUserRoleList(Map<String, Object> params, int pageNum, int pageSize) throws ApiBizException {

        params.put("roleId",null);
        return userRoleDao.listOfPage(params,pageNum,pageSize);
    }

    @Override
    public PageInfo getRoleUserList(Map<String, Object> params, int pageNum, int pageSize) throws ApiBizException {
        params.put("userId",null);
        return userRoleDao.listOfPage(params,pageNum,pageSize);
    }

    @Override
    public List<Object> getRoleResources(Map<String, Object> params) throws ApiBizException {



        return sysResourceRoleDao.getRoleResourceDos(params.get("roleId").toString());
    }

    @Override
    public List<Object> getRoleChannel(Map<String, Object> params) throws ApiBizException {
        return sysResourceRoleDao.getRoleChannel(params.get("roleId").toString());
    }

    @Override
    public List<Object> getRoleChannelPerson(Map<String, Object> params) throws ApiBizException {
        return sysResourceRoleDao.getPersonRoleChannel(params.get("roleId").toString());
    }
}
