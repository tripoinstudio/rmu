package com.tripoin.rmumobile.view.activity.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.tripoin.rmumobile.R;
import com.tripoin.rmumobile.util.GeneralConverter;
import com.tripoin.rmumobile.util.GeneralValidation;
import com.tripoin.rmumobile.view.activity.ActivityMain;
import com.tripoin.rmumobile.view.activity.api.IBaseActivity;

import java.util.ArrayList;
import java.util.List;

import roboguice.RoboGuice;
import roboguice.activity.RoboActionBarActivity;

/**
 * Created by Achmad Fauzi on 11/19/2014.
 * achmad.fauzi@sigma.co.id
 *
 * This class is used as common functions for an Activity ( IBaseActivity)
 * Extends towards RoboActivity for dependency injection
 */
public abstract class ABaseActivity extends RoboActionBarActivity implements IBaseActivity{

    protected Typeface typeFaceTitle;
    protected Typeface typeFaceContent;
    protected Typeface typeFaceEditText;
    protected ActionBar actionBar;
    protected List<TextView> textViews = new ArrayList<TextView>();
    protected List<EditText> editTexts = new ArrayList<EditText>();
    public GeneralConverter generalConverter = new GeneralConverter();
    public final GeneralValidation generalValidation = new GeneralValidation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupActionBar();
        setupTypeFace();
        initWidget();
        setupValues();
    }

    @Override
    protected void onStart() {
        onLowMemory();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        if( isUsedOptionMenu() ){
            getMenuInflater().inflate( getOptionMenuLayoutId(), menu );
            generateOptionMenu(menu);
        }
        return isUsedOptionMenu();
    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, String extraContent ) {
        Intent intent = new Intent( this, clazz );
        intent.putExtra( extraKey, extraContent );
        startActivity( intent );
    }

    @Override
    public void goToMainView( String extraKey, String extraContent ) {
        gotoNextActivity( ActivityMain.class, extraKey, extraContent );
    }

    @Override
    public void exitApplication( Context context ) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    public void setupActionBar(){
        this.actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable( new ColorDrawable( getResources().getColor(R.color.red_dark_holo ) ) );
        actionBar.setTitle( initActionBarTitle() );
    }

    @Override
    public void setupTypeFace() {
        typeFaceTitle = Typeface.createFromAsset(getAssets(), initAssetName()[0]);
        typeFaceContent = Typeface.createFromAsset(getAssets(), initAssetName()[1]);
        typeFaceContent = Typeface.createFromAsset(getAssets(), initAssetName()[2]);

        if( getTitleTextViews() != null )
            assignTitleTypeFace( getTitleTextViews() );

        if( getContentTextViews() != null )
            assignContentTypeFace( getContentTextViews() );

        if( getEditTexts() != null )
            assignEditTexts( getEditTexts() );

        //release unused objects
        textViews = null;
        editTexts = null;
    }

    /**
     * Mengkoleksi TextView dalam grup Title dalam sebuah Activity
     * @return List<TextView>
     */
    protected abstract List<TextView> getTitleTextViews();

    private void assignTitleTypeFace( List<TextView> textViews ){
        for ( TextView tv: textViews ){
            tv.setTypeface( typeFaceTitle );
        }
    }

    /**
     * Mengkoleksi TextView dalam grup content dalam sebuah Activity
     * @return List<TextView>
     */
    protected abstract List<TextView> getContentTextViews();

    private void assignContentTypeFace( List<TextView> textViews ){
        for ( TextView tv: textViews ){
            tv.setTypeface( typeFaceContent );
        }
    }

    /**
     * Mengkoleksi EditText dalam sebuah Activity
     * @return List<EditText>
     */
    protected abstract List<EditText> getEditTexts();

    private void assignEditTexts( List<EditText> editTexts ){
        for ( EditText et: editTexts ){
            et.setTypeface( typeFaceEditText );
        }
    }

    protected boolean isUsedOptionMenu(){
        return false;
    }

    protected void generateOptionMenu( Menu menu ){}

    protected abstract int getOptionMenuLayoutId();

    /**
     * Initiate 3 asset names ( fonts ) which will be used within current active Activity
     * asset 1 for title
     * asset 2 for content
     * asset 3 for edittext
     * @return String[]
     */
    protected abstract String[] initAssetName();

    /**
     * Initiate title ( String ) for ActionBar within current active Activity
     * @return String
     */
    protected abstract String initActionBarTitle();

}
