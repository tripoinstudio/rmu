package com.tripoin.rmu.persistence.orm_persistence.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.persistence.enumeration.DatabaseConstant;

import java.sql.SQLException;

/**
 * Created by HP M4 on 4/28/2015.
 */
public class MenuDAOHelper extends OrmLiteSqliteOpenHelper {

    private Dao<MenuModel, Integer> menuDAO = null;

    public MenuDAOHelper(Context context) {
        super( context, DatabaseConstant.DB_NAME.toString(), null, Integer.parseInt( DatabaseConstant.DB_VERSION.toString() ) );
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.d("CONNECTIONSOURCE", connectionSource.toString());
            TableUtils.createTable(connectionSource, MenuModel.class);
            /*Add here to add some connections*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, MenuModel.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
