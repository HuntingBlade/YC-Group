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
    public String insertChannel(ChannelDo channelDo) throws ApiBizException {
        if (channelDo == null) {
            return ResultCode.PARAMETER_ERROR;
        }
        Integer id = channelDo.getParentId();
        ChannelDo parentChannelDo = channelDao.getChannelById(id);
        String group = "";
        String indexName = "";
        if (parentChannelDo.getId() == 2) {
            group = "about";
            indexName = "/front/public/about";
        } else if (parentChannelDo.getId() == 3) {
            group = "qualification";
            indexName = "/front/public/channelDetail";
        } else if (parentChannelDo.getId() == 4) {
            group = "case";
            indexName = "/front/public/channelInfo";
        } else if (parentChannelDo.getId() == 5) {
            group = "news";
            indexName = "/front/public/join";
        } else if (parentChannelDo.getId() == 6) {
            group = "join";
            indexName = "/front/public/join";
        } else if (parentChannelDo.getId() == 7) {
            group = "contact";
            indexName = "/front/public/contact";
        }
        channelDo.setGroup(group);
        channelDo.setIndexName(indexName);
        Integer result = channelDao.insertChannel(channelDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String deletedChannelById(SyMap map) throws ApiBizException {
        Integer id = map.getInteger("id");
        ChannelDo channelDo = new ChannelDo();
        channelDo.setId(id);
        channelDo.setIsDeleted(1);
        Integer result = channelDao.updateChannel(channelDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String updateChannel(Map map, String type) throws ApiBizException {
        SyMap params = new SyMap(map);
        ChannelDo channelDo = new ChannelDo();
        Integer channelId = params.getInteger("id");
        String name = params.getString("name");
        Integer sort = params.getInteger("sort");
        String pageName = params.getString("pageName");
        String listTemplate = params.getString("listTemplate");
        channelDo.setId(channelId);
        channelDo.setName(name);
        channelDo.setPageName(pageName);
        channelDo.setSort(sort);
        channelDo.setListTemplate(listTemplate);

        Integer updateSysConfig;
        if ("firstClass".equals(type)) {
            SysConfigDo sysConfigDo = new SysConfigDo();
            String sysId = ((SysConfigDo) sysConfigDao.getSysConfig(channelId.toString(), "yc")).getId();
            String sysSource = params.getString("sysSource");
            String sysValue = params.getString("sysValue");
            String sysUrl = params.getString("sysUrl");
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
            updateSysConfig = sysConfigDao.update(sysConfigDo);
            if (updateSysConfig < 0) {
                throw new RuntimeException();
            }
        }
        Integer updateChannel = channelDao.updateChannel(channelDo);
        if (updateChannel < 0) {
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
