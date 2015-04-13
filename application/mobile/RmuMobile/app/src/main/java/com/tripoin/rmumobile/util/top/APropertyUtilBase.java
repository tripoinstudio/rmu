package com.tripoin.rmumobile.util.top;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmumobile.util.api.IPropertyUtilBase;
import com.tripoin.rmumobile.util.enumeration.PropertyConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Achmad Fauzi on 11/18/2014.
 * achmad.fauzi@sigma.co.id
 *
 * Abstract class for basic manipulation of property
 */
public abstract class APropertyUtilBase implements IPropertyUtilBase{

    protected Properties prop = new Properties();
    private final String propertiesPath = PropertyConstant.PROPERTIES_PATH.toString();
    protected File file;
    protected FileOutputStream out;
    protected FileInputStream fis;
    protected String fileName;
    private Context context;

    protected  Map< String, String> propertyMap = new HashMap<String, String>();

    protected APropertyUtilBase( String fileName, Context context ) {
        this.context = context;
        this.fileName = fileName;
        file = new File( propertiesPath + fileName );
        try {
            fis = new FileInputStream( file );
        } catch (FileNotFoundException e) {
            createIfNotExist();
        }
    }

    public void createTheFile() {
        try {
            file.createNewFile();
            out = context.openFileOutput( fileName, context.MODE_PRIVATE );
            OutputStreamWriter outputStreamWriter =new OutputStreamWriter( out );
            outputStreamWriter.close();
        } catch (Exception e) {
            Log.d("Create New File " + fileName, e.toString());
        }finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void createIfNotExist() {
        try {
            File dir = new File( propertiesPath );
            if ( !dir.exists() ){
                dir.mkdirs();
            }
            fis = new FileInputStream( file );
            createTheFile();
        } catch (FileNotFoundException e) {
            createTheFile();
        }
    }

    public boolean isFileExist(){
        boolean result = true;
        try {
            File dir = new File( propertiesPath );
            if ( !dir.exists() ){
                result = false;
            }
            fis = new FileInputStream( file );
        } catch (FileNotFoundException e) {
            result = false;
        }
        return result;
    }

    @Override
    public void deleteFile(String path) {
        file = new File( path );
        if ( file.exists()) {
            if ( file.delete() ) {
                Log.e("file Deleted : " ,  path);
            } else {
                Log.e("file not Deleted : " , path);
            }
        }
    }
}
