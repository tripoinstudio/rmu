package com.tripoin.rmu.view.fragment.impl;

import android.content.Context;

import com.tripoin.rmu.model.DTO.train.TrainDTO;
import com.tripoin.rmu.model.DTO.train.TrainItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.TrainModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.TrainDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.ICarriagePost;
import com.tripoin.rmu.rest.api.ISeatPost;
import com.tripoin.rmu.rest.api.ITrainPost;
import com.tripoin.rmu.rest.impl.TrainListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.base.ASynchronizeData;

/**
 * Created by bangkit on 5/2/2015.
 */
public class SynchronizeMaster extends ASynchronizeData implements ICarriagePost, ISeatPost, ITrainPost {

    private String tableNameTrain;
    private String tableNameCarriage;
    private String tableNameSeat;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;

    protected SynchronizeMaster(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    protected SynchronizeMaster(PropertyUtil securityUtil, Context context, String tableNameCarriage, String tableNameSeat, String tableNameTrain) {
        super(securityUtil, context);
        this.tableNameTrain = tableNameTrain;
        this.tableNameCarriage = tableNameCarriage;
        this.tableNameSeat = tableNameSeat;
        this.context = context;
        this.securityUtil = securityUtil;
        TrainDBManager.init(context);
    }

    @Override
    public void updateContent(String latestVersion) {
        this.latestVersion = latestVersion;
        //drop
        TrainDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.TRAIN_TABLE));
        //select new Object
        TrainListRest trainListRest = new TrainListRest(this) {
            @Override
            protected Context getContext() {
                return context;
            }
        };
        trainListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public String getTableNameTrain() {
        return tableNameTrain;
    }

    @Override
    public void selectRelatedTable() {

    }

    @Override
    public void onPostSyncTrain(Object objectResult) {
        if(objectResult != null){
            TrainDTO trainDTO = (TrainDTO) objectResult;
            TrainModel trainModel = null;
            for(TrainItemDTO itemDTO : trainDTO.getTrainItemDTOs()){
                trainModel = new TrainModel();
                trainModel.setTrainCode(itemDTO.getTrainCode());
                trainModel.setTrainNo(itemDTO.getTrainNo());
                trainModel.setTrainRemarks(itemDTO.getTrainRemaks());
                TrainDBManager.getInstance().insertEntity(trainModel);
            }

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableNameTrain());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);
        }else{

        }
    }

    @Override
    public void onPostSyncCarriage(Object objectResult) {

    }

    @Override
    public void onPostSyncSeat(Object objectResult) {

    }
}
