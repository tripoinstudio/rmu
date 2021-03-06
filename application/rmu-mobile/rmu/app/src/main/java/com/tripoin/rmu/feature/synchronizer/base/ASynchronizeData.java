package com.tripoin.rmu.feature.synchronizer.base;

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
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public abstract class ASynchronizeData implements ISynchronizeData, IMasterVersionPost{

    private Context context;
    private PropertyUtil securityUtil;
    protected VersionModel versionModel;

    protected ASynchronizeData(PropertyUtil securityUtil, Context context) {
        this.securityUtil = securityUtil;
        this.context = context;
        VersionDBManager.init(context);
    }

    public void detectVersionDiff() {
        MasterVersionRest masterVersionRest = new MasterVersionRest(this) {
            @Override
            public Context getContext() {
                return context;
            }
        };
        masterVersionRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public void onSyncMasterVersionPost(Object objectResult) {
        if( objectResult != null ){
            MasterVersion masterVersion = (MasterVersion) objectResult;
            for(MasterVersionItem versionItem : masterVersion.getMasterVersionItems()){
                if(versionItem.getVersionTable().equals(getTableName())){
                    int diff = tableDiff(versionItem.getVersionTimeStamp());
                    //VersionModel versionModel = (VersionModel) VersionDBManager.getInstance().getAllData().get(0);
                    Log.d("DIFF", String.valueOf(diff));
                    if( diff != 0 ) {
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
        Date newVersion = null;
        Date oldVersion = null;
        int result = 0;
        try{
            versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            GeneralConverter generalConverter = new GeneralConverter();
            newVersion = generalConverter.getDateToComparator(latestVersion);
            oldVersion = generalConverter.getDateToComparator(versionModel.getVersionTimestamp());
            result = oldVersion.compareTo(newVersion);
        }catch (Exception e){
            e.printStackTrace();
            result = -1;
        }
        return result;
    }
}
