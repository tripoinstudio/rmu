package com.tripoin.rmu.persistence.orm_persistence.service;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.tripoin.rmu.model.persist.ImageModel;
import com.tripoin.rmu.persistence.orm_persistence.DAO.DatabaseDAOHelper;
import com.tripoin.rmu.persistence.orm_persistence.api.IBaseDatabaseHandler;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 * @param <DATA>
 */
public class ImageMenuDBManager<DATA> implements IBaseDatabaseHandler{

    static private ImageMenuDBManager instance;

    static public void init( Context ctx ){
        if( instance == null ){
            instance = new ImageMenuDBManager( ctx );
        }
    }

    static public ImageMenuDBManager getInstance(){
        return instance;
    }

    private DatabaseDAOHelper databaseDAOHelper;

    public ImageMenuDBManager(Context ctx) {
        databaseDAOHelper = new DatabaseDAOHelper(ctx);
    }

    public DatabaseDAOHelper getDatabaseDAOHelper() {
        return databaseDAOHelper;
    }

    @Override
    public int insertEntity(Object entity) {
        int result = 0;
        try {
            result = getDatabaseDAOHelper().getImageMenuDAO().create((ImageModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DATA> getAllData() {
        List<DATA> result = null;
        try {
            result = (List<DATA>) getDatabaseDAOHelper().getImageMenuDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateEntity(Object entity) {
        try {
            getDatabaseDAOHelper().getImageMenuDAO().update((ImageModel) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        try {
            getDatabaseDAOHelper().getImageMenuDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeRaw( String query ){
        Log.d("QUERY", query);
        try{
            getDatabaseDAOHelper().getImageMenuDAO().executeRaw(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ImageModel getDataFromQuery(String columnName, String data) {
        QueryBuilder<ImageModel, Integer> queryBuilder = null;
        PreparedQuery<ImageModel> preparedQuery = null;
        List<ImageModel> result = null;
        try {
            queryBuilder = getDatabaseDAOHelper().getImageMenuDAO().queryBuilder();
            queryBuilder.where().eq(columnName,data);
            preparedQuery = queryBuilder.prepare();
            result = (List<ImageModel>) getDatabaseDAOHelper().getImageMenuDAO().query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result.size() == 0)
            return null;
        return result.get(0);
    }

    public List<ImageModel> getDataFromQueryByIdParent(String columnName, Integer data) {
        QueryBuilder<ImageModel, Integer> queryBuilder = null;
        PreparedQuery<ImageModel> preparedQuery = null;
        List<ImageModel> result = null;
        try {
            queryBuilder = getDatabaseDAOHelper().getImageMenuDAO().queryBuilder();
            queryBuilder.where().eq(columnName,data);
            preparedQuery = queryBuilder.prepare();
            result = (List<ImageModel>) getDatabaseDAOHelper().getImageMenuDAO().query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
