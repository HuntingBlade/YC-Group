package com.shytong.core.util;

import com.shytong.common.exception.ApiBizException;
import com.shytong.core.constant.SyConstant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author sytong
 * @Package com.shytong.core.util
 * @Description:
 * @date 2018-03-2914:32
 */
public class SyValiddation {

    //整数
    public static String INT="^(0|[1-9][0-9]*|-[1-9][0-9]*)$";
    //大等于0
    public static String INT_EGT_ZERO="^(0|[1-9][0-9]*)$";
    //大等于0
    public static String INT_GT_ZERO="^([1-9][0-9]*)$";
    public static String NUMBER_ALL="^([1-9]\\d*|0(\\.\\d{1,})?$";
    public static String NUMBER="^([1-9]\\d{0,%d}|0)(\\.\\d{1,%d})?$";
    public static String NUMBER_NO_INT="^0(\\.\\d{1,%d})$";
    public static String NUMBER_NO_INT_SCALE="^(0)$";
    public static String NUMBER_NO_SCALE="(^([1-9]\\d{0,%d}|0)$";
    public static int maxint=1;
    public static String EMAIL="^[^@]+@[^@]+(\\.[^@]+)+$";
    public static String PHONE="^\\d{11}$";

    public static ConcurrentHashMap<String,Pattern> patterns=new ConcurrentHashMap<>();

    public static void main(String[] args) throws ApiBizException {

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate d=LocalDate.parse("2017-01-01",formatter);

        SyValiddation s=new SyValiddation();
        s.number("1.22",1,2,true,"ddd");



//        System.out.println(1 instanceof Number);
//        s.checkLength("11",1,1,"444");
    }

   public SyValiddation len(Object str, int length, boolean flag, String tip) throws ApiBizException {
      return   this.len(str,0,length,flag,tip);

    }


    public SyValiddation collection (Collection str, boolean flag, String tip) throws ApiBizException {

        if((str==null||str.isEmpty())&&flag){
            throw  new ApiBizException(SyConstant.ERR_VALID_NULL,tip);
        }
        return this;
    }
    public SyValiddation checkNull(Object str, boolean flag, String tip) throws ApiBizException {

        if(str==null&&flag){
            throw  new ApiBizException(SyConstant.ERR_VALID_NULL,tip);
        }
        return this;
    }
    public SyValiddation checkBlank(Object str, boolean flag, String tip) throws ApiBizException {

        if((SyStringUtils.isBlank(str))) {

            if (flag) {
                throw new ApiBizException(SyConstant.ERR_VALID_NULL, tip);
            }
        }
        return this;
    }

    public SyValiddation checkBlank(String str, String tip) throws ApiBizException {

        if((SyStringUtils.isBlank(str))) {


                throw new ApiBizException(SyConstant.ERR_VALID_NULL, tip);

        }
        return this;
    }
    public  boolean isBlank(Object str,boolean flag,String tip) throws ApiBizException {

        if((SyStringUtils.isBlank(str))){

            if(flag){
                throw  new ApiBizException(SyConstant.ERR_VALID_NULL,tip);
            }
            return  false;
        }
        return true;
    }
    public SyValiddation specify(Object str, String value, boolean flag, String tip) throws ApiBizException {

        if(isBlank(str,flag,tip)){
            String[]values=value.split(",");
            String s1=str.toString();
            boolean flag1=false;
               for(String s:values){

                   if (s1.equals(s)) {
                       flag1=true;
                       break;
                   } }
               if(!flag1){
                   throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
               }
        }
        return this;
    }
    public SyValiddation email(Object str, boolean flag, String tip) throws ApiBizException {

        if(isBlank(str,flag,tip)){

            this.regex(str.toString(),EMAIL,flag,tip);

        }
        return this;
    }
    public SyValiddation phone(Object str, boolean flag, String tip) throws ApiBizException {

        if(isBlank(str,flag,tip)){

            this.regex(str.toString(),PHONE,flag,tip);

        }
        return this;
    }
    public SyValiddation number(Object str, int maxint, int scale, boolean flag, String tip) throws ApiBizException {

        if(isBlank(str,flag,tip)){
        if(maxint==0&&scale==0){
            if(!"0".equals(str.toString())){
                throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
            }
        }else if(scale==0){
            this.regex(str.toString(),String.format(NUMBER_NO_SCALE,maxint-1),flag,tip);
            }else if(maxint==0){
            this.regex(str.toString(),String.format(NUMBER_NO_INT,scale),flag,tip);

        }else{


                this.regex(str.toString(),String.format(NUMBER,maxint-1,scale),flag,tip);
            }


        }
        return this;
    }

