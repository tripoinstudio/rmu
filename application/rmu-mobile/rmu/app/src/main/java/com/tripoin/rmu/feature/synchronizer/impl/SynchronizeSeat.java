package com.tripoin.rmu.feature.synchronizer.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.feature.synchronizer.base.ASynchronizeData;
import com.tripoin.rmu.model.DTO.seat.SeatDTO;
import com.tripoin.rmu.model.DTO.seat.SeatItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.SeatDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.ISeatPost;
import com.tripoin.rmu.rest.impl.SeatListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;

import java.util.List;

/**
 * Created by bangkit on 5/2/2015.
 */
public class SynchronizeSeat extends ASynchronizeData implements ISeatPost {

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;

    protected SynchronizeSeat(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    protected SynchronizeSeat(PropertyUtil securityUtil, Context context, String tableName) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.securityUtil = securityUtil;
        SeatDBManager.init(context);
    }

    @Override
    public void onPostSyncSeat(Object objectResult) {
        if(objectResult != null){
            SeatDTO seatDTO = (SeatDTO) objectResult;
            /*List<MenuModel> menuModels = new ArrayList<MenuModel>();*/
            SeatModel seatModel = null;
            for(SeatItemDTO itemDTO : seatDTO.getSeatItemDTOs()){
                seatModel = new SeatModel();
                seatModel.setSeatCode(itemDTO.getSeatCode());
                seatModel.setSeatNo(itemDTO.getSeatNo());
                seatModel.setSeatRemarks(itemDTO.getSeatRemarks());
                /*carriageModels.add(carriageModel);*/
                SeatDBManager.getInstance().insertEntity(seatModel);
            }

            Log.d("SEAT", "post detect version");
            List<SeatModel> seatModels = SeatDBManager.getInstance().getAllData();
            for(SeatModel model: seatModels){
                Log.d("SeatModel", model.toString());
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
        SeatDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.SEAT_TABLE));
        //select new Object
        SeatListRest seatListRest = new SeatListRest(this) {
            @Override
            protected Context getContext() {
                return context;
            }
        };
        seatListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void selectRelatedTable() {

    }
}
