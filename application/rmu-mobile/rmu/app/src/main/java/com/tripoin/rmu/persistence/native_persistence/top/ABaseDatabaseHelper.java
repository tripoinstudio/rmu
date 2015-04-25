package com.tripoin.rmu.persistence.native_persistence.top;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by Achmad Fauzi on 12/22/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Works on Creation and Update for database Version
 */
public abstract class ABaseDatabaseHelper extends SQLiteOpenHelper{

    public ABaseDatabaseHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("ON CREATE ", "BASE DB HELPER");
        for( String sql : generateSqlCreate() ){
            db.execSQL( sql );
        }
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        for( String tableNames : getTableNames() ){
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + tableNames );
        }
        // Create tables again
        onCreate(db);
    }

    protected abstract List<String> generateSqlCreate();

    protected abstract List<String> getTableNames();
}
