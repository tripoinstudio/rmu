package com.tripoin.rmu.view.fragment.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.model.DTO.carriage.CarriageDTO;
import com.tripoin.rmu.model.DTO.carriage.CarriageItemDTO;
import com.tripoin.rmu.model.DTO.menu.MenuDTO;
import com.tripoin.rmu.model.DTO.menu.MenuItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.CarriageDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.MenuDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.ICarriagePost;
import com.tripoin.rmu.rest.impl.CarriageListRest;
import com.tripoin.rmu.rest.impl.MenuListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.base.ASynchronizeData;

import java.util.List;

/**
 * Created by bangkit on 5/2/2015.
 */
public class SynchronizeCarriage extends ASynchronizeData implements ICarriagePost {

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;

    protected SynchronizeCarriage(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    protected SynchronizeCarriage(PropertyUtil securityUtil, Context context, String tableName) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.securityUtil = securityUtil;
        CarriageDBManager.init(context);
    }

    @Override
    public void onPostSyncCarriage(Object objectResult) {
        if(objectResult != null){
            CarriageDTO carriageDTO = (CarriageDTO) objectResult;
            /*List<MenuModel> menuModels = new ArrayList<MenuModel>();*/
            CarriageModel carriageModel = null;
            for(CarriageItemDTO itemDTO : carriageDTO.getCarriageItemDTOs()){
                carriageModel = new CarriageModel();
                carriageModel.setCarriageCode(itemDTO.getCarriageCode());
                carriageModel.setCarriageNo(itemDTO.getCarriageNo());
                carriageModel.setCarriageRemarks(itemDTO.getCarriageRemarks());
                /*carriageModels.add(carriageModel);*/
                CarriageDBManager.getInstance().insertEntity(carriageModel);
            }

            Log.d("CARRIAGE", "post detect version");
            List<CarriageModel> carriageModels = CarriageDBManager.getInstance().getAllData();
            for(CarriageModel model: carriageModels){
                Log.d("CarriageModel", model.toString());
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
        CarriageDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.CARRIAGE_TABLE));
        //select new Object
        CarriageListRest carriageListRest = new CarriageListRest(this) {
            @Override
            protected Context getContext() {
                return context;
            }
        };
        carriageListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public String getTableName() {
        return tableName;
    }
}
