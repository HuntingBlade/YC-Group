package com.shytong.core.util;

import org.apache.ibatis.reflection.MetaObject;

/**
 * @author sytong
 * @Package com.shytong.core.util
 * @Description:
 * @date 2018-04-1910:17
 */
public class SyBean {

    private MetaObject metaObject;


    public SyBean(MetaObject metaObject) {
        this.metaObject=metaObject;
    }

    public void setValue(String name,Object value){

        if(metaObject.hasSetter(name)){

            metaObject.setValue(name,value);

        }


    }


    public Object getValue(String name){


        if(metaObject.hasGetter(name)){

         return    metaObject.getValue(name);

        }
        return null;
    }
    public String getStringValue(String name){

        if(metaObject.hasGetter(name)){

            Object o=     metaObject.getValue(name);

            if(o!=null){

                return  o.toString();
            }

        }
        return null;
    }

}
