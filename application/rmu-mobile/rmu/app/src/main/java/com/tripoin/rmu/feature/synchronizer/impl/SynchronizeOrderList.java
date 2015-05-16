package com.tripoin.rmu.feature.synchronizer.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderListDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.IOrderListPost;
import com.tripoin.rmu.rest.impl.OrderHeaderListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderList;
import com.tripoin.rmu.feature.synchronizer.base.ASynchronizeData;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 2:49 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class SynchronizeOrderList extends ASynchronizeData implements IOrderListPost, ISynchronizeOrderList {

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;
    private ISynchronizeOrderList iSynchronizeOrderList;

    public SynchronizeOrderList(PropertyUtil securityUtil, Context context, String tableName, ISynchronizeOrderList iSynchronizeOrderList) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.securityUtil = securityUtil;
        OrderListDBManager.init(context);
        this.iSynchronizeOrderList = iSynchronizeOrderList;
    }

    @Override
    public void updateContent(String latestVersion) {
        this.latestVersion = latestVersion;
        //drop
        OrderListDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.ORDER_LIST_TABLE));

        //select new Object
        OrderHeaderListRest orderHeaderListRest = new OrderHeaderListRest(this) {
            @Override
            public Context getContext() {
                return context;
            }
        };
        orderHeaderListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void selectRelatedTable() {
        onPostContSyncOrderList(OrderListDBManager.getInstance().getAllData());
    }

    @Override
    public void onPostSynchronize(Object objectResult) {
        if(objectResult != null){
            OrderHeaderDTO orderHeaderDTO = (OrderHeaderDTO) objectResult;
            OrderListModel orderListModel = null;
            for(OrderHeaderItemDTO itemDTO : orderHeaderDTO.getOrderHeaderItemDTOs()){
                orderListModel = new OrderListModel();
                orderListModel.setOrderId(itemDTO.getOrderHeaderNo());
                orderListModel.setCarriageNumber(itemDTO.getCarriageNo());
                orderListModel.setSeatNumber(itemDTO.getSeatNo());
                orderListModel.setTotalPaid(itemDTO.getOrderTotalPaid());
                orderListModel.setOrderTime(itemDTO.getOrderHeaderDateTime());
                orderListModel.setProcessStatus(Integer.parseInt(itemDTO.getOrderHeaderStatus()));

                OrderListDBManager.getInstance().insertEntity(orderListModel);
            }

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);

            List<OrderListModel> orderListModelList = OrderListDBManager.getInstance().getAllData();
            iSynchronizeOrderList.onPostFirstSyncOrderList(orderListModelList);
        }else{
            Log.d("Sync Orderlist Object Result", "not found");
        }
    }

    @Override
    public void onPostFirstSyncOrderList(List<OrderListModel> orderListModels) {
        iSynchronizeOrderList.onPostFirstSyncOrderList(orderListModels);
    }

    @Override
    public void onPostContSyncOrderList(List<OrderListModel> orderListModels) {
        iSynchronizeOrderList.onPostContSyncOrderList(orderListModels);
    }
}
