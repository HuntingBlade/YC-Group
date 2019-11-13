package com.shytong.modules.sys.org.controller;
import com.shytong.common.model.SyMap;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.core.database.BaseDao;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;
import com.shytong.modules.sys.org.model.OrgDo;
import javax.servlet.http.HttpServletRequest;
import com.shytong.common.exception.ApiBizException;
import java.util.Map;
import com.shytong.common.web.BaseController;
import org.springframework.web.bind.annotation.*;
import com.shytong.modules.sys.org.service.IOrgService;
@RestController
@RequestMapping( value = "/api/org", produces = "application/json;charset=UTF-8;",  consumes = "application/json;")
public class OrgController extends BaseController {


    @Resource(name="orgService")
    private IOrgService orgService;
    @Autowired
    private BaseDao baseDao;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public  String add(HttpServletRequest servletRequest,@RequestBody OrgDo orgDo)throws ApiBizException{
        
        SyValidationUtils.valid()
            .len(orgDo.getName(),32,true,"名称格式错误")
            .len(orgDo.getCode(),256,false,"机构编码格式错误")
            .len(orgDo.getParentId(),32,false,"父id格式错误");
        orgService.insert(orgDo);
        return this.normalResp();





        }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public  String edit(HttpServletRequest servletRequest,@RequestBody OrgDo orgDo)throws ApiBizException{
        
        SyValidationUtils.valid()
            .len(orgDo.getId(),32,false,"id格式错误")
            .len(orgDo.getName(),32,false,"名称格式错误")
            .len(orgDo.getCode(),256,false,"机构编码格式错误")
            .len(orgDo.getParentId(),32,true,"父id格式错误");
        orgService.updateById(orgDo);
        return this.normalResp();





        }
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public  String list(HttpServletRequest servletRequest,@RequestBody SyMap params)throws ApiBizException{
        
        SyValidationUtils.valid()
            .isInt(params.get("pageNum"),true,"页码格式错误")
            .isInt(params.get("pageSize"),true,"每页条数格式错误");
        PageInfo pageInfo = orgService.listOfPage(params,params.getInteger("pageNum"),params.getInteger("pageSize"));
        return this.normalRespPage( pageInfo );





        }
    @RequestMapping(value = "/detail",consumes="*",method = RequestMethod.GET)
    public  String detail(HttpServletRequest servletRequest,@RequestParam Map queryParamMap)throws ApiBizException{
        SyMap queryParams = new SyMap( queryParamMap );
        SyValidationUtils.valid()
            .len(queryParams.get("id"),32,false,"id格式错误");
        OrgDo orgDo = orgService.getById(queryParams.getString("id"));
        return this.normalResp( orgDo );





        }
    @RequestMapping(value = "/delete",consumes="*",method = RequestMethod.GET)
    public  String delete(HttpServletRequest servletRequest,@RequestParam Map queryParamMap)throws ApiBizException{
        SyMap queryParams = new SyMap( queryParamMap );
        SyValidationUtils.valid()
            .len(queryParams.get("id"),32,false,"id格式错误");
        orgService.deleteById(queryParams.getString("id"));
        return this.normalResp();





        }
}

