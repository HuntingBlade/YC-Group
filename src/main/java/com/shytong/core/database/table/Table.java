package com.shytong.core.database.table;

import com.shytong.core.database.table.impl.Column;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author sytong
 * @Package com.shytong.core.database.table
 * @Description:
 * @date 2017-12-2716:19
 */
public class Table {

    private  String tableName;
    private List<Column> columns;

    private HashMap<String,Column> columnsMap;

    public HashMap<String, Column> getColumnsMap() {
        return columnsMap;
    }

    public void setColumnsMap(HashMap<String, Column> columnsMap) {
        this.columnsMap = columnsMap;
    }

    public Table(int size){
       this.columns=new ArrayList<>(size);
       this.columnsMap=new HashMap<>(size);
   }
    public  void setColumn(Column column){
        columns.add(column);
        columnsMap.put(column.getCode().toLowerCase(),column);



    }

    public List<Column> getColumns() {
        return columns;
    }



    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }




}
