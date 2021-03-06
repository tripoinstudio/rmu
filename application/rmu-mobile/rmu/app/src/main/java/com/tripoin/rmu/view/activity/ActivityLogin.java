package com.tripoin.rmu.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.DialogInterface;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.DTO.user.UserDTO;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;
import com.tripoin.rmu.rest.api.IConnectionPost;
import com.tripoin.rmu.rest.api.ILoginPost;
import com.tripoin.rmu.rest.impl.ConnectionRest;
import com.tripoin.rmu.rest.impl.LoginRest;
import com.tripoin.rmu.util.GeneralConverter;
import com.tripoin.rmu.util.GeneralValidation;
import com.tripoin.rmu.util.NetworkConnectivity;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.api.IFirstSignHandler;
import com.tripoin.rmu.view.activity.base.ABaseActivity;
import com.tripoin.rmu.view.activity.impl.FirstSignHandlerImpl;
import com.tripoin.rmu.view.enumeration.ViewConstant;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Achmad Fauzi on 9/21/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */

public class ActivityLogin extends ABaseActivity implements ILoginPost, IConnectionPost {

    @InjectView(R.id.txt_username) EditText txtUserName;
    @InjectView(R.id.txt_password) EditText txtPassword;
    @InjectView(R.id.bt_setting_ip_bt) ImageView btSettingIpBt;


    @InjectView(R.id.chkShowPassword) CheckBox chkShowPassword;
    TextView lbTestStatusDialog;

    private GeneralValidation generalValidation;
    private GeneralConverter generalConverter;
    private NetworkConnectivity networkConnectivity;

    private String chipperAuth;
    private String userName;
    private PropertyUtil securityUtil;
    private IFirstSignHandler iSignHandler;

    @Override
    public void initWidget() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), getApplicationContext());
        generalValidation = new GeneralValidation();
        generalConverter = new GeneralConverter();
        networkConnectivity = new NetworkConnectivity(this);
        iSignHandler = new FirstSignHandlerImpl(securityUtil, this);
        iSignHandler.detectLoginStatus();
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
        iSignHandler.detectLoginStatus();
    }

    @OnClick(R.id.btSignIn)
    public void signIn(){
        if(!generalValidation.isEmptyEditText(txtUserName)){
            userName = txtUserName.getText().toString();
            if(!generalValidation.isEmptyEditText(txtPassword)){
                chipperAuth = generalConverter.encodeToBase64(txtUserName.getText().toString()
                        .concat(ViewConstant.COLON.toString())
                        .concat(txtPassword.getText().toString()));
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
                    iSignHandler.signIn(userDTO, userName, chipperAuth);
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

    @OnClick(R.id.bt_setting_ip_bt)
    public void btSetIpBt(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View dialogView = layoutInflater.inflate(R.layout.fragment_ip_server_dialog, null);
        final TextView tfIpDialog = (TextView) dialogView.findViewById(R.id.tfIpDialog);
        final TextView tfPortDialog = (TextView) dialogView.findViewById(R.id.tfPortDialog);
        lbTestStatusDialog = (TextView) dialogView.findViewById(R.id.lbTestStatusDialog);
        Button testConnDialog = (Button) dialogView.findViewById(R.id.testConnDialog);
        final PropertyUtil propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), this);
        tfIpDialog.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString()));
        tfPortDialog.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString()));

        tfIpDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(dialogView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);
                TextView lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                final EditText tfEditText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                lblEditText.setText("Enter IP Address");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(dialogView.getContext());
                alertDialogBuilder.setView(dialogView);
                tfEditText.setText(tfIpDialog.getText().toString());
                tfEditText.setSelection(tfEditText.getText().length());

                alertDialogBuilder.setCancelable(true).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty(PropertyConstant.SERVER_HOST_KEY.toString(), tfEditText.getText().toString());
                        tfIpDialog.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString()));
                        Toast.makeText(ActivityLogin.this,"IP Server Change to : " +propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString()), Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }
        });

        tfPortDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(dialogView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);
                TextView lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                final EditText tfEditText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                lblEditText.setText("Enter Port");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(dialogView.getContext());
                alertDialogBuilder.setView(dialogView);
                tfEditText.setText(tfPortDialog.getText().toString());
                tfEditText.setSelection(tfEditText.getText().length());

                alertDialogBuilder.setCancelable(true).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty(PropertyConstant.SERVER_PORT_KEY.toString(), tfEditText.getText().toString());
                        tfPortDialog.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString()));
                        Toast.makeText(ActivityLogin.this,"Port Change to : " +propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString()), Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }
        });

        testConnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionRest connectionRest = new ConnectionRest(ActivityLogin.this) {
                    @Override
                    public Context getContext() {
                        return ActivityLogin.this;
                    }
                };
                connectionRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
            }
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(dialogView);
        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

    @Override
    public void onPostConnectionTest(Object objectResult) {
        if (objectResult != null) {
            BaseRESTDTO baseRESTDTO = (BaseRESTDTO) objectResult;
            if (baseRESTDTO.getErr_code().equals(ViewConstant.ZERO.toString())) {
                lbTestStatusDialog.setText(baseRESTDTO.getErr_msg());
                lbTestStatusDialog.setTextColor(getResources().getColor(R.color.green_base));
            } else {
                lbTestStatusDialog.setText("Connection Failed");
                lbTestStatusDialog.setTextColor(getResources().getColor(R.color.red_dark_holo));
            }
        } else {
            lbTestStatusDialog.setText("Connection Failed");
            lbTestStatusDialog.setTextColor(getResources().getColor(R.color.red_dark_holo));
        }
    }

}
