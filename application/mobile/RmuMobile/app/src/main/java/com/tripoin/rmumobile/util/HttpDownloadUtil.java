package com.tripoin.rmumobile.util;

import android.util.Log;

import com.tripoin.rmumobile.model.DTO.DownloadHolderDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Achmad Fauzi on 1/21/2015.
 * achmad.fauzi@sigma.co.id
 */
public class HttpDownloadUtil {
    private static final int BUFFER_SIZE = 4096;

    /**
     * Downloads a file from a URL
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws java.io.IOException
     */
    public DownloadHolderDTO downloadFile(String fileURL, String saveDir)throws IOException {
        DownloadHolderDTO downloadHolderDTO = new DownloadHolderDTO();
        Long startDownloadTime = System.currentTimeMillis();
        URL url ;
        HttpURLConnection httpConn = null;
        /*try{*/
            url = new URL(fileURL);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setConnectTimeout(1000 * 30); // mTimeout is in seconds
            int responseCode = httpConn.getResponseCode();
            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();
                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10, disposition.length() - 1);
                    }
                } else {
                    // extracts file name from URL
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
                }

                downloadHolderDTO.setFileName( fileName );
                downloadHolderDTO.setSize( contentLength );
                downloadHolderDTO.setType( contentType );

                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;
                // opens an output stream to save into file
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);
                int bytesRead;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
            } else {
                downloadHolderDTO.setErrMessage("No file to download. Server replied HTTP code: "+responseCode);
                Log.e("No file to download. Server replied HTTP code: ", String.valueOf(responseCode));
            }
        /*}catch ( Exception e ){
            downloadHolderDTO.setErrMessage("Error occured when downloading "+ e.toString());
            Log.e("Error occured when downloading ", e.toString());
        }*/
        Long endDownloadTime = System.currentTimeMillis();
        Double totalTime = Double.valueOf(endDownloadTime - startDownloadTime);
        if( totalTime <= 0 ){
            totalTime = 0.1;
        }
        downloadHolderDTO.setTotalTime( totalTime );
        httpConn.disconnect();
        return downloadHolderDTO;
    }
}
