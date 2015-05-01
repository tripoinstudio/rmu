package com.tripoin.rmu.view.activity;


import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.DTO.master.MasterVersion;
import com.tripoin.rmu.model.DTO.master.MasterVersionItem;
import com.tripoin.rmu.model.DTO.user.UserDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.ILoginPost;
import com.tripoin.rmu.rest.impl.LoginRest;
import com.tripoin.rmu.security.base.ASecureActivity;
import com.tripoin.rmu.util.Version;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.view.enumeration.ViewConstant;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Achmad Fauzi on 9/21/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */

@ContentView(R.layout.activity_login)
public class ActivityLogin extends ASecureActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, ILoginPost {

    @InjectView(R.id.txt_username) private EditText txtUserName;
    @InjectView(R.id.txt_password) private EditText txtPassword;
    @InjectView(R.id.btSignIn) private Button btSignIn;
    @InjectView(R.id.chkShowPassword) private CheckBox chkShowPassword;

    private String chipperAuth;
    private String userName;

    @Override
    protected int getOptionMenuLayoutId() {
        return R.menu.empty_menu;
    }

    @Override
    protected String initActionBarTitle() {
        getSupportActionBar().hide();
        return stringEmpty;
    }

    @Override
    public void initWidget() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //detectLoginStatus();
    }

    @Override
    public void setupValues() {
        btSignIn.setOnClickListener(this);
        chkShowPassword.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btSignIn ){
            if(!generalValidation.isEmptyEditText(txtUserName)){
                userName = txtUserName.getText().toString();
                if(!generalValidation.isEmptyEditText(txtPassword)){
                    chipperAuth = generalConverter.encodeToBase64(txtUserName.getText().toString().concat(ViewConstant.COLON.toString()).concat(txtPassword.getText().toString()));
                    if(networkConnectivity.checkConnectivity()){
                        LoginRest loginRest = new LoginRest(this) {
                            @Override
                            protected Context getContext() {
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
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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

                    VersionDBManager.init(this);
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
                    Log.d("USER DTO LOGIN", userDTO.toString());
                    gotoNextActivity(ActivityMain.class, PropertyConstant.USER_DTO.toString(), userDTO);
                }else {
                    Toast.makeText(this, "An error occured ".concat(userDTO.getErr_msg()),Toast.LENGTH_SHORT).show();
                }
            }else{
                Log.d(stringAnErrorOccured, "Object result Login is not found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
