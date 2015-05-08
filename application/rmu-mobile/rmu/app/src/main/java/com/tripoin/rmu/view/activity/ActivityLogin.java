package com.tripoin.rmu.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.DTO.master.MasterVersionItem;
import com.tripoin.rmu.model.DTO.user.UserDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.ILoginPost;
import com.tripoin.rmu.rest.impl.LoginRest;
import com.tripoin.rmu.util.GeneralConverter;
import com.tripoin.rmu.util.GeneralValidation;
import com.tripoin.rmu.util.NetworkConnectivity;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.base.ABaseActivity;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.impl.FragmentChangeIPServer;
import com.tripoin.rmu.view.fragment.impl.FragmentUserProfile;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by Achmad Fauzi on 9/21/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */

public class ActivityLogin extends ABaseActivity implements ILoginPost {

    @InjectView(R.id.txt_username) EditText txtUserName;
    @InjectView(R.id.txt_password) EditText txtPassword;
    @InjectView(R.id.btSignIn) Button btSignIn;
    @InjectView(R.id.chkShowPassword) CheckBox chkShowPassword;

    private GeneralValidation generalValidation;
    private GeneralConverter generalConverter;
    private NetworkConnectivity networkConnectivity;

    private String chipperAuth;
    private String userName;
    private PropertyUtil securityUtil;

    @Override
    public void initWidget() {
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), getApplicationContext());
        generalValidation = new GeneralValidation();
        generalConverter = new GeneralConverter();
        networkConnectivity = new NetworkConnectivity(this, null);
        VersionDBManager.init(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.menu_server_configuration){
            Toast.makeText(this,"server config", Toast.LENGTH_SHORT).show();
        }else if( item.getItemId() == R.id.menu_bluetooth_configuration ){
            Toast.makeText(this,"bluetooth config", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //detectLoginStatus();
    }

    @OnClick(R.id.btSignIn)
    public void signIn(){
        if(!generalValidation.isEmptyEditText(txtUserName)){
            userName = txtUserName.getText().toString();
            if(!generalValidation.isEmptyEditText(txtPassword)){
                chipperAuth = generalConverter.encodeToBase64(txtUserName.getText().toString().concat(ViewConstant.COLON.toString()).concat(txtPassword.getText().toString()));
                if(networkConnectivity.checkConnectivity()){
                    LoginRest loginRest = new LoginRest(this) {
                        @Override
                        public Context getContext() {
                            return ActivityLogin.this;
                        }
                    };
                    loginRest.execute(chipperAuth);
                }
            }else{
                Toast.makeText(this, "Password can not be empty", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "User name can not be empty", Toast.LENGTH_SHORT).show();
        }
    }


    @OnCheckedChanged(R.id.chkShowPassword) void onChecked(boolean isChecked){
        if (!isChecked) {
            txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitApplication( this );
    }

    @Override
    public void onPostDelegate(Object objectResult) {
        try{
            if(objectResult != null){
                UserDTO userDTO = (UserDTO) objectResult;
                if(userDTO.getErr_code().equals(ViewConstant.ZERO.toString())){

                    securityUtil.saveSingleProperty(PropertyConstant.USER_NAME.toString(), userName);
                    securityUtil.saveSingleProperty(PropertyConstant.LOGIN_STATUS_KEY.toString(), PropertyConstant.LOGIN_STATUS_VALUE.toString());
                    securityUtil.saveSingleProperty(PropertyConstant.CHIPPER_AUTH.toString(), chipperAuth);

                    VersionModel versionModel = null;
                    try{
                        versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, "master_seat");
                    }catch (Exception e){
                        for( MasterVersionItem masterVersion : userDTO.getMasterVersionItems() ){
                            versionModel = new VersionModel();
                            versionModel.setVersionNameTable(masterVersion.getVersionTable());
                            versionModel.setVersionTimestamp("01-01-2000 23:59:59.0");
                            VersionDBManager.getInstance().insertEntity(versionModel);
                        }
                    }
                    gotoNextActivity(ActivityMain.class, PropertyConstant.USER_DTO.toString(), userDTO);
                }else {
                    Toast.makeText(this, "An error occurred ".concat(userDTO.getErr_msg()),Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Connection timed out, please your check connection", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnItemSelected(R.id.dropdown_ip_bt)
    public void dropdownIpBt(){
        Spinner dropdown_ip_bt;
        dropdown_ip_bt = (Spinner) findViewById(R.id.dropdown_ip_bt);
        dropdown_ip_bt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),"OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),"OnItemSelectedListener : " + parent.getItemAtPosition(parent.getLastVisiblePosition()).toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
