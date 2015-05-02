package com.tripoin.rmu.view.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tripoin.rmu.util.enumeration.PropertyConstant;

import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 3:05 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class CustomCardSource extends CardThumbnail {

    public CustomCardSource(final Context context, final String imageName) {
        super(context);
        CustomSource source = new CustomSource() {
            @Override
            public String getTag() {
                return imageName;
            }

            @Override
            public Bitmap getBitmap() {
                return BitmapFactory.decodeFile(PropertyConstant.PROPERTIES_PATH.toString().concat(imageName), new BitmapFactory.Options());
            }
        };
        setCustomSource(source);
    }


}
