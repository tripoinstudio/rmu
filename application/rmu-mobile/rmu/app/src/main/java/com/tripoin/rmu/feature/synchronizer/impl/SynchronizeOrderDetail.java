package com.tripoin.rmu.feature.synchronizer.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.feature.synchronizer.base.ASynchronizeData;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailDTO;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderDetailDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.IOrderDetailPost;
import com.tripoin.rmu.rest.impl.OrderDetailListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderDetail;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 7:56 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public abstract class SynchronizeOrderDetail extends ASynchronizeData implements IOrderDetailPost, ISynchronizeOrderDetail {

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;
    private ISynchronizeOrderDetail iSynchronizeOrderDetail;
    private List<OrderDetailModel> orderDetailModels;

    protected SynchronizeOrderDetail(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    protected SynchronizeOrderDetail(PropertyUtil securityUtil, Context context, String tableName, ISynchronizeOrderDetail iSynchronizeOrderDetail) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.securityUtil = securityUtil;
        OrderDetailDBManager.init(context);
        this.iSynchronizeOrderDetail = iSynchronizeOrderDetail;
    }

    @Override
    public void updateContent(String latestVersion) {
        this.latestVersion = latestVersion;
        OrderDetailDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.ORDER_DETAIL_TABLE));
        OrderDetailListRest orderDetailListRest = new OrderDetailListRest(this) {
            @Override
            public Context getContext() {
                return context;
            }

            @Override
            public String getOrderHeaderId() {
                return getOrderHeader();
            }
        };
        orderDetailListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void selectRelatedTable() {
        onPostContSyncOrderDetail((OrderDetailModel) OrderDetailDBManager.getInstance().getAllData().get(0), OrderDetailDBManager.getInstance().getAllData());
    }

    public abstract String getOrderHeader();

    @Override
    public void onPostSynchronize(Object objectResult) {
        if(objectResult != null){
            OrderDetailDTO orderDetailDTO = (OrderDetailDTO) objectResult;
            /*List<MenuModel> menuModels = new ArrayList<MenuModel>();*/
            OrderDetailModel orderDetailModel = null;
            for(OrderDetailItemDTO itemDTO : orderDetailDTO.getOrderDetailItemDTOs()){
                orderDetailModel = new OrderDetailModel();
                orderDetailModel.setOrderHeaderNo(itemDTO.getOrderHeaderNo());
                orderDetailModel.setOrderDetailTotalOrder(itemDTO.getOrderDetailTotalOrder());
                orderDetailModel.setOrderDetailTotalAmount(itemDTO.getOrderDetailTotalAmount());
                orderDetailModel.setOrderHeaderStatus(itemDTO.getOrderHeaderStatus());
                orderDetailModel.setUserCode(itemDTO.getUserCode());
                orderDetailModel.setMenuCode(itemDTO.getMenuCode());
                orderDetailModel.setMenuName(itemDTO.getMenuName());
                orderDetailModel.setSeatCode(itemDTO.getSeatCode());
                orderDetailModel.setCarriageCode(itemDTO.getCarriageCode());
                orderDetailModel.setTrainCode(itemDTO.getTrainCode());
                OrderDetailDBManager.getInstance().insertEntity(orderDetailModel);
            }

            /*VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);*/

            orderDetailModels = OrderDetailDBManager.getInstance().getAllData();
            for (OrderDetailModel detailModel: orderDetailModels){
                Log.d("ordermke", detailModel.toString());
            }
            iSynchronizeOrderDetail.onPostFirstSyncOrderDetail(orderDetailModel, orderDetailModels);
        }else{
            Log.d("Sync Orderlist Object Result", "not found");
        }
    }

    @Override
    public void onPostFirstSyncOrderDetail(OrderDetailModel orderDetailModel, List<OrderDetailModel> orderDetailModels) {
        for(OrderDetailModel detailModel: orderDetailModels){
            Log.d("post first sync", detailModel.toString());
        }
        iSynchronizeOrderDetail.onPostFirstSyncOrderDetail(orderDetailModel, orderDetailModels);
    }

    @Override
    public void onPostContSyncOrderDetail(OrderDetailModel orderDetailModel, List<OrderDetailModel> orderDetailModels) {
        for(OrderDetailModel detailModel: orderDetailModels){
            Log.d("post cont sync", detailModel.toString());
        }
        iSynchronizeOrderDetail.onPostContSyncOrderDetail(orderDetailModel, orderDetailModels);
    }
}
