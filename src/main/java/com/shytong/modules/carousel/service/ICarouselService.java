package com.shytong.modules.carousel.service;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.carousel.model.CarouselDo;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 20:24
 */
public interface ICarouselService {
    /**
     * 添加轮播项
     *
     * @param carouselDo
     * @return
     * @throws ApiBizException
     */
    Integer insertCarousel(CarouselDo carouselDo) throws ApiBizException;

    /**
     * 删除轮播项 (批量删除)
     *
     * @param array
     * @return
     * @throws ApiBizException
     */
    String deletedCarousel(String[] array) throws ApiBizException;

    /**
     * 修改轮播项
     *
     * @param carouselDo
     * @return
     * @throws ApiBizException
     */
    Integer updateCarousel(CarouselDo carouselDo) throws ApiBizException;

    /**
     * 获取轮播列表项
     *
     * @param params
     * @return
     * @throws ApiBizException
     */
    PageInfo selectCarouselList(SyMap params) throws ApiBizException;
}
