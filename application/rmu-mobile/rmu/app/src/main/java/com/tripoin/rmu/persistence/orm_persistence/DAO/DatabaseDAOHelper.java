package com.tripoin.rmu.persistence.orm_persistence.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.model.persist.SelfTestModel;
import com.tripoin.rmu.model.persist.TrainModel;
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
    private Dao<OrderListModel, Integer> orderListDAO = null;
    private Dao<CarriageModel, Integer> carriageDAO = null;
    private Dao<SeatModel, Integer> seatDAO = null;
    private Dao<TrainModel, Integer> trainDAO = null;

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
            TableUtils.createTable(connectionSource, OrderListModel.class);
            /*Add here to add some connections*/
            TableUtils.createTable(connectionSource, CarriageModel.class);
            TableUtils.createTable(connectionSource, SeatModel.class);
            TableUtils.createTable(connectionSource, TrainModel.class);
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
            TableUtils.dropTable(connectionSource, OrderListModel.class, true);
            TableUtils.dropTable(connectionSource, CarriageModel.class, true);
            TableUtils.dropTable(connectionSource, SeatModel.class, true);
            TableUtils.dropTable(connectionSource, TrainModel.class, true);
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

    public Dao<OrderListModel, Integer> getOrderListDAO(){
        if( orderListDAO == null ){
            try {
                orderListDAO = getDao(OrderListModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderListDAO;
    }

    public Dao<CarriageModel, Integer> getCarriageDAO() {
        if( carriageDAO == null ){
            try {
                carriageDAO = getDao(CarriageModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return carriageDAO;
    }

    public Dao<SeatModel, Integer> getSeatDAO() {
        if( seatDAO == null ){
            try {
                seatDAO = getDao(SeatModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return seatDAO;
    }

    public Dao<TrainModel, Integer> getTrainDAO() {
        if( trainDAO == null ){
            try {
                trainDAO = getDao(TrainModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return trainDAO;
    }
}
