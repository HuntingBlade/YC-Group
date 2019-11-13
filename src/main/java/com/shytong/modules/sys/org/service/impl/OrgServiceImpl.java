package com.shytong.modules.sys.org.service.impl;
import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.constant.SyConstantInfo;
import com.shytong.core.util.SyIdUtils;
import com.shytong.modules.sys.org.dao.IOrgDao;
import com.shytong.modules.sys.org.model.OrgDo;
import com.shytong.modules.sys.org.service.IOrgService;
import com.shytong.modules.sys.user.dao.ISysUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @author 
 * @Package com.shytong.modules.sys.org.service.impl
 * @Description:
 * @date 2018-04-03 10:22:05
 */
    @Service("orgService")
public class OrgServiceImpl implements IOrgService  {
    @Resource(name ="orgDao")
    private IOrgDao orgDao;
    @Resource(name ="sysUserDao")
    private ISysUserDao sysUserDao;
    @Override
    public int insert(OrgDo orgDo) throws ApiBizException {
        int n=0;
        synchronized (this){
            if(!"0".equals(orgDo.getParentId())){
               OrgDo orgDo1=  orgDao.getById(orgDo.getParentId());
                if(orgDo1==null){
                    throw new ApiBizException(-1,"机构不存在");
                };
               }
            if(orgDao.search()
                    .eq("name",orgDo.getName())
                    .eq("parentId",orgDo.getParentId())
                    .isExists()){
                throw new ApiBizException(-1,"该父机构已存在同名机构");
            }
            orgDo.setId(SyIdUtils.uuid());
            n=  orgDao.insert(orgDo);
        }



        return  n;
    }

    @Override
    public int updateById(OrgDo  orgDo) throws ApiBizException {


           return  this.updateById(orgDo,null);
    }

    @Override
    public int updateById(OrgDo orgDo, String fields) throws ApiBizException {

        int n=0;
        synchronized (this){
            if(!"0".equals(orgDo.getParentId())){
                OrgDo orgDo1=  orgDao.getById(orgDo.getParentId());
                if(orgDo1==null){
                    throw new ApiBizException(-1,"机构不存在");
                };

            }
            if(orgDao.checkIsExists(orgDo.getName(),orgDo.getParentId(),orgDo.getId())){
                throw new ApiBizException(-1,"该父机构已存在同名机构");
            }
            n=orgDao.updateById(orgDo,fields);
        }

        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"更新失败");

        }
        return n;

    }

    @Override
    public OrgDo getById(String id) throws ApiBizException {

        return this.getById(id,null);
    }

    @Override
    public OrgDo getById(String id, String fields) throws ApiBizException {

        OrgDo syDo=orgDao.getById(id,fields);
        if(syDo==null){
        throw new ApiBizException(SyConstant.ERR_DATA_NULL, SyConstantInfo.ERR_DATA_NULL);
        }
        return syDo;
    }

    @Override
    public PageInfo listOfPage(Map<String,Object> params, int pageNum, int pageSize) throws ApiBizException {

        return orgDao.listOfPage(params ,  pageNum,  pageSize);
    }
    @Override
    public int deleteById(String id) throws ApiBizException {
        int n=orgDao.deleteById(id);



        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"删除失败");

        }

        return n;
    }
    }
