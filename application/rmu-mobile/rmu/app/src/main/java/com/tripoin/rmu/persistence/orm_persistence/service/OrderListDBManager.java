package com.tripoin.rmu.persistence.orm_persistence.service;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.persistence.orm_persistence.DAO.DatabaseDAOHelper;
import com.tripoin.rmu.persistence.orm_persistence.api.IBaseDatabaseHandler;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 * @param <DATA>
 */
public class OrderListDBManager<DATA> implements IBaseDatabaseHandler{

    static private OrderListDBManager instance;

    static public void init( Context ctx ){
        if( instance == null ){
            instance = new OrderListDBManager( ctx );
        }
    }

    static public OrderListDBManager getInstance(){
        return instance;
    }

    private DatabaseDAOHelper databaseDAOHelper;

    public OrderListDBManager(Context ctx) {
        databaseDAOHelper = new DatabaseDAOHelper(ctx);
    }

    public DatabaseDAOHelper getDatabaseDAOHelper() {
        return databaseDAOHelper;
    }

    @Override
    public int insertEntity(Object entity) {
        int result = 0;
        try {
            result = getDatabaseDAOHelper().getOrderListDAO().create((OrderListModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DATA> getAllData() {
        List<DATA> result = null;
        try {
            result = (List<DATA>) getDatabaseDAOHelper().getOrderListDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateEntity(Object entity) {
        try {
            getDatabaseDAOHelper().getOrderListDAO().update((OrderListModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        try {
            getDatabaseDAOHelper().getOrderListDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeRaw( String query ){
        Log.d("QUERY", query);
        try{
            getDatabaseDAOHelper().getOrderListDAO().executeRaw(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<DATA> getAllDataFromQuery(String seatNumber, String carriageNumber, String processStatus, String orderNumber) {
        QueryBuilder<OrderListModel, Integer> queryBuilder = null;
        PreparedQuery<OrderListModel> preparedQuery = null;
        List<DATA> result = null;
        try {
            queryBuilder = getDatabaseDAOHelper().getOrderListDAO().queryBuilder();
            Where where = queryBuilder.where();
            if(seatNumber != null && !seatNumber.trim().equalsIgnoreCase("All")) {
                where.eq("order_list_seat_number", seatNumber);
                if((carriageNumber != null && !carriageNumber.trim().equalsIgnoreCase("All")) || (processStatus!=null && !processStatus.trim().equalsIgnoreCase("0")) || (orderNumber!=null&&!orderNumber.trim().equalsIgnoreCase("")))
                    where.and();
            }
            if(carriageNumber != null && !carriageNumber.trim().equalsIgnoreCase("All")) {
                where.eq("order_list_carriage_number", carriageNumber);
                if((processStatus!=null && !processStatus.trim().equalsIgnoreCase("0")) || (orderNumber!=null&&!orderNumber.trim().equalsIgnoreCase("")))
                    where.and();
            }
            if(processStatus!=null && !processStatus.trim().equalsIgnoreCase("0")){
                where.eq("order_list_process_status", processStatus);
                if(orderNumber!=null&&!orderNumber.trim().equalsIgnoreCase(""))
                    where.and();
            }
            if(orderNumber!=null&&!orderNumber.trim().equalsIgnoreCase("")){
                where.like("order_list_order_id","%"+orderNumber+"%");
            }
            preparedQuery = where.prepare();
            result = (List<DATA>) getDatabaseDAOHelper().getOrderListDAO().query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
