package com.tripoin.rmu.view.activity;


import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.security.api.ASecureActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Achmad Fauzi on 9/21/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */

@ContentView(R.layout.activity_login)
public class ActivityLogin extends ASecureActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.txt_username) private EditText txtUserName;
    @InjectView(R.id.txt_password) private EditText txtPassword;
    @InjectView(R.id.btSignIn) private Button btSignIn;
    @InjectView(R.id.chkShowPassword) private CheckBox chkShowPassword;

    @Override
    protected int getOptionMenuLayoutId() {
        return
                R.menu.empty_menu;
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
                    /*String data = generalConverter.encodeToBase64(userName.concat(":").concat(password));
                    Log.d("DATA", data);
                    new LoginRest(userName, password, this).execute(data);*/
                    gotoNextActivity(ActivityMain.class, "user_name", userName);
                }else{
                    Toast.makeText(this, "Password can not be empty", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "User name can not be empty", Toast.LENGTH_SHORT).show();
            }
            /*if( userName.equals("admin") && password.equals("admin") ){

            }*/
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
}
