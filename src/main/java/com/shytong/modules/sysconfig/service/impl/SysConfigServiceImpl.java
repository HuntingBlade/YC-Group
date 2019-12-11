package com.shytong.modules.sysconfig.service.impl;

import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.resultcode.ResultCode;
import com.shytong.core.util.SyIdUtils;
import com.shytong.modules.sysconfig.dao.ISysConfigDao;
import com.shytong.modules.sysconfig.model.SysConfigDo;
import com.shytong.modules.sysconfig.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/11 17:32
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    @Autowired
    private ISysConfigDao sysConfigDao;

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String insert(SysConfigDo sysConfigDo, String type) throws ApiBizException {
        if (sysConfigDo == null) {
            return ResultCode.PARAMETER_ERROR;
        }
        JudgeType(sysConfigDo, type);
        sysConfigDo.setId(SyIdUtils.uuid());
        Integer result = sysConfigDao.insert(sysConfigDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer deleted(SyMap map) {
        SysConfigDo sysConfigDo = new SysConfigDo();
        sysConfigDo.setId(map.getString("id"));
        sysConfigDo.setIsDeleted(1);
        Integer result = sysConfigDao.update(sysConfigDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String update(SysConfigDo sysConfigDo, String type) {
        if (sysConfigDo == null) {
            return ResultCode.PARAMETER_ERROR;
        }
        JudgeType(sysConfigDo, type);
        Integer result = sysConfigDao.update(sysConfigDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return ResultCode.SUCCESS;
    }

    @Override
    public List getList(SyMap params) {
        return sysConfigDao.getList(params);
    }


    public void JudgeType(SysConfigDo sysConfigDo, String type) {
        String _type = "indexCarousel";
        if (_type.equals(type)) {
            String sysSource = sysConfigDo.getSysSource();
            if (!sysSource.isEmpty()) {
                sysConfigDo.setSysKey("1");
                String source = "0";
                if (sysSource.equals(source)) {
                    sysConfigDo.setSysValue("/upfiles/img/" + sysConfigDo.getSysValue());
                }
            }
        }
    }
}
