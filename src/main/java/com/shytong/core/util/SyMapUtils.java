package com.shytong.core.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.core.util
 * @Description:
 * @date 2018-01-1719:34
 */
public class SyMapUtils  {



    public static Map<String,String> toMap(String keys,String... values){

        if(SyStringUtils.isBlank(keys)){
            return null;
        }
        Map<String,String> m=new HashMap<>();
        String[] keyArray=keys.split(",");
        int l=values.length;
        for (int i=0 ;i<keyArray.length;i++) {

            m.put(keyArray[i],i<l?values[i]:null);

        }



        return m;
    }

    public static void main(String[] args) {

        String[] l="m,s,sf".split(",");
        List<String> s= Arrays.asList(l);
//        s.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                Sys
//            }
//        });


    }
}

