package com.tripoin.rmumobile.persistence.orm_persistence.service;

import android.content.Context;

import com.tripoin.rmumobile.model.persist.SelfTestModel;
import com.tripoin.rmumobile.persistence.orm_persistence.DAO.DatabaseDAOHelper;
import com.tripoin.rmumobile.persistence.orm_persistence.api.IBaseDatabaseHandler;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * achmad.fauzi@sigma.co.id
 * @param <DATA>
 */
public class SelfTestDBManager<DATA> implements IBaseDatabaseHandler{

    static private SelfTestDBManager instance;

    static public void init( Context ctx ){
        if( instance == null ){
            instance = new SelfTestDBManager( ctx );
        }
    }

    static public SelfTestDBManager getInstance(){
        return instance;
    }

    private DatabaseDAOHelper databaseDAOHelper;

    public SelfTestDBManager(Context ctx) {
        databaseDAOHelper = new DatabaseDAOHelper( ctx);
    }

    public DatabaseDAOHelper getDatabaseDAOHelper() {
        return databaseDAOHelper;
    }

    @Override
    public int insertEntity(Object entity) {
        int result = 0;
        try {
            result = getDatabaseDAOHelper().getSelfTestDAO().create((SelfTestModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DATA> getAllData() {
        List<DATA> result = null;
        try {
            result = (List<DATA>) getDatabaseDAOHelper().getSelfTestDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateEntity(Object entity) {
        try {
            getDatabaseDAOHelper().getSelfTestDAO().update((SelfTestModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        try {
            getDatabaseDAOHelper().getSelfTestDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatement( String query ){
        try{
            getDatabaseDAOHelper().getSelfTestDAO().updateRaw( query );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
