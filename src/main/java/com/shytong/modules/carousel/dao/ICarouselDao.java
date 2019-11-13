package com.shytong.modules.carousel.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.carousel.model.CarouselDo;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 20:26
 */
public interface ICarouselDao {
    /**
     * 添加轮播项
     * @param carouselDo
     * @return
     */
    Integer insertCarousel(CarouselDo carouselDo);

    /**
     * 删除轮播项
     * @param list
     * @return
     */
    Integer deletedCarousel(List list);

    /**
     * 修改轮播项
     * @param carouselDo
     * @return
     */
    Integer updateCarousel(CarouselDo carouselDo);

    /**
     * 获取轮播列表项
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    PageInfo selectCarouselList(Integer pageNum, Integer pageSize, SyMap params);
}
