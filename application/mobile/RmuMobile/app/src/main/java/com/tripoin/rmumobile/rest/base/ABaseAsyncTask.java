package com.tripoin.rmumobile.rest.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Achmad Fauzi on 1/1/2015.
 * achmad.fauzi@sigma.co.id
 */
public abstract class ABaseAsyncTask extends AsyncTask{

    protected ProgressDialog progressDialog = new ProgressDialog( getContext() );

    protected abstract String getProgressDialogTitle();

    protected abstract Context getContext();

    @Override
    protected void onPreExecute() {
        progressDialog.setTitle( getProgressDialogTitle() );
        progressDialog.setMessage( "Please Wait" );
        progressDialog.setCancelable( true );
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        progressDialog.dismiss();
    }

}
