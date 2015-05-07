package com.tripoin.rmu.view.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tripoin.rmu.view.activity.ActivityMain;
import com.tripoin.rmu.view.activity.api.IBaseActivity;
import com.tripoin.rmu.view.enumeration.ViewConstant;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 11:13 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract class ABaseActivity extends Activity implements IBaseActivity{

    protected Typeface typeface;
    protected List<TextView> textViews;
    protected List<EditText> editTexts;
    protected List<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayoutId());
        setupTypeFace();
        initWidget();
        ButterKnife.inject(this);
    }

    @Override
    protected void onStart() {
        onLowMemory();
    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, String extraContent) {
        Intent intent = new Intent( this, clazz );
        intent.putExtra( extraKey, extraContent );
        startActivity( intent );
    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, Serializable extraContent) {
        Intent intent = new Intent( this, clazz );
        intent.putExtra( extraKey, extraContent );
        startActivity( intent );
    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, Parcelable extraContent) {
        Intent intent = new Intent( this, clazz );
        intent.putExtra( extraKey, extraContent );
        startActivity( intent );
    }


    @Override
    public void goToMainView(String extraKey, String extraContent) {
        gotoNextActivity( ActivityMain.class, extraKey, extraContent );
    }

    @Override
    public void exitApplication(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }


    @Override
    public void setupTypeFace() {
        try{
            typeface = Typeface.createFromAsset( getAssets(), initFontAssets()[0] );
            if(getTextViews().size()>0 || getTextViews() != null){
                assignTextViewTypeFace(getTextViews());
            }
            typeface = Typeface.createFromAsset( getAssets(), initFontAssets()[1] );
            if(getEditTexts().size()>0 || getEditTexts() != null){
                assignEditTextTypeFace(getEditTexts());
            }
            typeface = Typeface.createFromAsset( getAssets(), initFontAssets()[2] );
            if(getButtons().size()>0 || getButtons() != null){
                assignButtonTypeFace(getButtons());
            }
        }catch (Exception e){
            Log.w("Activity Warning", "No TypeFace Assignment found");
        }
        //release unused objects
        textViews = null;
        editTexts = null;
        buttons = null;
    }

    public List<TextView> getTextViews(){
        return null;
    }

    public List<EditText> getEditTexts(){
        return null;
    }

    public List<Button> getButtons(){
        return null;
    }

    @Override
    public String[] initFontAssets() {
        return new String[]{
                ViewConstant.FONT_ROBOT_LIGHT.toString(),
                ViewConstant.FONT_ROBOT_LIGHT_ITALIC.toString(),
                ViewConstant.FONT_ROBOT_BOLD.toString()
        };
    }


    private void assignTextViewTypeFace( List<TextView> textViews ){
        for ( TextView tv: textViews ){
            tv.setTypeface(typeface);
        }
    }

    private void assignButtonTypeFace( List<Button> buttons ){
        for( Button button: buttons){
            button.setTypeface(typeface);
        }
    }

    private void assignEditTextTypeFace(List<EditText> editTexts){
        for(EditText editText: editTexts){
            editText.setTypeface(typeface);
        }
    }
}
