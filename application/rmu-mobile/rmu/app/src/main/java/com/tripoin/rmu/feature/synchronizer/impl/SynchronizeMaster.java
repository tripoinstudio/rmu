package com.tripoin.rmu.feature.synchronizer.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.feature.synchronizer.base.ASynchronizeData;
import com.tripoin.rmu.model.DTO.carriage.CarriageDTO;
import com.tripoin.rmu.model.DTO.carriage.CarriageItemDTO;
import com.tripoin.rmu.model.DTO.seat.SeatDTO;
import com.tripoin.rmu.model.DTO.seat.SeatItemDTO;
import com.tripoin.rmu.model.DTO.train.TrainDTO;
import com.tripoin.rmu.model.DTO.train.TrainItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.model.persist.TrainModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.CarriageDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.SeatDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.TrainDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.ICarriagePost;
import com.tripoin.rmu.rest.api.ISeatPost;
import com.tripoin.rmu.rest.api.ITrainPost;
import com.tripoin.rmu.rest.impl.CarriageListRest;
import com.tripoin.rmu.rest.impl.SeatListRest;
import com.tripoin.rmu.rest.impl.TrainListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeMaster;

import java.util.List;

/**
 * Created by bangkit on 5/2/2015.
 */
public class SynchronizeMaster extends ASynchronizeData implements ICarriagePost, ISeatPost, ITrainPost, ISynchronizeMaster {

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;
    private ISynchronizeMaster iSynchronizeMaster;
    private List<CarriageModel> carriageModels;
    private List<TrainModel> trainModels;
    private List<SeatModel> seatModels;

    protected SynchronizeMaster(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    public SynchronizeMaster(PropertyUtil securityUtil, Context context, String tableName, ISynchronizeMaster iSynchronizeMaster) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.securityUtil = securityUtil;
        this.iSynchronizeMaster = iSynchronizeMaster;
        TrainDBManager.init(context);
        CarriageDBManager.init(context);
        SeatDBManager.init(context);
    }

    @Override
    public void updateContent(String latestVersion) {
        this.latestVersion = latestVersion;

        TrainDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.TRAIN_TABLE));
        TrainListRest trainListRest = new TrainListRest(this) {
            @Override
            public Context getContext() {
                return context;
            }
        };

        CarriageDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.CARRIAGE_TABLE));
        CarriageListRest carriageListRest = new CarriageListRest(this) {
            @Override
            public Context getContext() {
                return context;
            }
        };

        SeatDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.SEAT_TABLE));
        SeatListRest seatListRest = new SeatListRest(this) {
            @Override
            public Context getContext() {
                return context;
            }
        };

        carriageListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
        seatListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
        trainListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void selectRelatedTable() {
        onPostContSyncMasterCarriage(CarriageDBManager.getInstance().getAllData());
        onPostContSyncMasterSeat(SeatDBManager.getInstance().getAllData());
        onPostContSyncMasterTrain(TrainDBManager.getInstance().getAllData());
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

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);

            trainModels = TrainDBManager.getInstance().getAllData();
            for(TrainModel trainModel1:trainModels){
                Log.d("Train data",trainModel1.toString());
            }
            iSynchronizeMaster.onPostFirstSyncMasterTrain(trainModels);
        }else{

        }
    }

    @Override
    public void onPostSyncCarriage(Object objectResult) {
        if(objectResult != null){
            CarriageDTO carriageDTO = (CarriageDTO) objectResult;
            CarriageModel carriageModel = null;
            for(CarriageItemDTO itemDTO : carriageDTO.getCarriageItemDTOs()){
                carriageModel = new CarriageModel();
                carriageModel.setCarriageCode(itemDTO.getCarriageCode());
                carriageModel.setCarriageNo(itemDTO.getCarriageNo());
                carriageModel.setCarriageRemarks(itemDTO.getCarriageRemarks());
                CarriageDBManager.getInstance().insertEntity(carriageModel);
            }

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);

            carriageModels = CarriageDBManager.getInstance().getAllData();
            for(CarriageModel carriageModel1:carriageModels){
                Log.d("Carriage data",carriageModel1.toString());
            }
            iSynchronizeMaster.onPostFirstSyncMasterCarriage(carriageModels);
        }else{

        }
    }

    @Override
    public void onPostSyncSeat(Object objectResult) {
        if(objectResult != null){
            SeatDTO seatDTO = (SeatDTO) objectResult;
            SeatModel seatModel = null;
            for(SeatItemDTO itemDTO : seatDTO.getSeatItemDTOs()){
                seatModel = new SeatModel();
                seatModel.setSeatCode(itemDTO.getSeatCode());
                seatModel.setSeatNo(itemDTO.getSeatNo());
                seatModel.setSeatRemarks(itemDTO.getSeatRemarks());
                SeatDBManager.getInstance().insertEntity(seatModel);
            }

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);

            seatModels = SeatDBManager.getInstance().getAllData();
            for(SeatModel seatModel1:seatModels){
                Log.d("Seat data",seatModel1.toString());
            }
            iSynchronizeMaster.onPostFirstSyncMasterSeat(seatModels);
        }else{

        }
    }

    @Override
    public void onPostFirstSyncMasterCarriage(List<CarriageModel> carriageModels) {
        iSynchronizeMaster.onPostFirstSyncMasterCarriage(carriageModels);
    }

    @Override
    public void onPostContSyncMasterCarriage(List<CarriageModel> carriageModels) {
        iSynchronizeMaster.onPostContSyncMasterCarriage(carriageModels);
    }

    @Override
    public void onPostFirstSyncMasterSeat(List<SeatModel> seatModels) {
        iSynchronizeMaster.onPostFirstSyncMasterSeat(seatModels);
    }

    @Override
    public void onPostContSyncMasterSeat(List<SeatModel> seatModels) {
        iSynchronizeMaster.onPostContSyncMasterSeat(seatModels);
    }

    @Override
    public void onPostFirstSyncMasterTrain(List<TrainModel> trainModels) {
        iSynchronizeMaster.onPostFirstSyncMasterTrain(trainModels);
    }

    @Override
    public void onPostContSyncMasterTrain(List<TrainModel> trainModels) {
        iSynchronizeMaster.onPostContSyncMasterTrain(trainModels);
    }
}