    public SyValiddation len(Object str, int min, int max, boolean flag, String tip) throws ApiBizException {

        if(isBlank(str,flag,tip)){

            if(str.toString().length()<min||str.toString().length()>max){
                throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
            }
        }
        return this;
    }

    public SyValiddation intEgtZero(Object str, int len, boolean flag, String tip) throws ApiBizException {
        if(isBlank(str,flag,tip)){
        if(str.toString().length()>len||!this.checkIsIntEgtZero(str)){

            throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
        }
        }
        return this;
    }
    public SyValiddation intGtZero(Object str, int len, boolean flag, String tip) throws ApiBizException {
        if(isBlank(str,flag,tip)){
         if(str.toString().length()>len||!this.checkIsIntGtZero(str)){
             throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
         }
        }
         return this;
        }
    public SyValiddation isInt(Object str, boolean flag, String tip) throws ApiBizException {
        if(isBlank(str,flag,tip)){
            if(!this.checkIsInt(str)){

                throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
            }}
        return this;
    }
    public SyValiddation isInt(Object str, int len, boolean flag, String tip) throws ApiBizException {
        if(isBlank(str,flag,tip)){
       if(str.toString().replace("-","").length()>len||!this.checkIsInt(str)){

           throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
       }}
        return this;
    }

    public SyValiddation isInt(Object str, int min, int max, boolean flag, String tip) throws ApiBizException {
        if(isBlank(str,flag,tip)){

            if(!this.checkIsInt(str)){

                throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
            }
                int n= SyTypeUtils.castToInt(str);
                if(n<min||n>max){
                    throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);
                }
        }
        return this;
    }

    public SyValiddation validDate(Object str, String format, boolean flag, String tip) throws ApiBizException {
        if(isBlank(str,flag,tip)){
        if(format==null){

            format="yyyy-MM-dd HH:mm:ss";
        }
        if(str==null||str.toString().length()!=format.length()){
            throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);

        }
        }
      return   this.regex( str.toString(), format, tip);

    }


    public   boolean   checkIsIntEgtZero(Object str){
         return this.regex(str.toString(),INT_EGT_ZERO);
        };
    public  boolean   checkIsIntGtZero(Object str){

        return this.regex(str.toString(),INT_GT_ZERO);
     };
    public      boolean   checkIsInt(Object str){

        return this.regex(str.toString(),INT);



        };
    public  boolean regex(String str,String regex)  {

        if(regex==null||regex.isEmpty()){
          return false;
        }
        Pattern pattern=patterns.get(regex);
        if(pattern==null){
            patterns.put(regex,Pattern.compile(regex));
        }
        pattern=patterns.get(regex);
       return pattern.matcher(str).matches();

    }
    public SyValiddation regex(Object str, String regex, boolean flag, String tip) throws ApiBizException {
        if(isBlank(str,flag,tip)){
        if(regex==null||regex.isEmpty()){
            throw  new ApiBizException(SyConstant.ERR_VALID,tip);
        }
        Pattern pattern=patterns.get(regex);
        if(pattern==null){
            patterns.put(regex,Pattern.compile(regex));
        }
        pattern=patterns.get(regex);
        if(!pattern.matcher(str.toString()).matches()){
            throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);

        }
        }
        return this;
    }
    public SyValiddation regex(String str, String regex, String tip) throws ApiBizException {

        if(regex==null||regex.isEmpty()){
            throw  new ApiBizException(SyConstant.ERR_VALID,tip);
        }
        Pattern pattern=patterns.get(regex);
        if(pattern==null){
            patterns.put(regex,Pattern.compile(regex));
        }
        pattern=patterns.get(regex);
        if(!pattern.matcher(str).matches()){
            throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);

        }
        return this;
    }
    public SyValiddation regexDynamic(String str, String regex, String tip) throws ApiBizException {

        if(regex==null||regex.isEmpty()){
            throw  new ApiBizException(SyConstant.ERR_VALID,tip);
        }
        Pattern pattern=Pattern.compile(regex);
        if(!pattern.matcher(str).matches()){
            throw  new ApiBizException(SyConstant.ERR_VALID_FORMAT,tip);

        }
        return this;
    }



}
