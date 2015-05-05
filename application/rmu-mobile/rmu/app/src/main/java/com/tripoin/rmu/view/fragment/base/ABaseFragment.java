package com.tripoin.rmu.view.fragment.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.view.activity.ActivityMain;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.IRoboguiceFragment;

import java.util.ArrayList;
import java.util.List;

import roboguice.RoboGuice;


/**
 * Created by Achmad Fauzi on 11/24/2014.
 * achmad.fauzi@sigma.co.id
 *
 * This class is used as common functions of a fragment ( IBaseFragment )
 * Extend to RoboFragment for dependency injection
 */
public abstract class ABaseFragment extends Fragment implements IRoboguiceFragment {

    protected Typeface typeFaceTitle;
    protected Typeface typeFaceContent;
    protected Typeface typeFaceEditText;
    protected List<TextView> textViews = new ArrayList<TextView>();
    /*private List<EditText> editTexts = new ArrayList<EditText>();*/
    protected List<Button> buttons = new ArrayList<Button>();

    protected ABaseFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectViewMembers(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeFragment();
        onLowMemory();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            RoboGuice.getInjector(getActivity()).injectMembersWithoutViews(this);
        }catch ( Exception e ){
            RoboGuice.getInjector(new ActivityMain()).injectMembersWithoutViews(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        setupActionBar();
        return rootView;
    }

    private void initializeFragment(){
        try{
            initWidget();
        }catch ( Exception e ){
            Toast.makeText(getActivity(), "Application error", Toast.LENGTH_SHORT).show();
        }
        typeFaceTitle = Typeface.createFromAsset(getContext().getAssets(), initAssetName()[0]);
        typeFaceContent = Typeface.createFromAsset(getContext().getAssets(), initAssetName()[1]);
        typeFaceContent = Typeface.createFromAsset(getContext().getAssets(), initAssetName()[2]);

        /*try{*/
            if( getTitleTextViews() != null )
                assignTitleTypeFace( getTitleTextViews() );

            if( getContentTextViews() != null )
                assignContentTypeFace( getContentTextViews() );

            if( getEditTexts() != null )
                assignEditTextTypeFace( getEditTexts() );

            if( getButtons() != null )
                assignButtonTypeFace( getButtons() );
        /*}catch (Exception e){
            e.printStackTrace();
        }*/

        //release unused objects
        textViews = null;
        buttons = null;
    }

    @Override
    public void setupActionBar() {
        setDisplayHomeUpEnable( false );
    }

    public void setDisplayHomeUpEnable( Boolean enable ){
        ( (ActionBarActivity) getActivity() ).getSupportActionBar().setDisplayHomeAsUpEnabled( enable );
    }

    private void assignTitleTypeFace( List<TextView> textViews ){
        for ( TextView tv: textViews ){
            tv.setTypeface( typeFaceTitle );
        }
    }

    private void assignContentTypeFace( List<TextView> textViews ){
        for ( TextView tv: textViews ){
            tv.setTypeface( typeFaceContent );
        }
    }

    private void assignEditTextTypeFace(List<EditText> editTexts){
        for ( EditText et: editTexts ){
            et.setTypeface( typeFaceEditText );
        }
    }

    private void assignButtonTypeFace( List<Button> buttons ){
        for ( Button button : buttons ){
            button.setTypeface( typeFaceContent );
        }
    }
    /**
     * Initiate 3 asset names ( font ) which will be used in that activty
     * asset 1 for title
     * asset 2 for content also for button
     * asset 3 for edittext
     * @return String[]
     */
    public String[] initAssetName() {
        return new String[]{
                ViewConstant.FONT_ROBOT_BLACK.toString(),
                ViewConstant.FONT_ROBOT_BLACK_ITALIC.toString(),
                ViewConstant.FONT_ROBOT_BOLD_ITALIC.toString()
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

    @Override
    public void initWidget() {

    }

    /**
     * Check wheter Fragment is Active
     * @return boolean
     */
    public boolean isFragmentUIActive() {
        return isAdded() && !isDetached() && !isRemoving();
    }

/*    public void gotoNextFragment(Fragment fragmentView){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( content_frame, fragmentView );
        fragmentTransaction.commit();
    }

    public void gotoNextFragmentTest(FragmentAllTest fragmentView){
        FragmentTransaction fragmentTransaction = fragmentView.actMain.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( content_frame, fragmentView );
        fragmentTransaction.commit();
    }*/

}
