package com.shytong.modules.carousel.dao.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.carousel.dao.ICarouselDao;
import com.shytong.modules.carousel.model.CarouselDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 20:28
 */
@Repository
public class CarouselDaoImpl implements ICarouselDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public Integer insertCarousel(CarouselDo carouselDo) {
        return baseDao.insert("CarouselMapper.insertCarousel", carouselDo);
    }

    @Override
    public Integer deletedCarousel(List list) {
        return baseDao.delete("CarouselMapper.deletedCarousel", list);
    }

    @Override
    public Integer updateCarousel(CarouselDo carouselDo) {
        return baseDao.update("CarouselMapper.updateCarousel", carouselDo);
    }

    @Override
    public PageInfo selectCarouselList(Integer pageNum, Integer pageSize, SyMap params) {
        return baseDao.commlistOfPage("CarouselMapper.selectCarouselList", pageNum, pageSize, params);
    }
}
