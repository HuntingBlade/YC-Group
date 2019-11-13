package com.shytong.modules.carouselconfig.service;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.carouselconfig.model.CarouselConfigDo;

/**
 * @Author CL
 * @Date 2019/11/13
 */
public interface ICarouselConfigService {
    /**
     * 添加轮播配置项
     * @param carouselConfigDo
     * @return
     * @throws ApiBizException
     */
    Integer addCarouselConfig(CarouselConfigDo carouselConfigDo) throws ApiBizException;

    /**
     * 删除轮播配置项
     * @param params
     * @return
     * @throws ApiBizException
     */
    Integer deletedCarouselConfig(SyMap params) throws ApiBizException;

    /**
     * 更新轮播配置项
     * @param carouselConfigDo
     * @return
     * @throws ApiBizException
     */
    Integer updateCarouselConfig(CarouselConfigDo carouselConfigDo) throws ApiBizException;

    /**
     * 获取轮播配置列表项
     * @param params
     * @return
     * @throws ApiBizException
     */
    PageInfo selectCarouselConfig(SyMap params) throws ApiBizException;
}
