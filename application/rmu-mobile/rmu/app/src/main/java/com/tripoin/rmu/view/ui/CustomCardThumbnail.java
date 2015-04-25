package com.tripoin.rmu.view.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 3:05 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class CustomCardThumbnail extends CardThumbnail {

    public CustomCardThumbnail(final Context context, final int innerLayout) {
        super(context);
        CustomSource source = new CustomSource() {
            @Override
            public String getTag() {
                return "";
            }

            @Override
            public Bitmap getBitmap() {
                return new RoundedImage(BitmapFactory.decodeResource(context.getResources(), innerLayout)).getBitmap();
            }
        };
        setCustomSource(source);
    }


}
