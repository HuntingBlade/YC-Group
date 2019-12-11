package com.shytong.modules.channel.service;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.channel.model.ChannelDo;
import org.springframework.ui.ModelMap;

import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/12 17:21
 */
public interface IChannelService {

    /**
     * 添加栏目项
     *
     * @param channelDo
     * @return
     * @throws ApiBizException
     */
    Integer insertChannel(ChannelDo channelDo) throws ApiBizException;

    /**
     * 根据ID删除栏目项
     *
     * @param map
     * @return
     * @throws ApiBizException
     */
    Integer deletedChannelById(SyMap map) throws ApiBizException;

    /**
     * 修改项目栏
     *
     * @param map
     * @return
     * @throws ApiBizException
     */
    String updateChannel(Map map) throws ApiBizException;

    /**
     * 获取栏目列表
     *
     * @param params
     * @return
     * @throws ApiBizException
     */
    PageInfo<ChannelDo> selectList(SyMap params) throws ApiBizException;

    /**
     * 根据id获取栏目信息
     *
     * @param model
     * @param params
     * @param pageSize
     * @param pageNum
     * @param type
     * @return
     */
    void selectListById(ModelMap model, SyMap params, Integer pageSize, Integer pageNum, Integer type);
}
