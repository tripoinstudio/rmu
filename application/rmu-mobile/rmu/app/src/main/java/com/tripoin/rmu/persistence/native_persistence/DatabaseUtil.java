package com.tripoin.rmu.persistence.native_persistence;

import android.util.Log;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Achmad Fauzi on 12/22/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Works to generate table sql statement
 */
public class DatabaseUtil{

    public static final String CREATE_TABLE_STATEMENT = " create table ";

    // table property
    private static final String NOT_NULL_PROPERTY = " not null ";
    private static final String PRIMARY_KEY_PRPERTY = " primary key ";
    private static final String AUTO_INCREMENT_PROPERTY = " autoincrement ";


    public static String generateCreateTableSqlStatement(Table table){
        StringBuffer sql = new StringBuffer();

        List<Column> cols = table.getColumns();

        sql.append(CREATE_TABLE_STATEMENT + " ").append(table.getName());
        sql.append(" ( ");

        Iterator<Column> its = cols.listIterator();

        while (its.hasNext()) {
            Column col = (Column) its.next();
            sql.append(" ");
            sql.append(col.getName());
            sql.append(" ");
            sql.append(col.getDataType().toString());
            sql.append(" ");

            if(col.isPrimaryKey()){
                sql.append(PRIMARY_KEY_PRPERTY);
                sql.append(" ");
            }

            if(col.isNotNull()){
                sql.append(NOT_NULL_PROPERTY);
                sql.append(" ");
            }

            if( col.isAutoIncrement() ){
                sql.append(AUTO_INCREMENT_PROPERTY);
                sql.append(" ");
            }

            if(its.hasNext()){
                sql.append(" , ");
            }
        }
        sql.append(" ); ");
        Log.d("CREATE TABLE STATEMENT", sql.toString());
        return sql.toString();
    }

}
