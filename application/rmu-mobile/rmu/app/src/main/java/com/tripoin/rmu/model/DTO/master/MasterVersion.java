package com.tripoin.rmu.model.DTO.master;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;

import java.util.ArrayList;

/**
 * Created by Achmad Fauzi on 4/30/2015 : 11:09 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class MasterVersion extends BaseRESTDTO {

    @JsonProperty("master_version")
    private ArrayList<MasterVersionItem> masterVersionItems;

    public ArrayList<MasterVersionItem> getMasterVersionItems() {
        return masterVersionItems;
    }

    public void setMasterVersionItems(ArrayList<MasterVersionItem> masterVersionItems) {
        this.masterVersionItems = masterVersionItems;
    }

    @Override
    public String toString() {
        return "MasterVersion{" +
                "masterVersionItems=" + masterVersionItems +
                '}';
    }
}
