package com.tripoin.rmu.util.api;

/**
 * Created by Achmad Fauzi on 11/14/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Interface for advance manipulation of configuration file
 */
public interface IPropertyUtil {

    /**
     * This method is used to save single property/ configuration from device permanently formatted as plain text
     * @param key String
     * @param value String
     */
    public void saveSingleProperty(String key, String value);

    /**
     * This method is used to save full property/ configuration from device permanently formatted as plain text
     * @param keys String[]
     * @param values String
     */
    public void saveFullProperty(String[] keys, String values);

    /**
     * This method is used to read single property from device which is saved before
     * @param key String
     * @return String
     */
    public String readSingleProperty(String key);

    /**
     * This method is used to read full property from device which is saved before
     * @return null
     */
    public void readFullProperty();

}
