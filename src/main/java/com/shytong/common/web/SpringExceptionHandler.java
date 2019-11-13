package com.shytong.common.web;

import com.shytong.common.exception.ApiBizException;
import com.shytong.common.exception.ApiBizRunException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class SpringExceptionHandler {
	protected ResEntity defaultRes=new ResEntity(99, "系统异常");
	protected Logger logger = LoggerFactory
			.getLogger(SpringExceptionHandler.class);
  /** 
     * 全局处理Exception 
     * 错误的情况下返回500 
     * @param ex 
     * @param
     * @return 
     */  
    @ExceptionHandler(value = {Exception.class})
    public @ResponseBody
    ResEntity handleOtherExceptions(final Exception ex, HttpServletResponse
			response) throws Exception {
		logger.error("99", ex);
		response.setHeader("Content-Type","application/json");
    	if(ex.getClass().getName().indexOf("MethodArgumentNotValidException")!=-1){
    	  	MethodArgumentNotValidException e=(MethodArgumentNotValidException)ex;

        	BindingResult result=	e.getBindingResult();

       	 if (result.hasErrors()) {
			 return  new ResEntity(98,  result.getAllErrors().get(0).getDefaultMessage());
		 }

        	 return  defaultRes;

    	}


		return  defaultRes;
    }
	@ExceptionHandler(value = {ApiBizException.class})
	public @ResponseBody
    ResEntity handleOtherExceptions(final ApiBizException ex) throws Exception {

		ex.printStackTrace();
		return new ResEntity(ex.getErrCode(),ex.getErrInfo());

	}
	@ExceptionHandler(value = {ApiBizRunException.class})
	public @ResponseBody
    ResEntity handleApiBizRunExceptions(final ApiBizRunException ex) throws Exception {

		ex.printStackTrace();
		return new ResEntity(ex.getErrCode(),ex.getErrInfo());

	}
}  