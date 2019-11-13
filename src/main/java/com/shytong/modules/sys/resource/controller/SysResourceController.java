package com.shytong.modules.sys.resource.controller;
import com.shytong.common.model.SyMap;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.sys.resource.service.ISysResourceService;
import javax.annotation.Resource;
import com.shytong.modules.sys.resource.model.SysResourceDo;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletRequest;
import com.shytong.common.exception.ApiBizException;
import java.util.Map;
import com.shytong.common.web.BaseController;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping( value = "/api/sysReource", produces = "application/json;charset=UTF-8;",  consumes = "application/json;")
public class SysResourceController extends BaseController {


    @Resource(name="sysResourceService")
    private ISysResourceService sysResourceService;
    @Autowired
    private BaseDao baseDao;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public  String add(HttpServletRequest servletRequest,@RequestBody SysResourceDo sysResourceDo)throws ApiBizException{
        
        SyValidationUtils.valid()
            .len(sysResourceDo.getOrgId(),32,true,"机构id格式错误")
            .len(sysResourceDo.getCode(),32,true,"编码格式错误")
            .len(sysResourceDo.getTypeCode(),32,true,"识别类型编码格式错误")
            .len(sysResourceDo.getType(),2,true,"资源类型格式错误")
            .len(sysResourceDo.getName(),256,true,"资源名称格式错误")
            .len(sysResourceDo.getDescription(),256,false,"介绍格式错误")
            .len(sysResourceDo.getParam(),2048,false,"参数配置格式错误")
            .len(sysResourceDo.getInfo(),2048,false,"信息格式错误");
        sysResourceService.insert(sysResourceDo);
        return this.normalResp();





        }
    @RequestMapping(value = "/delete",consumes="*",method = RequestMethod.GET)
    public  String delete(HttpServletRequest servletRequest,@RequestParam Map queryParamMap)throws ApiBizException{
        SyMap queryParams = new SyMap( queryParamMap );
        SyValidationUtils.valid()
            .len(queryParams.get("id"),32,true,"资源id格式错误");
        sysResourceService.deleteById(queryParams.getString("id"));
        return this.normalResp();





        }
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public  String list(HttpServletRequest servletRequest,@RequestBody SyMap params)throws ApiBizException{
        
        SyValidationUtils.valid()
            .isInt(params.get("pageNum"),true,"页码格式错误")
            .isInt(params.get("pageSize"),true,"每页条数格式错误");
        PageInfo pageInfo = sysResourceService.listOfPage(params,params.getInteger("pageNum"),params.getInteger("pageSize"));
        return this.normalRespPage( pageInfo );





        }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public  String edit(HttpServletRequest servletRequest,@RequestBody SysResourceDo sysResourceDo)throws ApiBizException{
        
        SyValidationUtils.valid()
            .len(sysResourceDo.getId(),32,true,"资源id格式错误")
            .len(sysResourceDo.getOrgId(),32,true,"机构id格式错误")
            .len(sysResourceDo.getCode(),32,true,"编码格式错误")
            .len(sysResourceDo.getTypeCode(),32,true,"识别类型编码格式错误")
            .len(sysResourceDo.getType(),2,true,"资源类型格式错误")
            .len(sysResourceDo.getName(),256,true,"资源名称格式错误")
            .len(sysResourceDo.getDescription(),256,false,"介绍格式错误")
            .len(sysResourceDo.getParam(),2048,false,"参数配置格式错误")
            .len(sysResourceDo.getInfo(),2048,false,"信息格式错误");
        sysResourceService.updateById(sysResourceDo);
        return this.normalResp();





        }
    @RequestMapping(value = "/detail",consumes="*",method = RequestMethod.GET)
    public  String detail(HttpServletRequest servletRequest,@RequestParam Map queryParamMap)throws ApiBizException{
        SyMap queryParams = new SyMap( queryParamMap );
        SyValidationUtils.valid()
            .len(queryParams.get("id"),32,true,"资源id格式错误");
        SysResourceDo sysResourceDo = sysResourceService.getById(queryParams.getString("id"));
        return this.normalResp( sysResourceDo );





        }
}

