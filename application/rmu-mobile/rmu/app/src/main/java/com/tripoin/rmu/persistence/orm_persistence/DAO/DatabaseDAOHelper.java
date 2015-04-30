package com.tripoin.rmu.persistence.orm_persistence.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.SelfTestModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.enumeration.DatabaseConstant;

import java.sql.SQLException;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class DatabaseDAOHelper extends OrmLiteSqliteOpenHelper{

    private Dao<SelfTestModel, Integer> selfTestDAO = null;
    private Dao<VersionModel, Integer> versionDAO = null;
    private Dao<MenuModel, Integer> menuDAO = null;

    public DatabaseDAOHelper(Context ctx) {
        super( ctx, DatabaseConstant.DB_NAME.toString(), null, Integer.parseInt( DatabaseConstant.DB_VERSION.toString() ) );
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.d("CONNECTIONSOURCE", connectionSource.toString());
            TableUtils.createTable(connectionSource, SelfTestModel.class);
            TableUtils.createTable(connectionSource, VersionModel.class);
            TableUtils.createTable(connectionSource, MenuModel.class);
            /*Add here to add some connections*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, SelfTestModel.class, true);
            TableUtils.dropTable(connectionSource, VersionModel.class, true);
            TableUtils.dropTable(connectionSource, MenuModel.class, true);
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

    public Dao<MenuModel, Integer> getMenuDAO(){
        if( menuDAO == null ){
            try {
                menuDAO = getDao(MenuModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return menuDAO;
    }
}
