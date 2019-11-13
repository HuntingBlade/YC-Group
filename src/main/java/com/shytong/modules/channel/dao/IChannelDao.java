package com.shytong.modules.channel.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.channel.model.ChannelDo;

/**
 * @Author: CL
 * @Date: 2019/11/12 17:46
 */
public interface IChannelDao {
    /**
     * 添加栏目项
     * @param channelDo
     * @return
     */
    Integer insertChannel(ChannelDo channelDo);

    /**
     * 更新栏目项
     *
     * @param channelDo
     * @return
     */
    Integer updateChannel(ChannelDo channelDo);

    /**
     * 获取栏目列表
     *
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    PageInfo<ChannelDo> selectList(Integer pageNum, Integer pageSize, SyMap params);
}
