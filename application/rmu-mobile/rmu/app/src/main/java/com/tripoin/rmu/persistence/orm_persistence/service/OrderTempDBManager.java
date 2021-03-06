package com.tripoin.rmu.persistence.orm_persistence.service;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.persistence.orm_persistence.DAO.DatabaseDAOHelper;
import com.tripoin.rmu.persistence.orm_persistence.api.IBaseDatabaseHandler;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 * @param <DATA>
 */
public class OrderTempDBManager<DATA> implements IBaseDatabaseHandler{

    static private OrderTempDBManager instance;

    static public void init( Context ctx ){
        if( instance == null ){
            instance = new OrderTempDBManager( ctx );
        }
    }

    static public OrderTempDBManager getInstance(){
        return instance;
    }

    private DatabaseDAOHelper databaseDAOHelper;

    public OrderTempDBManager(Context ctx) {
        databaseDAOHelper = new DatabaseDAOHelper(ctx);
    }

    public DatabaseDAOHelper getDatabaseDAOHelper() {
        return databaseDAOHelper;
    }

    @Override
    public int insertEntity(Object entity) {
        int result = 0;
        try {
            result = getDatabaseDAOHelper().getOrderTempDAO().create((OrderTempModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DATA> getAllData() {
        List<DATA> result = null;
        try {
            result = (List<DATA>) getDatabaseDAOHelper().getOrderTempDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateEntity(Object entity) {
        try {
            getDatabaseDAOHelper().getOrderTempDAO().update((OrderTempModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrUpdateEntity(Object entity) {
        try {
            getDatabaseDAOHelper().getOrderTempDAO().createOrUpdate((OrderTempModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        try {
            getDatabaseDAOHelper().getOrderTempDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeRaw( String query ){
        Log.d("QUERY", query);
        try{
            getDatabaseDAOHelper().getOrderTempDAO().executeRaw(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public OrderTempModel getDataFromQuery(String columnName, String data) {
        QueryBuilder<OrderTempModel, Integer> queryBuilder = null;
        PreparedQuery<OrderTempModel> preparedQuery = null;
        List<OrderTempModel> result = null;
        try {
            queryBuilder = getDatabaseDAOHelper().getOrderTempDAO().queryBuilder();
            queryBuilder.where().eq(columnName,data);
            preparedQuery = queryBuilder.prepare();
            result = (List<OrderTempModel>) getDatabaseDAOHelper().getOrderTempDAO().query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result == null || result.size() == 0)
            return null;
        return result.get(0);
    }

}
