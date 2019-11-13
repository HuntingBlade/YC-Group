package com.shytong.modules.carousel.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.carousel.dao.ICarouselDao;
import com.shytong.modules.carousel.model.CarouselDo;
import com.shytong.modules.carousel.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 20:25
 */
@Service
public class CarouselServiceImpl implements ICarouselService {

    @Autowired
    private ICarouselDao carouselDao;

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer insertCarousel(CarouselDo carouselDo) throws ApiBizException {
        if (carouselDo == null) {
            throw new ApiBizException(-1, "参数错误");
        }
        Integer result = carouselDao.insertCarousel(carouselDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer deletedCarousel(List list) throws ApiBizException {
        if (list.size() <= 0) {
            throw new ApiBizException(-1, "参数错误");
        }
        Integer result = carouselDao.deletedCarousel(list);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public Integer updateCarousel(CarouselDo carouselDo) throws ApiBizException {
        if (carouselDo == null) {
            throw new ApiBizException(-1, "参数错误");
        }
        Integer result = carouselDao.updateCarousel(carouselDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public PageInfo selectCarouselList(SyMap params) throws ApiBizException {
        Integer pageNum = params.getInteger("pageNum");
        Integer pageSize = params.getInteger("pageSize");
        if (pageNum < 0 || pageSize < 0) {
            throw new ApiBizException(-1, "参数错误");
        }
        PageInfo list = carouselDao.selectCarouselList(pageNum, pageSize, params);
        return list;
    }
}
