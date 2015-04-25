package com.tripoin.rmu.persistence.orm_persistence.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tripoin.rmu.model.persist.SelfTestModel;
import com.tripoin.rmu.persistence.enumeration.DatabaseConstant;

import java.sql.SQLException;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class DatabaseDAOHelper extends OrmLiteSqliteOpenHelper{

    private Dao<SelfTestModel, Integer> selfTestDAO = null;

    public DatabaseDAOHelper(Context ctx) {
        super( ctx, DatabaseConstant.DB_NAME.toString(), null, Integer.parseInt( DatabaseConstant.DB_VERSION.toString() ) );
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.d("CONNECTIONSOURCE", connectionSource.toString());
            TableUtils.createTable(connectionSource, SelfTestModel.class);
            /*Add here to add some connections*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, SelfTestModel.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<SelfTestModel, Integer> getSelfTestDAO() {
        if( selfTestDAO == null ){
            try {
                selfTestDAO = getDao(SelfTestModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return selfTestDAO;
    }
}
