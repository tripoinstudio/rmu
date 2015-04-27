package com.tripoin.rmu.rest.util;

import android.util.Log;

import com.tripoin.rmu.rest.api.IJSONParser;
import com.tripoin.rmu.rest.enumeration.RestConstant;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Achmad Fauzi on 9/20/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Class to fetch result of Web service into stream variable
 */
public class SSLJSONParser implements IJSONParser{

    private static InputStream inputStream = null;
    private static JSONObject jsonObject = null;
    private static String json = "";

    public SSLJSONParser() {
    }

    @Override
    public JSONObject getJSONFromUrl(String url){
        try {
            //DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            /*HttpClient defaultHttpClient = customHttpClient.getHttpClient();*/
            HttpClient defaultHttpClient = new SSLHTTPClient().getHttpsClient( new CustomHttpClient().getHttpClient() );
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setParams( params );
            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while( ( line = bufferedReader.readLine() ) != null ){
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();
            json = stringBuilder.toString();
            Log.d("JSON RAW", json );
        }catch (Exception e){
            Log.e("BufferedReader Error", "Error Converting Result " + e.toString());
        }

        //try parse a string to a JSON Object
        try{
            jsonObject = new JSONObject(json);
        }catch (JSONException e){
            Log.e( "JSON Parser", "Error Parsing Data " + e.toString() );
        }
        //return Json String
        return jsonObject;
    }

    @Override
    public JSONObject retrieveJSONAsGet( String url, String chipperText ){
        //Making Http Request
        try {
            //DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpClient defaultHttpClient = new SSLHTTPClient().getHttpsClient( new CustomHttpClient().getHttpClient() );
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            HttpGet httpGet = new HttpGet(url);
            httpGet.setParams( params );
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while( ( line = bufferedReader.readLine() ) != null ){
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();
            json = stringBuilder.toString();
            Log.d("JSON RAW", json );
        }catch (Exception e){
            Log.e("BufferedReader Error", "Error Converting Result " + e.toString());
        }

        //try parse a string to a JSON Object
        try{
            jsonObject = new JSONObject(json);
        }catch (JSONException e){
            Log.e( "JSON Parser", "Error Parsing Data " + e.toString() );
        }
        //return Json String
        return jsonObject;
    }

    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params){
        //Making HttpRequest
        try{
            //Check for request method
            if ( method == RestConstant.HTTP_POST.toString()){
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
            }else if ( method == RestConstant.HTTP_GET.toString() ){
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url+="?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ( (line = bufferedReader.readLine()) != null ){
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();
            json = stringBuilder.toString();
        }catch (Exception e){
            Log.e("JSON PARSER", "Error Parsing Data"+e.toString());
        }
        return jsonObject;
    }

}
