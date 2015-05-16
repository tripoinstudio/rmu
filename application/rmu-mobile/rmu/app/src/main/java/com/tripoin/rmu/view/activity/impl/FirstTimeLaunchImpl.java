package com.tripoin.rmu.view.activity.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.tripoin.rmu.feature.synchronizer.impl.SynchronizeMaster;
import com.tripoin.rmu.model.DTO.master.MasterVersionItem;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.model.persist.TrainModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.api.IFirstTimeLaunch;
import com.tripoin.rmu.view.activity.api.ISignHandler;
import com.tripoin.rmu.view.fragment.api.ISynchronizeMaster;

import java.util.List;

/**
 * Created by Achmad Fauzi on 2/23/2015.
 * achmad.fauzi@sigma.co.id
 */
public class FirstTimeLaunchImpl implements IFirstTimeLaunch, ISynchronizeMaster {

    private Context context;
    private SharedPreferences runCheck;
    private ISignHandler iSignHandler;
    private PropertyUtil securityUtil;

    public FirstTimeLaunchImpl(Context context, ISignHandler signHandler, PropertyUtil securityUtil) {
        this.context = context;
        runCheck = context.getSharedPreferences("hasRunBefore", 0);
        iSignHandler = signHandler;
        this.securityUtil = securityUtil;
    }

    @Override
    public boolean isNotFirstTimeLaunch() {
        Log.d("NOT", "FTL1");
        Boolean hasRun = runCheck.getBoolean("hasRun", false);
        setFirstTimeLaunchStatus(true);
        if ( !hasRun ) {
            Log.d("NOT", "FTL2");
            VersionModel versionModel;
            try{
                versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, "master_seat");
            }catch (Exception e){
                String [] TableDATA = {
                        ModelConstant.REST_ORDER_DETAIL_TABLE,
                        ModelConstant.REST_ORDER_HEADER_TABLE,
                        ModelConstant.REST_MENU_TABLE,
                        ModelConstant.REST_CARRIAGE_TABLE,
                        ModelConstant.REST_IMAGE_TABLE,
                        ModelConstant.REST_MASTER_TABLE,
                        ModelConstant.REST_SEAT_TABLE,
                        ModelConstant.REST_TRAIN_TABLE,
                };
                for( String tableData : TableDATA ){
                    versionModel = new VersionModel();
                    versionModel.setVersionNameTable(tableData);
                    versionModel.setVersionTimestamp("01-01-2000 23:59:59.0");
                    VersionDBManager.getInstance().insertEntity(versionModel);
                }
            }
            new MasterSync().execute();
            if( iSignHandler.checkLoginStatus() ){
                Log.d("NOT", "FTL3");
                iSignHandler.signOut();
            }
        }
        return hasRun;
    }

    private class MasterSync extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            SynchronizeMaster synchronizeMaster = new SynchronizeMaster(securityUtil, context, ModelConstant.REST_MASTER_TABLE,  FirstTimeLaunchImpl.this );
            synchronizeMaster.detectVersionDiff();
            return null;
        }
    }

    public boolean isNotFirstTimeLaunchWithoutSignOut() {
        Boolean hasRun = runCheck.getBoolean("hasRun", false);
        setFirstTimeLaunchStatus( true );
        return hasRun;
    }

    @Override
    public void setFirstTimeLaunchStatus(boolean status) {
        SharedPreferences.Editor edit = runCheck.edit();
        edit.putBoolean( "hasRun", status );
        edit.commit();
    }

    @Override
    public void onPostFirstSyncMasterCarriage(List<CarriageModel> carriageModels) {

    }

    @Override
    public void onPostContSyncMasterCarriage(List<CarriageModel> carriageModels) {

    }

    @Override
    public void onPostFirstSyncMasterSeat(List<SeatModel> seatModels) {

    }

    @Override
    public void onPostContSyncMasterSeat(List<SeatModel> seatModels) {

    }

    @Override
    public void onPostFirstSyncMasterTrain(List<TrainModel> trainModels) {

    }

    @Override
    public void onPostContSyncMasterTrain(List<TrainModel> trainModels) {

    }
}
