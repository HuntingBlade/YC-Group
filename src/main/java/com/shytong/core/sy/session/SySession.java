package com.shytong.core.sy.session;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.core.sy.session
 * @Description:
 * @date 2018-04-1613:29
 */
public class SySession {

    public Map<String,Object> data;

    public SySession(String id,Map data){

        this.id=id;
        this.data=data;
    }

    private String id;

    public JSONObject getJsonObject(String key){


        if(data.get(key)!=null){


            return  JSONObject.parseObject(data.get(key).toString());
        }

        return null;
    }
    public JSONArray getJsonArray(String key){


        if(data.get(key)!=null){


            return  JSONArray.parseArray(data.get(key).toString());
        }

        return null;
    }

    public   <T> T getObject(String key,Class<T> tClass){
        if(data.get(key)!=null){


            return JSONObject.parseObject(data.get(key).toString(),tClass);

        }
        return null;
    }

    public String getValueOfString(String key){

        if(data.get(key)!=null){
            return  data.get(key).toString();
        }
        return null;

    }

    public void setValue(String key, Object value){

        data.put(key,value);


    }

    public void save(){

        for(String key:data.keySet()){

            if(!(data.get(key) instanceof  String)){

                data.put(key,JSONObject.toJSONString(data.get(key)));
            }


        }

        SySessionUtil.hmSet(this.id,this.data);
    }

    public void removeKey(String ...key){

        for(String k:key){

            this.data.remove(k);

        }


        SySessionUtil.del(this.id,key);



    }






}
