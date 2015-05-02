package com.tripoin.rmu.view.fragment.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.model.DTO.carriage.CarriageDTO;
import com.tripoin.rmu.model.DTO.carriage.CarriageItemDTO;
import com.tripoin.rmu.model.DTO.train.TrainDTO;
import com.tripoin.rmu.model.DTO.train.TrainItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.TrainModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.CarriageDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.TrainDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.ICarriagePost;
import com.tripoin.rmu.rest.api.ITrainPost;
import com.tripoin.rmu.rest.impl.CarriageListRest;
import com.tripoin.rmu.rest.impl.TrainListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.base.ASynchronizeData;

import java.util.List;

/**
 * Created by bangkit on 5/2/2015.
 */
public class SynchronizeTrain extends ASynchronizeData implements ITrainPost {

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;

    protected SynchronizeTrain(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    protected SynchronizeTrain(PropertyUtil securityUtil, Context context, String tableName) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.securityUtil = securityUtil;
        TrainDBManager.init(context);
    }

    @Override
    public void onPostSyncTrain(Object objectResult) {
        if(objectResult != null){
            TrainDTO trainDTO = (TrainDTO) objectResult;
            /*List<MenuModel> menuModels = new ArrayList<MenuModel>();*/
            TrainModel trainModel = null;
            for(TrainItemDTO itemDTO : trainDTO.getTrainItemDTOs()){
                trainModel = new TrainModel();
                trainModel.setTrainCode(itemDTO.getTrainCode());
                trainModel.setTrainNo(itemDTO.getTrainNo());
                trainModel.setTrainRemarks(itemDTO.getTrainRemaks());
                /*carriageModels.add(carriageModel);*/
                TrainDBManager.getInstance().insertEntity(trainModel);
            }

            Log.d("TRAIN", "post detect version");
            List<TrainModel> trainModels = TrainDBManager.getInstance().getAllData();
            for(TrainModel model: trainModels){
                Log.d("TrainModel", model.toString());
            }

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);
        }else{
//            Log.d("Sync Carriage Object Result", "not found");
        }
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
    public String getTableName() {
        return tableName;
    }
}
