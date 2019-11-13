package com.shytong.core.database;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.core.database.plugins
 * @Description:
 * @date 2017-12-2711:22
 */
@Mapper
public interface IIbatisDao {

    @SelectProvider(type = SqlDao.class, method = "buildSql")
    public List<Map<String,Object>> selctList(Object params);
    @InsertProvider(type = SqlDao.class, method = "buildSql")
    public int insert(Object params);
    @DeleteProvider(type = SqlDao.class, method = "buildSql")
    public int del(Object params);
    @UpdateProvider(type = SqlDao.class, method = "buildSql")
    public int update(Object params);

}
