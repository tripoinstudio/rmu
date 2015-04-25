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
import com.tripoin.rmu.model.DTO.user.UserDTO;
import com.tripoin.rmu.rest.api.ILoginPost;
import com.tripoin.rmu.rest.impl.LoginRest;
import com.tripoin.rmu.security.api.ASecureActivity;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.view.enumeration.ViewConstant;

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
    public void setupValues() {
        btSignIn.setOnClickListener(this);
        chkShowPassword.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btSignIn ){
            String userName = txtUserName.getText().toString();
            String password = txtPassword.getText().toString();
            if(!generalValidation.isEmptyEditText(txtUserName)){
                if(!generalValidation.isEmptyEditText(txtPassword)){
                    String data = generalConverter.encodeToBase64(userName.concat(ViewConstant.COLON.toString()).concat(password));
                    if( networkConnectivity.checkConnectivity() ){
                        LoginRest loginRest = new LoginRest(this) {
                            @Override
                            protected Context getContext() {
                                return ActivityLogin.this;
                            }
                        };
                        loginRest.execute(data);
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
                    propertyUtil.saveSingleProperty(PropertyConstant.USER_NAME.toString(), userDTO.getUserItemDTOs().getUserCode());
                    gotoNextActivity(ActivityMain.class, PropertyConstant.USER_NAME.toString(), userDTO.getUserItemDTOs().getUserCode());
                }else{
                    Toast.makeText(this, ViewConstant.ERROR.toString().
                            concat(ViewConstant.SPACE.toString()).
                            concat(userDTO.getErr_msg()), Toast.LENGTH_SHORT).show();
                }
            }else{
                Log.e(ViewConstant.ERROR.toString(), "Login Post is empty");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
