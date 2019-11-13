package com.shytong.modules.channel.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.channel.dao.IChannelDao;
import com.shytong.modules.channel.dao.impl.ChannelDao;
import com.shytong.modules.channel.model.ChannelDo;
import com.shytong.modules.channel.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 17:23
 */
@Service
public class ChannelServiceImpl implements IChannelService {

    @Autowired
    private IChannelDao channelDao;

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
        // 伪删
        channelDo.setIsDeleted(1);
        Integer result = channelDao.updateChannel(channelDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer updateChannel(ChannelDo channelDo) throws ApiBizException {
        if (channelDo == null) {
            throw new ApiBizException(-1, "参数出错");
        }
        Integer result = channelDao.updateChannel(channelDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public PageInfo<ChannelDo> selectList(SyMap params) throws ApiBizException {
        Integer pageNum = params.getInteger("pageNum");
        Integer pageSize = params.getInteger("pageSize");
        return channelDao.selectList(pageNum, pageSize, params);
    }
}
