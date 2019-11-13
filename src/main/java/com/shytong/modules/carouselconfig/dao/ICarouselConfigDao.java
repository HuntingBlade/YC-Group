package com.shytong.modules.carouselconfig.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.carouselconfig.model.CarouselConfigDo;

/**
 * @Author CL
 * @Date 2019/11/13
 */
public interface ICarouselConfigDao {
    /**
     * 添加轮播配置项
     * @param carouselConfigDo
     * @return
     */
    Integer addCarouselConfig(CarouselConfigDo carouselConfigDo);

    /**
     * 更新轮播配置项
     * @param carouselConfigDo
     * @return
     */
    Integer updateCarouselConfig(CarouselConfigDo carouselConfigDo);

    /**
     * 获取轮播配置列表项
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    PageInfo selectCarouselConfig(int pageNum, int pageSize, SyMap params);
}
