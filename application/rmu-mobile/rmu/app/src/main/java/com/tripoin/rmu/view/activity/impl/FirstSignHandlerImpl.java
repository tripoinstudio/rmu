package com.tripoin.rmu.view.activity.impl;

import android.util.Log;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.DTO.master.MasterVersionItem;
import com.tripoin.rmu.model.DTO.user.UserDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderDetailDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderListDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderTempDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.ActivityLogin;
import com.tripoin.rmu.view.activity.ActivityMain;
import com.tripoin.rmu.view.activity.api.IFirstSignHandler;
import com.tripoin.rmu.view.activity.api.ISignHandler;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 11:09 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FirstSignHandlerImpl implements IFirstSignHandler {

    private PropertyUtil securityUtil;
    private ActivityLogin activityLogin;

    public FirstSignHandlerImpl(PropertyUtil securityUtil, ActivityLogin activityLogin) {
        this.securityUtil = securityUtil;
        this.activityLogin = activityLogin;
    }


    @Override
    public void signOut() {
        securityUtil.saveSingleProperty(PropertyConstant.LOGIN_STATUS_KEY.toString(), PropertyConstant.LOGOUT_STATUS_VALUE.toString());

    }

    @Override
    public void detectLoginStatus() {
        Log.d("FIRST SIGN HANDLER", String.valueOf(checkLoginStatus()));
        if(checkLoginStatus()){
            activityLogin.gotoNextActivity(ActivityMain.class, ViewConstant.EMPTY.toString(), ViewConstant.EMPTY.toString());
        }
    }

    @Override
    public boolean checkLoginStatus() {
        boolean result;
        try {
            result = (!securityUtil.getValuePropertyMap(PropertyConstant.USER_NAME.toString()).equals(ViewConstant.EMPTY.toString())) && !(securityUtil.getValuePropertyMap(PropertyConstant.LOGIN_STATUS_KEY.toString()).equals(PropertyConstant.LOGOUT_STATUS_VALUE.toString()));
        }catch ( Exception e){
            result = false;
        }
        return result;
    }

    @Override
    public void signIn(UserDTO userDTO, String userName, String chipperAuth) {
        String prevUserName = securityUtil.getValuePropertyMap(PropertyConstant.USER_NAME.toString());
        Log.d("prev "+prevUserName, "current "+userName);
        if(prevUserName != null){
            if( ! prevUserName.equals(userName) ){
                try{
                    OrderListDBManager.init(activityLogin);
                    OrderTempDBManager.init(activityLogin);
                    OrderDetailDBManager.init(activityLogin);
                    VersionDBManager.init(activityLogin);


                    OrderListDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.ORDER_LIST_TABLE));
                    VersionModel versionModel = (VersionModel) VersionDBManager.getInstance().getDataFromQuery(ModelConstant.VERSION_NAMETABLE, ModelConstant.REST_ORDER_HEADER_TABLE);
                    versionModel.setVersionTimestamp(ModelConstant.TIME_STAMP_RESET);
                    VersionDBManager.getInstance().updateEntity(versionModel);

                    OrderTempDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.ORDER_TEMP_TABLE));

                    OrderDetailDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.ORDER_DETAIL_TABLE));
                    VersionModel vm = (VersionModel) VersionDBManager.getInstance().getDataFromQuery(ModelConstant.VERSION_NAMETABLE, ModelConstant.REST_ORDER_DETAIL_TABLE);
                    vm.setVersionTimestamp(ModelConstant.TIME_STAMP_RESET);
                    VersionDBManager.getInstance().updateEntity(vm);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            Log.d("PRev user name "+prevUserName, "CUrrent user Name "+userName);
        }

        securityUtil.saveSingleProperty(PropertyConstant.USER_NAME.toString(), userName);
        securityUtil.saveSingleProperty(PropertyConstant.LOGIN_STATUS_KEY.toString(), PropertyConstant.LOGIN_STATUS_VALUE.toString());
        securityUtil.saveSingleProperty(PropertyConstant.CHIPPER_AUTH.toString(), chipperAuth);

        VersionModel versionModel = null;
        try{
            versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, ModelConstant.REST_SEAT_TABLE);
        }catch (Exception e){
            for( MasterVersionItem masterVersion : userDTO.getMasterVersionItems() ){
                versionModel = new VersionModel();
                versionModel.setVersionNameTable(masterVersion.getVersionTable());
                versionModel.setVersionTimestamp(ModelConstant.TIME_STAMP_RESET);
                VersionDBManager.getInstance().insertEntity(versionModel);
            }
        }
        activityLogin.gotoNextActivity(ActivityMain.class, PropertyConstant.USER_DTO.toString(), userDTO);
    }


}
