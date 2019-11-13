package com.shytong.core.database.table;

import com.shytong.core.database.table.impl.Column;

import java.util.List;

/**
 * @author sytong
 * @Package com.shytong.core.database.table
 * @Description:
 * @date 2017-12-2716:07
 */

public interface ITableManger {

    public  void  init();
    public List<Column> getColumns(String tableName) ;
    public Table getTable(String tableName) ;

}
