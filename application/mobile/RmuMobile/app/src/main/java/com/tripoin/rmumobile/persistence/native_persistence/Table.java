package com.tripoin.rmumobile.persistence.native_persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Achmad Fauzi on 12/22/2014.
 * achmad.fauzi@sigma.co.id
 *
 * Table DTO helper
 */
public class Table implements Serializable{

    private String name;
    private List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
