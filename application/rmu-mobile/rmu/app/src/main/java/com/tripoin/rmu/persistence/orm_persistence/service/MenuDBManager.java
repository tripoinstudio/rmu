package com.tripoin.rmu.persistence.orm_persistence.service;

import android.content.Context;

import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.persistence.orm_persistence.DAO.DatabaseDAOHelper;
import com.tripoin.rmu.persistence.orm_persistence.api.IBaseDatabaseHandler;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 * @param <DATA>
 */
public class MenuDBManager<DATA> implements IBaseDatabaseHandler{

    static private MenuDBManager instance;

    static public void init( Context ctx ){
        if( instance == null ){
            instance = new MenuDBManager( ctx );
        }
    }

    static public MenuDBManager getInstance(){
        return instance;
    }

    private DatabaseDAOHelper databaseDAOHelper;

    public MenuDBManager(Context ctx) {
        databaseDAOHelper = new DatabaseDAOHelper(ctx);
    }

    public DatabaseDAOHelper getDatabaseDAOHelper() {
        return databaseDAOHelper;
    }

    @Override
    public int insertEntity(Object entity) {
        int result = 0;
        try {
            result = getDatabaseDAOHelper().getMenuDAO().create((MenuModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DATA> getAllData() {
        List<DATA> result = null;
        try {
            result = (List<DATA>) getDatabaseDAOHelper().getMenuDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateEntity(Object entity) {
        try {
            getDatabaseDAOHelper().getMenuDAO().update((MenuModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        try {
            getDatabaseDAOHelper().getMenuDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatement( String query ){
        try{
            getDatabaseDAOHelper().getMenuDAO().updateRaw( query );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
