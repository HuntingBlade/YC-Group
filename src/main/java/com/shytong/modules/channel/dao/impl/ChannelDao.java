package com.shytong.modules.channel.dao.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.channel.dao.IChannelDao;
import com.shytong.modules.channel.model.ChannelDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/12 17:25
 */
@Repository
public class ChannelDao implements IChannelDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public Integer insertChannel(ChannelDo channelDo) {
        return baseDao.insert("ChannelMapper.insert", channelDo);
    }

    @Override
    public Integer updateChannel(ChannelDo channelDo) {
        return baseDao.update("ChannelMapper.update", channelDo);
    }

    @Override
    public PageInfo<ChannelDo> selectList(Integer pageNum, Integer pageSize, SyMap params) {
        return baseDao.commlistOfPage("ChannelMapper.select", pageNum, pageSize, params);
    }

    @Override
    public List<ChannelDo> getSonChannelListById(Integer parentId) {
        return baseDao.selectList("ChannelMapper.getSonChannelListById", parentId);
    }

    @Override
    public List<Map> getChannelListByPId(Integer parentId) {
        return baseDao.selectList("ChannelMapper.getChannelListByPId", parentId);
    }

    @Override
    public List<Map> getChannelListBySonChannelId(Integer channelId) {
        return baseDao.selectList("ChannelMapper.getChannelListBySonChannelId", channelId);
    }

    @Override
    public List<Map> getChannelListByParentChannelId(Integer parentId) {
        return baseDao.selectList("ChannelMapper.getChannelListByParentChannelId", parentId);
    }

    @Override
    public ChannelDo getChannelById(Integer channelId) {
        return baseDao.selectOne("ChannelMapper.getChannelById", channelId);
    }

    @Override
    public List<ChannelDo> getSonChannelListByGroup(String group) {
        return baseDao.selectList("ChannelMapper.getSonChannelListByGroup", group);
    }

    @Override
    public Integer getParentChannelId(Integer channelId) {
        return baseDao.selectOne("ChannelMapper.getParentChannelId", channelId);
    }

    @Override
    public Integer channelIsExist(Integer channelId) {
        return baseDao.selectOne("ChannelMapper.channelIsExist", channelId);
    }

    @Override
    public List<ChannelDo> getChannelGroupById(Integer channelId) {
        return baseDao.selectList("ChannelMapper.getChannelGroupById", channelId);
    }

    @Override
    public PageInfo getChannelListAndSysConfigByPId(SyMap params, Integer pageNum, Integer pageSize) {
        return baseDao.commlistOfPage("ChannelMapper.getChannelListAndSysConfigByPId", pageNum, pageSize, params);
    }


}
