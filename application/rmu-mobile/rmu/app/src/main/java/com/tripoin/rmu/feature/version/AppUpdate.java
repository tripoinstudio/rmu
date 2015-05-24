package com.tripoin.rmu.feature.version;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tripoin.rmu.util.ImageDownloader;
import com.tripoin.rmu.util.enumeration.PropertyConstant;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Achmad Fauzi on 5/23/2015 : 7:11 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class AppUpdate extends ImageDownloader {

    private Context context;

    public AppUpdate(String urlSource, String pathTarget) {
        super(urlSource, pathTarget);
    }

    @Override
    public void downloadImage() {
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

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(PropertyConstant.PROPERTIES_PATH.toString().concat("update.apk"))), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
                context.startActivity(intent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
