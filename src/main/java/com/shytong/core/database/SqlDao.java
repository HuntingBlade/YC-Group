package com.shytong.core.database;

import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.core.database
 * @Description:
 * @date 2017-12-2712:59
 */
public class SqlDao {
    public String buildSql(Map<String,Object> m ) {

        return m.get("sql").toString();
    }


}
