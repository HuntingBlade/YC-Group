package com.shytong.modules.resourceLib.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.resourceLib.dao.ResourceLibDao;
import com.shytong.modules.resourceLib.model.UrlDo;
import com.shytong.modules.resourceLib.service.ResourceLibService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @Auther: HC
 * @Date: 2018/10/15 15:22
 * @Description:
 */
@Service
public class ResourceLibServiceImpl implements ResourceLibService {

    @Resource(name = "resourceLibDao")
    private ResourceLibDao resourceLibDao;

    @Override
    public PageInfo getList(SyMap params, int pageNum, int pageSize) {
        return resourceLibDao.getList(params, pageNum, pageSize);
    }

    @Override
    public Object detail(Map queryParamMap) {
        return resourceLibDao.findByResId(queryParamMap);
    }

    @Override
    public String addFile(MultipartFile file,String type) throws IOException {

        System.err.println("fileName：" + file.getOriginalFilename());
        System.err.println("fileSize：" + file.getSize());

        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
        //如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/images/uplaod/");
        if (!upload.exists()) {
            upload.mkdirs();
            System.out.println(upload.getAbsolutePath());
            //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
            //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/

        }

        System.err.println(path);
        System.err.println(upload);

        String newFileName;
        if("file".equals(type)){
            newFileName = file.getOriginalFilename();
        }else{
            //获取MD5
            byte[] bytes = file.getBytes();
            newFileName = DigestUtils.md5Hex(bytes);
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            newFileName=newFileName+"."+suffix;
        }
        //文件拷贝
        InputStream inputStream = file.getInputStream();
        String newName = upload.getAbsolutePath() +"/"+ newFileName;
        FileOutputStream out = new FileOutputStream(new File(newName));
        byte[] b = new byte[1024];
        int n = 0;
        while ((n = inputStream.read(b)) != -1) {
            out.write(b, 0, n);
        }

        inputStream.close();
        out.close();

        System.err.println(newFileName);

        return "/images/uplaod/"+ newFileName;
    }
}
