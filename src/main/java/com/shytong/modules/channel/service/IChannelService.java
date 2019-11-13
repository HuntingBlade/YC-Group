package com.shytong.modules.channel.service;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.channel.model.ChannelDo;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 17:21
 */
public interface IChannelService {

    /**
     * 添加栏目项
     * @param channelDo
     * @return
     * @throws ApiBizException
     */
    Integer insertChannel(ChannelDo channelDo) throws ApiBizException;

    /**
     * 根据ID删除栏目项
     * @param map
     * @return
     * @throws ApiBizException
     */
    Integer deletedChannelById(SyMap map) throws ApiBizException;

    /**
     * 修改项目栏
     * @param channelDo
     * @return
     * @throws ApiBizException
     */
    Integer updateChannel(ChannelDo channelDo) throws ApiBizException;

    /**
     * 获取栏目列表
     * @param params
     * @return
     * @throws ApiBizException
     */
    PageInfo<ChannelDo> selectList(SyMap params) throws ApiBizException;
}
