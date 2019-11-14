package com.shytong.modules.front.service.impl;

import com.shytong.modules.front.dao.IFrontDao;
import com.shytong.modules.front.service.IFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * @description:
 * @author: CL
 * @time: 2019/11/11 14:34
 */
@Service
public class FrontServiceImpl implements IFrontService {

    @Autowired
    private IFrontDao frontDao;

    @Override
    public void setHtml(ModelMap model) {
        // logo图片
        // 栏目
        // 轮播图
    }
}
