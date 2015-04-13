package com.tripoin.rmumobile.util.api;

/**
 * Created by Achmad Fauzi on 11/18/2014.
 * achmad.fauzi@sigma.co.id
 *
 * Interface for basic manipulation of configuration file
 */
public interface IPropertyUtilBase {

    /**
     * This method is used to create file if loaded property is not found
     */
    public void createTheFile();

    /**
     * This method is used to check if required file is already exist or not. If not, the file will be created
     */
    public void createIfNotExist();

    /**
     * read configuration from Property Map
     * @param key String
     * @return String
     */
    public String getValuePropertyMap(String key);

    /**
     * delete a file from a path
     * @param path String
     */
    public void deleteFile(String path);
}
