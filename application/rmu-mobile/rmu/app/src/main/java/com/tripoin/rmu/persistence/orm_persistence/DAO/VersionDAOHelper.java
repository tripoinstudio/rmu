package com.tripoin.rmu.persistence.orm_persistence.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.enumeration.DatabaseConstant;

import java.sql.SQLException;

/**
 * Created by HP M4 on 4/28/2015.
 */
public class VersionDAOHelper extends OrmLiteSqliteOpenHelper {

    private Dao<VersionModel, Integer> versionDAO = null;

    public VersionDAOHelper(Context context) {
        super( context, DatabaseConstant.DB_NAME.toString(), null, Integer.parseInt( DatabaseConstant.DB_VERSION.toString() ) );
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.d("CONNECTIONSOURCE", connectionSource.toString());
            TableUtils.createTable(connectionSource, VersionModel.class);
            /*Add here to add some connections*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, VersionModel.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<VersionModel, Integer> getVersionDAO(){
        if( versionDAO == null ){
            try {
                versionDAO = getDao(VersionModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return versionDAO;
    }
}
