package com.tripoin.rmu.view.fragment.api;

/**
 * Created by Achmad Fauzi on 4/30/2015 : 10:27 PM.
 * mailto : achmad.fauzi@sigma.co.id
 * @param <DATA> DATA
 */
public interface ISynchronizeData<DATA> {

    public void updateContent(String latestVersion);

    public String getTableNameTrain();

    public void selectRelatedTable();
    /*sync data commit*/

}
