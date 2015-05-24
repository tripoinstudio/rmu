package com.tripoin.rmu.util;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 11:42 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class ImageDownloader {

    protected String urlSource;
    protected String pathTarget;

    public ImageDownloader(String urlSource, String pathTarget) {
        this.urlSource = urlSource;
        this.pathTarget = pathTarget;
    }

    public void downloadImage(){
        try {
            File file = new File(pathTarget);
            if(!file.isFile()){
                URL url = new URL(urlSource);

                URLConnection ucon = url.openConnection();
                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                ByteArrayBuffer baf = new ByteArrayBuffer(50);
                int current = 0;
                while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
                }

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(baf.toByteArray());
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
