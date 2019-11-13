package com.shytong.modules.resourceLib.service;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: HC
 * @Date: 2018/10/15 15:22
 * @Description:
 */
public interface ResourceLibService {

    /**
     * 资源库列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ApiBizException
     */
    PageInfo getList(SyMap params, int pageNum, int pageSize)  throws ApiBizException;

    /**
     * 获取资源详情
     * @param
     * @return
     */
    Object detail(Map queryParamMap);

    /**
     * 增加文件
     * @param file
     * @return
     */
    String addFile(MultipartFile file, String type) throws IOException;

}
