package com.shytong.core.database.table.impl;

import com.shytong.core.util.SyStringUtils;

/**
 * @author sytong
 * @Package com.shytong.core.database.table.impl
 * @Description:
 * @date 2018-01-0214:36
 */
//
//System.out.println(s.getColumnName(i));
//        System.out.println(s.getColumnLabel(i));
//        System.out.println(s.getColumnTypeName(i));
//        System.out.println(s.getColumnType(i));
//        System.out.println(s.getColumnDisplaySize(i));
//        System.out.println(s.getSchemaName(i));
//        System.out.println(s.getCatalogName(i));
//        System.out.println(s.getColumnClassName(i));
//        System.out.println(s.getPrecision(i));
//        System.out.println(s.getScale(i));


public class Column {
    private String code;
    private int dataType;
    private  String dateTypeName;

    private int length;
    private int scale;
    private  String className;
    private String field;
    private  boolean unsigned;
    private  String mbFile;
    public Column(String code, int dataType,String dateTypeName, int length, int scale, String className) {
        this.code = code;
        this.dataType = dataType;
        this.dateTypeName=getDataTypeName(dataType,dateTypeName);
        this.length = length;
        this.scale = scale;
        this.className=className;
        this.field= SyStringUtils.unline2Hump(code);
        this.mbFile=mbFile(this.field,this.dateTypeName);
    }
        private String getDataTypeName(int dateType,String dateTypeName){

        if(dateType==93){
            return "TIMESTAMP";
        }



    return dateTypeName.replace("UNSIGNED","").replaceAll(" ","");

    }

    private String mbFile(String field,String dataTypeName){
        StringBuilder a=new StringBuilder();


        if(dateTypeName.equals("INT")){


            dateTypeName="INTEGER";
        }

        return  a.append("#{").append(field).append(",jdbcType=").append(dateTypeName).append("}").toString();

    }

    public String getField() {
        return field;
    }


    public void setField(String field) {
        this.field = field;
    }

    public String getDateTypeName() {
        return dateTypeName;
    }

    public void setDateTypeName(String dateTypeName) {
        this.dateTypeName = dateTypeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
