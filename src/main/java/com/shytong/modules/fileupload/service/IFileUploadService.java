package com.shytong.modules.fileupload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * @Author: CL
 * @Date: 2019/12/6 13:28
 */
public interface IFileUploadService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    String handleFileUpLoad(MultipartFile file) throws FileNotFoundException;
}
