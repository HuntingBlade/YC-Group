package com.shytong.core.util;


import com.shytong.common.exception.ApiBizException;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


public class SyValidationUtils {

	private static Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    private static SyValiddation syValiddation=new SyValiddation();

	public static SyValiddation valid(){

		return syValiddation;
	}
	
	public static <T> void  validateProperty(T obj,boolean require,String... propertyName) throws ApiBizException {
		
		for (String key : propertyName) {
			if(!require&& SyStringUtils.isBlank(key)){
				
				continue;
				
			}
			
			 Set<ConstraintViolation<T>> set = validator.validateProperty(obj,key, Default.class);
			
			 if( !CollectionUtils.isEmpty(set) ){
			
				 Map<String,String> errorMsg = new HashMap<String,String>();
				 for(ConstraintViolation<T> cv : set){
					 
					 throw new ApiBizException(-1, cv.getMessage());
					 
					 
				 }
				 
			 }
			
		}
		
	
		
//		
////		validator.
//		
//		
//		ValidationResult result = new ValidationResult();
//		 Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName,Default.class);
//		 if( !CollectionUtils.isEmpty(set) ){
//			 result.setHasErrors(true);
//			 Map<String,String> errorMsg = new HashMap<String,String>();
//			 for(ConstraintViolation<T> cv : set){
//				 errorMsg.put(propertyName, cv.getMessage());
//			 }
//			 result.setErrorMsg(errorMsg);
//		 }
//		 return result;
	}
	
	
	public static <T> ValidationResult validateEntity(T obj) throws ApiBizException {
		ValidationResult result = new ValidationResult();
		 Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		 if( !CollectionUtils.isEmpty(set) ){
			 result.setHasErrors(true);
			 Map<String,String> errorMsg = new HashMap<String,String>();
			 for(ConstraintViolation<T> cv : set){
				 throw new ApiBizException(-1, cv.getMessage());
//				 errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			 }
			 result.setErrorMsg(errorMsg);
		 }
		 return result;
	}
	
	
	
	
	
	public static <T> ValidationResult validateProperty(T obj, String propertyName){
		
		
//		validator.
		
		
		
		
		ValidationResult result = new ValidationResult();
		 Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName, Default.class);
		 if( !CollectionUtils.isEmpty(set) ){
			 result.setHasErrors(true);
			 Map<String,String> errorMsg = new HashMap<String,String>();
			 for(ConstraintViolation<T> cv : set){
				 errorMsg.put(propertyName, cv.getMessage());
			 }
			 result.setErrorMsg(errorMsg);
		 }
		 return result;
	}



	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^1\\d{10}$";

	public static String isNotBlank(String value, String filedName) {
		if(SyStringUtils.isNotBlank(value)){
			return  null;
		}
		return filedName+"不能为空";
	}


	private static String checkPhone(String value, String filedName,boolean isRequired){
		if(SyStringUtils.isBlank(value)){

			if(isRequired){
				return filedName+"不能为空";
			}
			return null;
		}
		if(value.length()!=11){
			return filedName+"格式错误";
		}

		if(!Pattern.matches(REGEX_MOBILE,value)){

			return filedName+"格式错误";
		}
		return null;


	}

	private static String checkIn(String value, String filedName,boolean isRequired,String... values){
		if(SyStringUtils.isBlank(value)){

			if(isRequired){
				return filedName+"不能为空";
			}
			return null;
		}
		if(values.length==0){
			return filedName+"格式错误";
		}
		for (String str:
				values) {
			if(value.equals(str)){
				return null;
			}

		}
		return filedName+"格式错误";


	}

	public static String isPhone(String value, String filedName) {


		return checkPhone(value,filedName,false );
	}


	public static String isPhoneRequired(String value, String filedName) {

		return checkPhone(value,filedName,true );
	}



	public static String isLenth(String value, String filedName,int min,int max){



	 if(value==null){

	 	if(min==max){
			return filedName+"长度错误，必须"+min+"位";
		}
	 	return filedName+"长度错误，必须在"+min+","+max+"之间";
	 }
	 int len=value.length();
	 if(min<=len&&max>=len){

	 	return null;
	 }
		if(min==max){
			return filedName+"长度错误，必须"+min+"位";
		}
		return filedName+"长度错误，必须在"+min+","+max+"之间";


	}
	public static String isIn(String value, String filedName, String... values) {


		return checkIn(value,filedName,false,values);
	}



	public static String isInRequired(String value, String filedName, String... values) {


		return checkIn(value,filedName,true,values);
	}
	public static String isPattern(String value,String regex,String message){

		if(	Pattern.matches(regex,value)){
			return null;
		}
		return message;

	}
	public static void checkNull(String str) throws ApiBizException {

		if(str==null){
			throw new ApiBizException(-1,str);
		}

	}

	public static void checkVerifyCode(String type,String verifyCode) throws ApiBizException {
		HttpServletRequest request=SyRequestUtil.getCurrentRequest();
		if(request==null){
			throw new ApiBizException(-1,"系统异常");
		}
		if(!VerifyCodeUtils.checkVerifyCode(request.getSession()
				,type,verifyCode)) {

			throw new ApiBizException(-1,"图形验证码错误");
		};
	}
	
	
}