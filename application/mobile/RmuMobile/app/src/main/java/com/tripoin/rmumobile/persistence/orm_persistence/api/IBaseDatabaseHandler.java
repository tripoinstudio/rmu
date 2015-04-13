package com.tripoin.rmumobile.persistence.orm_persistence.api;

import java.util.List;

/**
 * Created by Achmad Fauzi on 1/24/2015.
 * fauzi.knightmaster.achmad@gmail.com
 * @param <DATA>
 */
public interface IBaseDatabaseHandler<DATA> {

    /**
     * This function will add one entity of country which is 1 complete row in table
     * @param entity DATA
     */
    public int insertEntity(DATA entity);

    /**
     * This function will return all data from a table in an array list form
     * @return List<DATA>
     */
    public List<DATA> getAllData();

    /**
     * This function will update value from an entity
     * @param entity DATA
     */
    public void updateEntity(DATA entity);

    /**
     * This function will delete entity in table
     * @param id Integer
     */
    public void deleteEntity(Integer id);


}
