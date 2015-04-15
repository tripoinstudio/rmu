package com.tripoin.rmumobile.rest;

import com.tripoin.rmumobile.common.IApplicationSetup;
import com.tripoin.rmumobile.rest.enumeration.RestConstant;
import com.tripoin.rmumobile.util.GeneralConverter;
import com.tripoin.rmumobile.util.enumeration.PropertyConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Achmad Fauzi on 12/2/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * This class is used to build URL for web service requirement
 */

public class URLBuilder implements IApplicationSetup{

    private Map<String, String> contentToBuild;
    private String urlResult;
    private GeneralConverter generalConverter;

    public URLBuilder( GeneralConverter generalConverter ) {
        this.generalConverter = generalConverter ;
    }

    public void initiate(){
        contentToBuild = new LinkedHashMap<String, String>();
    }

    public void initiateTruncate(){
        urlResult = "";
        contentToBuild = new LinkedHashMap<String, String>();
    }

    /**
     * if both param tsk and rqid is null, it will build only params injected, without context path
     *
     * @param tsk String
     * @param rqid String
     * @return String
     */
    public String buildUrl( String tsk, String rqid ){
        if( ( tsk != null ) && ( rqid != null ) ){
            StringBuilder stringBuilder = new StringBuilder();
            if( getApplicationMode().equals( PropertyConstant.PRODUCTION.toString() )){
                stringBuilder.append("https://");
            }else if( getApplicationMode().equals( PropertyConstant.DEVELOPMENT.toString() ) ) {
                stringBuilder.append("http://");
            }
            stringBuilder.append(PropertyConstant.SERVER_HOST_DEFAULT_VALUE.toString());
            stringBuilder.append(":");
            stringBuilder.append(PropertyConstant.SERVER_PORT_DEFAULT_VALUE.toString());
            stringBuilder.append(PropertyConstant.DEFAULT_WS_PATH.toString() );
            stringBuilder.append( RestConstant.RQID.toString() );
            stringBuilder.append("=");
            stringBuilder.append( rqid );
            stringBuilder.append( "&" );
            stringBuilder.append(RestConstant.TSK.toString());
            stringBuilder.append("=");
            stringBuilder.append(tsk);
            stringBuilder.append("&");
            urlResult = stringBuilder.toString();
        }
        urlResult.concat( constructContent() );
        urlResult = generalConverter.removeLastChar( urlResult );
        return urlResult;
    }

    public String buildUrlPNMessaging(){
        urlResult.concat( constructContent() );
        return urlResult;
    }

    private String constructContent(){
        for ( Map.Entry<String, String> entry: contentToBuild.entrySet() ){
            try {
                String value = entry.getValue();
                if ( value == null || value.length()<0 ){
                    value = "-";
                }
                urlResult += new StringBuilder().append( URLEncoder.encode( entry.getKey(), "UTF-8") ).append("=").append(URLEncoder.encode( value, "UTF-8")).append("&").toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(urlResult.contains("%2F")){
            urlResult = urlResult.replaceAll( "%2F", "%252F");
        }
        if( urlResult.contains("%3D")){
            urlResult = urlResult.replaceAll( "%3D", RestConstant.EQUALS.toString());
        }
        if( urlResult.contains("%26")){
            urlResult = urlResult.replaceAll( "%26", RestConstant.AND.toString());
        }
        return urlResult;
    }


    public Map<String, String> getContentToBuild() {
        return contentToBuild;
    }

    public String getUrlResult() {
        return urlResult;
    }

    public void setUrlResult(String urlResult) {
        this.urlResult = urlResult;
    }

    @Override
    public String getApplicationMode(){
        return PropertyConstant.APP_MODE.toString();
    }
}
