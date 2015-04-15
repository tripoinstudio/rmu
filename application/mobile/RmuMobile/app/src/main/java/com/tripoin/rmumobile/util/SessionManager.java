package com.tripoin.rmumobile.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.tripoin.rmumobile.view.activity.ActivityMain;

import java.util.HashMap;


/**
 * Created by Achmad Fauzi on 9/20/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class SessionManager {
    //Shared Preferences
    SharedPreferences preferences;

    //Editor for Shared Preferences
    SharedPreferences.Editor editor;

    //Context
    Context context;

    //Shared Preferences Mode
    int PRIVATE_MODE = 0;

    //Shared Preferences Name
    private static final String PREF_NAME = "session";

    //All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_EMAIL = "email";

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    /*
        Create Login Session
     */
    public void createLoginSession(String email){
        //Storing Login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    /*
        Check login method wil check user login status
        If false it will redirect user to login page
        Else won't do anything
    */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            Intent i = new Intent( context, ActivityMain.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            //((Activity)_context).finish();
        }

    }

    /*
        Get Stored Session Data
     */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_EMAIL, preferences.getString(KEY_EMAIL, null));
        return user;
    }

    /*
        Clear Sesion Details
     */
    public void logoutUser(){
        //Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        Intent i = new Intent( context, ActivityMain.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity( i );
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }

}
