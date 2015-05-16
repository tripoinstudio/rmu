package com.tripoin.rmu.persistence.orm_persistence.service;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.persistence.orm_persistence.DAO.DatabaseDAOHelper;
import com.tripoin.rmu.persistence.orm_persistence.api.IBaseDatabaseHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 8:32 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class OrderDetailDBManager<DATA>  implements IBaseDatabaseHandler{

    static private OrderDetailDBManager instance;

    static public void init( Context ctx ){
        if( instance == null ){
            instance = new OrderDetailDBManager( ctx );
        }
    }

    static public OrderDetailDBManager getInstance(){
        return instance;
    }

    private DatabaseDAOHelper databaseDAOHelper;

    public OrderDetailDBManager(Context ctx) {
        databaseDAOHelper = new DatabaseDAOHelper(ctx);
    }

    public DatabaseDAOHelper getDatabaseDAOHelper() {
        return databaseDAOHelper;
    }

    @Override
    public int insertEntity(Object entity) {
        int result = 0;
        try {
            result = getDatabaseDAOHelper().getOrderDetailDAO().create((OrderDetailModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DATA> getAllData() {
        List<DATA> result = null;
        try {
            result = (List<DATA>) getDatabaseDAOHelper().getOrderDetailDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateEntity(Object entity) {
        try {
            getDatabaseDAOHelper().getOrderDetailDAO().update((OrderDetailModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        try {
            getDatabaseDAOHelper().getOrderDetailDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeRaw( String query ){
        Log.d("QUERY", query);
        try{
            getDatabaseDAOHelper().getOrderDetailDAO().executeRaw(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public OrderDetailModel getDataFromQuery(String columnName, String data) {
        QueryBuilder<OrderDetailModel, Integer> queryBuilder = null;
        PreparedQuery<OrderDetailModel> preparedQuery = null;
        List<OrderDetailModel> result = null;
        try {
            queryBuilder = getDatabaseDAOHelper().getOrderDetailDAO().queryBuilder();
            queryBuilder.where().eq(columnName,data);
            preparedQuery = queryBuilder.prepare();
            result = (List<OrderDetailModel>) getDatabaseDAOHelper().getOrderDetailDAO().query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result != null) {
            if(result.size() == 0)
                return null;
        }
        return result.get(0);
    }

    public List<OrderDetailModel> getListDataFromQuery(String columnName, String data) {
        QueryBuilder<OrderDetailModel, Integer> queryBuilder = null;
        PreparedQuery<OrderDetailModel> preparedQuery = null;
        List<OrderDetailModel> result = null;
        try {
            queryBuilder = getDatabaseDAOHelper().getOrderDetailDAO().queryBuilder();
            queryBuilder.where().eq(columnName,data);
            preparedQuery = queryBuilder.prepare();
            result = (List<OrderDetailModel>) getDatabaseDAOHelper().getOrderDetailDAO().query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result != null) {
            if(result.size() == 0)
                return null;
        }
        return result;
    }

}
