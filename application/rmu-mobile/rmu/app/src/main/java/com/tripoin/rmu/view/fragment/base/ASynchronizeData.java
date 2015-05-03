package com.tripoin.rmu.view.fragment.base;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.model.DTO.master.MasterVersion;
import com.tripoin.rmu.model.DTO.master.MasterVersionItem;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.IMasterVersionPost;
import com.tripoin.rmu.rest.impl.MasterVersionRest;
import com.tripoin.rmu.util.GeneralConverter;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeData;

import java.util.Date;

/**
 * Created by Achmad Fauzi on 4/30/2015 : 10:56 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract class ASynchronizeData implements ISynchronizeData, IMasterVersionPost{

    private Context context;
    private PropertyUtil securityUtil;

    protected ASynchronizeData(PropertyUtil securityUtil, Context context) {
        this.securityUtil = securityUtil;
        this.context = context;
        VersionDBManager.init(context);
    }

    public void detectVersionDiff() {
        Log.d("MENU", "3");
        MasterVersionRest masterVersionRest = new MasterVersionRest(this);
        masterVersionRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public void onSyncMasterVersionPost(Object objectResult) {
        if( objectResult != null ){
            Log.d("MENU", "4");
            MasterVersion masterVersion = (MasterVersion) objectResult;
            for(MasterVersionItem versionItem : masterVersion.getMasterVersionItems()){
                Log.d("MENU", "5");
                if(versionItem.getVersionTable().equals(getTableName())){
                    Log.d("MENU", "6");
                    int diff = tableDiff(versionItem.getVersionTimeStamp());
                    Log.d("diff", String.valueOf(diff));
                    //VersionModel versionModel = (VersionModel) VersionDBManager.getInstance().getAllData().get(0);
                    if( diff<0 ) {
                        Log.d("MENU", "version model null");
                        updateContent(versionItem.getVersionTimeStamp());
                    }else{
                        selectRelatedTable();
                        //updateContent();
                    }
                    break;
                }
            }
        }
    }

    public int tableDiff(String latestVersion) {
        Log.d("VERSIONSIZE", String.valueOf(VersionDBManager.getInstance().getAllData().size()));
        VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
        GeneralConverter generalConverter = new GeneralConverter();
        Date newVersion = generalConverter.getDateToComparator(latestVersion);
        Date oldVersion = generalConverter.getDateToComparator(versionModel.getVersionTimestamp());
        return oldVersion.compareTo(newVersion);
    }
}
