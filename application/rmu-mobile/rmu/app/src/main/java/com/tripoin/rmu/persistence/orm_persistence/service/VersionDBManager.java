package com.tripoin.rmu.persistence.orm_persistence.service;

import android.content.Context;

import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.DAO.VersionDAOHelper;
import com.tripoin.rmu.persistence.orm_persistence.api.IBaseDatabaseHandler;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 * @param <DATA>
 */
public class VersionDBManager<DATA> implements IBaseDatabaseHandler{

    static private VersionDBManager instance;

    static public void init( Context ctx ){
        if( instance == null ){
            instance = new VersionDBManager( ctx );
        }
    }

    static public VersionDBManager getInstance(){
        return instance;
    }

    private VersionDAOHelper versionDAOHelper;

    public VersionDBManager(Context ctx) {
        versionDAOHelper = new VersionDAOHelper(ctx);
    }

    public VersionDAOHelper getVersionDAOHelper() {
        return versionDAOHelper;
    }

    @Override
    public int insertEntity(Object entity) {
        int result = 0;
        try {
            result = getVersionDAOHelper().getVersionDAO().create((VersionModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DATA> getAllData() {
        List<DATA> result = null;
        try {
            result = (List<DATA>) getVersionDAOHelper().getVersionDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateEntity(Object entity) {
        try {
            getVersionDAOHelper().getVersionDAO().update((VersionModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        try {
            getVersionDAOHelper().getVersionDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatement( String query ){
        try{
            getVersionDAOHelper().getVersionDAO().updateRaw( query );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
