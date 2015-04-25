package com.tripoin.rmu.util;

import android.util.Base64;

/**
 * Created by Achmad Fauzi on 4/16/2015 : 12:04 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class Base64Credential {


    public String encodeToBase64(String s){
        return Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
    }

}
