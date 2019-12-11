package com.shytong.modules.channel.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.resultcode.ResultCode;
import com.shytong.modules.channel.dao.IChannelDao;
import com.shytong.modules.channel.model.ChannelDo;
import com.shytong.modules.channel.service.IChannelService;
import com.shytong.modules.sysconfig.dao.ISysConfigDao;
import com.shytong.modules.sysconfig.model.SysConfigDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/12 17:23
 */
@Service
public class ChannelServiceImpl implements IChannelService {

    @Autowired
    private IChannelDao channelDao;
    @Autowired
    private ISysConfigDao sysConfigDao;

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer insertChannel(ChannelDo channelDo) throws ApiBizException {
        if (channelDo == null) {
            throw new ApiBizException(-1, "参数出错");
        }
        Integer result = channelDao.insertChannel(channelDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer deletedChannelById(SyMap map) throws ApiBizException {
        Integer id = map.getInteger("id");
        if (id <= 0) {
            throw new ApiBizException(-1, "参数错误");
        }
        ChannelDo channelDo = new ChannelDo();
        channelDo.setId(id);
        channelDo.setIsDeleted(1);
        Integer result = channelDao.updateChannel(channelDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String updateChannel(Map map) throws ApiBizException {
        SyMap params = new SyMap(map);
        ChannelDo channelDo = new ChannelDo();
        SysConfigDo sysConfigDo = new SysConfigDo();
        Integer channelId = params.getInteger("id");
        String name = params.getString("name");
        String pageName = params.getString("pageName");
        Integer sort = params.getInteger("sort");
        String sysId = ((SysConfigDo) sysConfigDao.getSysConfig(channelId.toString(), "yc")).getId();
        String sysSource = params.getString("sysSource");
        String sysValue = params.getString("sysValue");
        String sysUrl = params.getString("sysUrl");

        channelDo.setId(channelId);
        channelDo.setName(name);
        channelDo.setPageName(pageName);
        channelDo.setSort(sort);
        sysConfigDo.setId(sysId);
        sysConfigDo.setSysSource(sysSource);
        sysConfigDo.setSysValue(sysValue);
        sysConfigDo.setSysUrl(sysUrl);

        if (!sysSource.isEmpty()) {
            String _sysSource = "0";
            if (_sysSource.equals(sysSource)) {
                sysConfigDo.setSysValue("/upfiles/img/" + sysConfigDo.getSysValue());
            }
        }

        Integer result1 = channelDao.updateChannel(channelDo);
        Integer result2 = sysConfigDao.update(sysConfigDo);
        if (result1 < 0 || result2 < 0) {
            throw new RuntimeException();
        }
        return ResultCode.SUCCESS;
    }

    @Override
    public PageInfo<ChannelDo> selectList(SyMap params) throws ApiBizException {
        return channelDao.selectList(1, 1, params);
    }

    @Override
    public void selectListById(ModelMap model, SyMap params, Integer pageSize, Integer pageNum, Integer type) {
        SysConfigDo sysConfigDo = (SysConfigDo) sysConfigDao.getSysConfig("secondClassPageSize", "yc");
        if (pageNum == null) {
            pageNum = 1;
        }
        if (sysConfigDo != null) {
            pageSize = Integer.parseInt(sysConfigDo.getSysValue());
        } else {
            pageSize = 10;
        }

        Integer parentId = params.getInteger("parentId");
        if (type == 1) {
            params.put("parentId", parentId);
            PageInfo firstChannelList = channelDao.getChannelListAndSysConfigByPId(params, pageNum, pageSize);
            List<ChannelDo> secondChannelList = channelDao.getSonChannelListById(parentId);
            model.addAttribute("firstChannelList", firstChannelList.getList());
            model.addAttribute("secondClassList", secondChannelList);
            model.addAttribute("secondClassTotal", firstChannelList.getTotal());
            model.addAttribute("secondClassPageSize", pageSize);
            model.addAttribute("secondClassPageNum", firstChannelList.getPageNum());
        } else {

        }
    }
}
