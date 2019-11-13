package com.shytong.modules.sys.resource.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.constant.SyConstantInfo;
import com.shytong.core.util.SyIdUtils;
import com.shytong.modules.sys.resource.dao.ISysResourceDao;
import com.shytong.modules.sys.resource.model.SysResourceDo;
import com.shytong.modules.sys.resource.service.ISysResourceService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @author 
 * @Package com.shytong.modules.sys.resource.service.impl
 * @Description:
 * @date 2018-04-03 11:57:11
 */
    @Service("sysResourceService")
public class SysResourceServiceImpl implements ISysResourceService  {
    @Resource(name ="sysResourceDao")
    private ISysResourceDao sysResourceDao;
    @Override
    @CacheEvict(value="roleResource",allEntries=true)
    public int insert(SysResourceDo sysResourceDo) throws ApiBizException {
         sysResourceDo.setId(SyIdUtils.uuid());
        return sysResourceDao.insert(sysResourceDo);
    }

    @Override
    @CacheEvict(value="roleResource",allEntries=true)
    public int updateById(SysResourceDo  sysResourceDo) throws ApiBizException {
       int n=sysResourceDao.updateById(sysResourceDo);
        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"更新失败");

        }
        return n;


    }

    @Override
    @CacheEvict(value="roleResource",allEntries=true)
    public int updateById(SysResourceDo sysResourceDo, String fields) throws ApiBizException {
        int n=sysResourceDao.updateById(sysResourceDo,fields);
        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"更新失败");

        }
        return n;

    }

    @Override
    public SysResourceDo getById(String id) throws ApiBizException {
        SysResourceDo syDo=sysResourceDao.getById(id);
        if(syDo==null){
        throw new ApiBizException(SyConstant.ERR_DATA_NULL, SyConstantInfo.ERR_DATA_NULL);
        }
        return syDo;
    }

    @Override
    public SysResourceDo getById(String id, String fields) throws ApiBizException {

        SysResourceDo syDo=sysResourceDao.getById(id,fields);
        if(syDo==null){
        throw new ApiBizException(SyConstant.ERR_DATA_NULL, SyConstantInfo.ERR_DATA_NULL);
        }
        return syDo;
    }

    @Override
    public PageInfo listOfPage(Map<String,Object> params, int pageNum, int pageSize) throws ApiBizException {

        return sysResourceDao.listOfPage(params ,  pageNum,  pageSize);
    }
    @Override
    @CacheEvict(value="roleResource",allEntries=true)
    public int deleteById(String id) throws ApiBizException {
        int n=sysResourceDao.deleteById(id);

        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"删除失败");

        }

        return n;
    }
    }
