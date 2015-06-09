package com.tripoin.rmu.feature.synchronizer.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.feature.synchronizer.base.ASynchronizeData;
import com.tripoin.rmu.feature.version.IVersionPost;
import com.tripoin.rmu.feature.version.Version;
import com.tripoin.rmu.model.DTO.master.MasterVersion;
import com.tripoin.rmu.model.DTO.master.MasterVersionItem;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderListDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.IMasterVersionPost;
import com.tripoin.rmu.rest.api.IOrderListPost;
import com.tripoin.rmu.rest.impl.BackgroundOrderHeaderListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeMaster;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderList;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 2:49 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class BackgroundSynchronizeApplicationVersion extends ASynchronizeData implements IMasterVersionPost {


    private String tableName;
    private Context context;
    private IVersionPost iVersionPost;


    public BackgroundSynchronizeApplicationVersion(PropertyUtil securityUtil, Context context, String tableName, IVersionPost iVersionPost) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.iVersionPost = iVersionPost;
    }

    protected BackgroundSynchronizeApplicationVersion(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    @Override
    public void updateContent(String latestVersion) {
        Log.d("Application needs to update to version", latestVersion);
        iVersionPost.onPostVersion(latestVersion);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void selectRelatedTable() {
        /*Do nothing because current application version equals server version*/
    }

    @Override
    public void onSyncMasterVersionPost(Object objectResult) {
        if( objectResult != null ){
            MasterVersion masterVersion = (MasterVersion) objectResult;
            for(MasterVersionItem versionItem : masterVersion.getMasterVersionItems()){
                if(versionItem.getVersionTable().equals(getTableName())){
                    String latestVersion = versionItem.getVersionRemarks().substring(versionItem.getVersionRemarks().indexOf("V") + 1, versionItem.getVersionRemarks().length() - 4);
                    int diff = tableDiff(latestVersion);
                    if( diff != 0 ) {
                        updateContent(latestVersion);
                    }else{
                        selectRelatedTable();
                        //updateContent();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public int tableDiff(String latestVersion) {
        Version version = new Version(context);
        return version.compareVersions(version.getVersionName(context), latestVersion);
    }
}
