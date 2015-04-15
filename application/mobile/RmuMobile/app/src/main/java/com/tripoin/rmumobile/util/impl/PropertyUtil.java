package com.tripoin.rmumobile.util.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmumobile.util.enumeration.PropertyConstant;
import com.tripoin.rmumobile.util.top.APropertyUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;


/**
 * Created by Achmad Fauzi on 11/14/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Implementation class to manipulate configuration file
 */
public class PropertyUtil extends APropertyUtil {


    public PropertyUtil(String fileName, Context context) {
        super(fileName, context);
        readFullProperty();
    }

    @Override
    public String getValuePropertyMap(String key) {
        String result = propertyMap.get( key );
        if ( result == null ){
            if ( key.equals(PropertyConstant.VIDEO_ID_TEST.toString()) ){
                result = PropertyConstant.VIDEO_ID_TEST_DEFAULT_VALUE.toString();
                saveSingleProperty( key, result );
            }else if ( key.equals( PropertyConstant.BROWSER_URL_TEST.toString() ) ){
                result = PropertyConstant.BROWSER_URL_TEST_DEFAULT_VALUE.toString();
                saveSingleProperty( key, result );
            }else if ( key.equals( PropertyConstant.INTERVAL_REQUEST_KEY.toString() ) ){
                result = PropertyConstant.INTERVAL_REQUEST_DEFAULT_VALUE.toString();
                saveSingleProperty( key, result );
            }else if( key.equals( PropertyConstant.SERVER_HOST_KEY.toString()) ){
                result = PropertyConstant.SERVER_HOST_DEFAULT_VALUE.toString();
                saveSingleProperty( key, result );
            }else if( key.equals( PropertyConstant.SERVER_PORT_KEY.toString()) ){
                result = PropertyConstant.SERVER_PORT_DEFAULT_VALUE.toString();
                saveSingleProperty( key, result );
            }else if( key.equals( PropertyConstant.START_WORKING_HOUR_KEY.toString()) ) {
                result = PropertyConstant.START_WORKING_HOUR_DEFAULT_VALUE.toString();
                saveSingleProperty(key, result);
            }else if( key.equals( PropertyConstant.STOP_WORKING_HOUR_KEY.toString()) ){
                result = PropertyConstant.STOP_WORKING_HOUR_DEFAULT_VALUE.toString();
                saveSingleProperty( key, result );
            }else if( key.equals( PropertyConstant.VIDEO_RESOLUTION_KEY.toString()) ) {
                result = PropertyConstant.VIDEO_RESOLUTION_DEFAULT_VALUE.toString();
                saveSingleProperty(key, result);
            }else if( key.equals( PropertyConstant.DOWNLOAD_URL_KEY.toString() )){
                result = PropertyConstant.DOWNLOAD_URL_DEFAULT_VALUE.toString();
                saveSingleProperty( key, result);
            }else if( key.equals( PropertyConstant.VIDEO_VIEW_KEY.toString() )){
                result = PropertyConstant.VIDEO_VIEW_DEFAULT_VALUE.toString();
                saveSingleProperty( key, result);
        }
        }
        return result;
    }

    @Override
    public void saveSingleProperty( String key, String value) {
        try {
            FileInputStream fis = new FileInputStream( file );
            prop.load( fis );
            Enumeration enumProps = prop.propertyNames();
            String keyProp = "";
            if ( enumProps.hasMoreElements() ){
                while ( enumProps.hasMoreElements() ){
                    keyProp = (String) enumProps.nextElement();
                    if ( keyProp.equals( key ) ){
                        prop.setProperty( keyProp, value );
                        break;
                    }else{
                        prop.setProperty( key, value );
                        break;
                    }
                }
            }else{
                prop.setProperty( key, value );
            }
            out = new FileOutputStream( file );
            prop.store(out, null);
            out.close();
        } catch (IOException e) {
            System.err.println( "Failed to open property "+fileName );
            createIfNotExist();
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            readFullProperty();
        }
    }

    @Override//still error
    public void saveFullProperty( String[] keys, String values ) {
        try {
            FileInputStream fis = new FileInputStream( file );
            prop.load( fis );
            Enumeration enumProps = prop.propertyNames();
            String keyProp = "";
            int a=0;
            while ( enumProps.hasMoreElements() ){
                keyProp = (String) enumProps.nextElement();
                keys[a] = prop.getProperty(keyProp);
                a++;
            }
        } catch (IOException e) {
            System.err.println( "Failed to open property "+ fileName );
            createIfNotExist();
        }finally {
            if ( fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //readFullProperty();
        }
    }

    @Override
    public String readSingleProperty( String key ) {
        String propertyValueResult = "";
        try {
            fis = new FileInputStream( file );
            prop.load( fis );
            Enumeration enumProps = prop.propertyNames();
            String keyProp;
            while ( enumProps.hasMoreElements() ){
                keyProp = (String) enumProps.nextElement();
                if ( key.equals( key ) ){
                    propertyValueResult = (String) prop.get( keyProp );
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to open property "+ fileName );
            e.printStackTrace();
        }finally {
            if ( fis != null ) {
                try {
                    fis.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
        return propertyValueResult;
    }

    @Override
    public void readFullProperty() {
        try {
            fis = new FileInputStream( file );
            prop.load( fis );
            Enumeration enumProps = prop.propertyNames();
            String keyProp = "";
            while ( enumProps.hasMoreElements() ){
                keyProp = (String) enumProps.nextElement();
                propertyMap.put( keyProp, prop.getProperty( keyProp ) );
                //Log.d( keyProp, propertyMap.get( keyProp ) );
            }
        } catch (IOException e) {
            System.err.println("Failed to open property "+ fileName );
            e.printStackTrace();
            createIfNotExist();
        }finally {
            if ( fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
