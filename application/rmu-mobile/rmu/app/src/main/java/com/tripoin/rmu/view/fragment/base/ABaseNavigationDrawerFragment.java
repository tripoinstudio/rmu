package com.tripoin.rmu.view.fragment.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.INavigationDrawerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 11:20 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract class ABaseNavigationDrawerFragment extends Fragment implements INavigationDrawerFragment{

    protected Typeface typeFaceContent;
    protected List<TextView> textViews = new ArrayList<TextView>();
    protected List<Button> buttons = new ArrayList<Button>();

    protected ABaseNavigationDrawerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        try{
            rootView = inflater.inflate( getViewLayoutId(), container, false );
        }catch ( Exception e ){
            if ( container != null ){
                container.removeView( rootView );
            }
            try{
                rootView = inflater.inflate(getViewLayoutId(), container, false);
            }catch (InflateException ie){

            }
        }
        getActivity().setTitle(getFragmentTitle());
        ButterKnife.inject(this, rootView);

        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeFragment();
        onLowMemory();
    }

    private void initializeFragment(){
        try{
            initWidget();
        }catch ( Exception e ){
            Toast.makeText(getActivity(), "Application error", Toast.LENGTH_SHORT).show();
        }

        typeFaceContent = Typeface.createFromAsset(getActivity().getAssets(), initAssetName()[0]);

        if( getContentTextViews() != null )
            assignContentTypeFace( getContentTextViews() );

        if( getButtons() != null )
            assignButtonTypeFace( getButtons() );

        //release unused objects
        textViews = null;
        buttons = null;
    }

    private void assignContentTypeFace( List<TextView> textViews ){
        for ( TextView tv: textViews ){
            tv.setTypeface( typeFaceContent );
        }
    }

    private void assignButtonTypeFace( List<Button> buttons ){
        for ( Button button : buttons ){
            button.setTypeface( typeFaceContent );
        }
    }
    /**
     * Initiate asset names ( font ) which will be used in that activity or Fragment
     * @return String[]
     */
    public String[] initAssetName() {
        return new String[]{
                ViewConstant.FONT_ROBOT_LIGHT_ITALIC.toString(),
                ViewConstant.FONT_ROBOT_BLACK.toString(),
                ViewConstant.FONT_ROBOT_BLACK_ITALIC.toString(),
                ViewConstant.FONT_ROBOT_BOLD.toString(),
                ViewConstant.FONT_ROBOT_ITALIC.toString(),
                ViewConstant.FONT_ROBOT_BOLD_ITALIC.toString(),
                ViewConstant.FONT_ROBOT_LIGHT.toString(),
                ViewConstant.FONT_ROBOT_MEDIUM.toString(),
                ViewConstant.FONT_ROBOT_MEDIUM_ITALIC.toString(),
                ViewConstant.FONT_ROBOT_REGULAR.toString(),
                ViewConstant.FONT_ROBOT_THIN.toString(),
                ViewConstant.FONT_ROBOT_THIN_ITALIC.toString()
        };
    }

    @Override
    public List<TextView> getContentTextViews() {
        return null;
    }

    @Override
    public List<TextView> getTitleTextViews() {
        return null;
    }

    @Override
    public List<EditText> getEditTexts() {
        return null;
    }

    @Override
    public List<Button> getButtons() {
        return null;
    }

    public void gotoNextFragment(Fragment fragmentView){
        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.container, fragmentView).commit();
    }



}
