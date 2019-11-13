package com.shytong.core.util;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.lang.reflect.Field;

/**
 * @author sytong
 * @Package com.shytong.core.util
 * @Description:
 * @date 2018-01-0423:42
 */
public class SyBeanUtil {
    private static class NullObject {
    }

    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(SyBeanUtil.NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
    public static final ReflectorFactory reflectorFactory=new DefaultReflectorFactory();


    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, reflectorFactory);
    }


    public static void main(String[] args) {





    }

    public static SyBean getSyBean(Object bean){

        return new SyBean(forObject(bean));

    }

    public static void copyBeanToOther( Object sourceBean,Object toBean){
        MetaObject sourceMeta= SyBeanUtil.forObject(sourceBean);
        MetaObject toMeta= SyBeanUtil.forObject(toBean);
        for(String name:toMeta.getSetterNames()){
             if(sourceMeta.hasGetter(name)){
                 toMeta.setValue(name,sourceMeta.getValue(name));
             }
         } }
    public  static Object getValue(Object bean,String field) {

        try {
            Field f = bean.getClass().getDeclaredField(field);
            f.setAccessible(true);

            return f.get(bean);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
      return  null;

    }

}
