package com.shytong.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author sytong
 * @Package com.shytong.core.util
 * @Description:
 * @date 2018-01-0418:29
 */
public class SyJsonUtil {

    public  static ObjectMapper mapper = new ObjectMapper();
    static{

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
                arg1.writeString("");
            }
        });
//        mapper.getSerializerProvider().set
//        mapper.setD
    }

    public static  String Object2Json(Object o)  {
        try {
            return   mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {

         e.printStackTrace();

        }
        return  null;
    }
}
