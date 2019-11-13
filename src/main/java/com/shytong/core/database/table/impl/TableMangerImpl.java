package com.shytong.core.database.table.impl;

import com.shytong.core.database.table.ITableManger;
import com.shytong.core.database.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sytong
 * @Package com.shytong.core.database.table.impl
 * @Description:
 * @date 2017-12-2716:08
 */

@Service("tableMangerService")
public class TableMangerImpl implements ITableManger {
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;
@Autowired
    private JdbcTemplate jdbcTemplate;

    private ConcurrentHashMap<String, Table> tables=new ConcurrentHashMap<>();
    @Override
    public void init() {

    }

    @Override
    public List<Column> getColumns(String tableName)  {

        Table t=tables.get(tableName);
        if(t==null){
            synchronized (this){
                try {
                    t =this.getTableInfo(tableName);
                } catch (SQLException e) {
                    return  null;
                }
            }
        }
        if(t==null){
            return  null;
        }
        return t.getColumns();
    }

    @Override
    public Table getTable(String tableName) {


        Table t=tables.get(tableName);
        if(t==null){ synchronized (this){
                try {
                    t =this.getTableInfo(tableName);
                } catch (SQLException e) {
                    return  null;
                }
            }
        }
        if(t==null){
            return  null;
        }

        return  t;
    }

    private Table sss(String tableName) throws SQLException{
        DatabaseMetaData d=dataSource.getConnection().getMetaData();
        ResultSet  tableRet=d.getTables(null,null,null,null);

        while (tableRet.next()){
           // System.out.println(tableRet.getString("TABLE_NAME"));
        }
        ResultSet  s1=d.getColumns(null,null,tableName,null);
        while (s1.next()){
            for(int i=1;i<= s1.getMetaData().getColumnCount();i++){
               // System.out.println(s1.getMetaData().getColumnName(i)+":"+s1.getString(s1.getMetaData().getColumnName(i)));
            }
            //System.out.println(tableRet.getString("COLUMN_DEF"));
        }

        return  null;

    }

    private Table getTableInfo(String tableName) throws SQLException {

        StringBuilder b=new StringBuilder();


        b.append("select * from ");
        b.append(tableName);
        b.append(" where 1=2");
        SqlRowSet srs = jdbcTemplate.queryForRowSet(b.toString());



         if(srs!=null){

             SqlRowSetMetaData s=srs.getMetaData();
             Table t=new Table(s.getColumnCount());
             List<Column> l=new ArrayList<>(s.getColumnCount());
             for(int i=1;i<= s.getColumnCount();i++){
//                 System.out.println(s.getColumnName(i));
//                 System.out.println(s.getColumnLabel(i));
//                 System.out.println(s.getColumnTypeName(i));
//                 System.out.println(s.getColumnType(i));
//                 System.out.println(s.getColumnDisplaySize(i));
//                 System.out.println(s.getSchemaName(i));
//                 System.out.println(s.getCatalogName(i));
//                 System.out.println(s.getColumnClassName(i));
//                 System.out.println(s.getPrecision(i));
//                 System.out.println(s.getScale(i));
//                 System.out.println("-----------------------------");


                 t.setColumn(new Column(s.getColumnName(i),s.getColumnType(i),s.getColumnTypeName(i),s.getPrecision(i),s.getScale(i),s.getColumnClassName(i)));

             }



             return  t;
         }

        return  null;

    }
//    private String getDateClassType(int dateType,boolean isUnsigned){
//
//        switch(dateType) {
//            case -7:
//            case 16:
//                return "java.lang.Boolean";
//            case -6:
//                if (isUnsigned) {
//                    return "java.lang.Integer";
//                }
//
//                return "java.lang.Integer";
//            case -5:
//                if (!isUnsigned) {
//                    return "java.lang.Long";
//                }
//
//                return "java.math.BigInteger";
//            case -4:
//            case -3:
//            case -2:
//
//
//                    return "java.lang.String";
//
//            case -1:
//            case 1:
//            case 12:
//
//                    return "java.lang.String";
//
//
//
//            case 2:
//            case 3:
//                return "java.math.BigDecimal";
//            case 4:
//                if (isUnsigned ) {
//                    return "java.lang.Long";
//                }
//
//                return "java.lang.Integer";
//            case 5:
//                if (isUnsigned) {
//                    return "java.lang.Integer";
//                }
//
//                return "java.lang.Integer";
//            case 6:
//            case 8:
//                return "java.lang.Double";
//            case 7:
//                return "java.lang.Float";
//            case 91:
//                return "java.sql.Date";
//            case 92:
//                return "java.sql.Time";
//            case 93:
//                return "java.sql.Timestamp";
//            default:
//                return "java.lang.Object";
//        }
//
//    }

//    private Stirng getDateClassType(int dateType,boolean isUnsigned)

//    public String getColumnTypeName(int column) throws SQLException {
//        Field field = this.getField(column);
//        int mysqlType = field.getMysqlType();
//        int jdbcType = field.getSQLType();
//        switch(mysqlType) {
//            case 0:
//            case 246:
//                return field.isUnsigned() ? "DECIMAL UNSIGNED" : "DECIMAL";
//            case 1:
//                return field.isUnsigned() ? "TINYINT UNSIGNED" : "TINYINT";
//            case 2:
//                return field.isUnsigned() ? "SMALLINT UNSIGNED" : "SMALLINT";
//            case 3:
//                return field.isUnsigned() ? "INT UNSIGNED" : "INT";
//            case 4:
//                return field.isUnsigned() ? "FLOAT UNSIGNED" : "FLOAT";
//            case 5:
//                return field.isUnsigned() ? "DOUBLE UNSIGNED" : "DOUBLE";
//            case 6:
//                return "NULL";
//            case 7:
//                return "TIMESTAMP";
//            case 8:
//                return field.isUnsigned() ? "BIGINT UNSIGNED" : "BIGINT";
//            case 9:
//                return field.isUnsigned() ? "MEDIUMINT UNSIGNED" : "MEDIUMINT";
//            case 10:
//                return "DATE";
//            case 11:
//                return "TIME";
//            case 12:
//                return "DATETIME";
//            case 13:
//                return "YEAR";
//            case 15:
//                return "VARCHAR";
//            case 16:
//                return "BIT";
//            case 245:
//                return "JSON";
//            case 247:
//                return "ENUM";
//            case 248:
//                return "SET";
//            case 249:
//                return "TINYBLOB";
//            case 250:
//                return "MEDIUMBLOB";
//            case 251:
//                return "LONGBLOB";
//            case 252:
//                if (this.getField(column).isBinary()) {
//                    return "BLOB";
//                }
//
//                return "TEXT";
//            case 253:
//                if (jdbcType == -3) {
//                    return "VARBINARY";
//                }
//
//                return "VARCHAR";
//            case 254:
//                if (jdbcType == -2) {
//                    return "BINARY";
//                }
//
//                return "CHAR";
//            case 255:
//                return "GEOMETRY";
//            default:
//                return "UNKNOWN";
//        }
//    }
}
