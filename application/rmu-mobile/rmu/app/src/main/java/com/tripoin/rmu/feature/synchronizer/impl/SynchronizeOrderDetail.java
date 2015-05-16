package com.tripoin.rmu.feature.synchronizer.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.feature.synchronizer.base.ASynchronizeData;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailDTO;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderDetailDBManager;
import com.tripoin.rmu.rest.api.IOrderDetailPost;
import com.tripoin.rmu.rest.impl.OrderDetailListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 7:56 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public abstract class SynchronizeOrderDetail extends ASynchronizeData implements IOrderDetailPost, ISynchronizeOrderDetail {

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private ISynchronizeOrderDetail iSynchronizeOrderDetail;

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
        OrderDetailModel detailModel = OrderDetailDBManager.getInstance().getDataFromQuery(ModelConstant.ORDER_DETAIL_ORDER_HEADER_NO, getOrderHeader());
        if(detailModel == null){
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
        }else{
            List<OrderDetailModel> orderDetailModels1 = OrderDetailDBManager.getInstance().getListDataFromQuery(ModelConstant.ORDER_DETAIL_ORDER_HEADER_NO, getOrderHeader());
            iSynchronizeOrderDetail.onPostSyncOrderDetail(orderDetailModels1);
        }

    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void selectRelatedTable() {
        OrderDetailModel detailModel = OrderDetailDBManager.getInstance().getDataFromQuery(ModelConstant.ORDER_DETAIL_ORDER_HEADER_NO, getOrderHeader());
        if(detailModel == null){
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
        }else{
            List<OrderDetailModel> orderDetailModels1 = OrderDetailDBManager.getInstance().getListDataFromQuery(ModelConstant.ORDER_DETAIL_ORDER_HEADER_NO, getOrderHeader());
            iSynchronizeOrderDetail.onPostSyncOrderDetail(orderDetailModels1);
        }
    }

    public abstract String getOrderHeader();

    @Override
    public void onPostSynchronize(Object objectResult) {
        if(objectResult != null){
            OrderDetailDTO orderDetailDTO = (OrderDetailDTO) objectResult;
            OrderDetailModel orderDetailModel = null;
            List<OrderDetailModel> detailModels = new ArrayList<OrderDetailModel>();
            for(OrderDetailItemDTO itemDTO : orderDetailDTO.getOrderDetailItemDTOs()){
                Log.d("ITEM DTO", itemDTO.toString());
                orderDetailModel = new OrderDetailModel();
                orderDetailModel.setOrderHeaderNo(itemDTO.getOrderHeaderNo());
                orderDetailModel.setOrderDetailTotalOrder(itemDTO.getOrderDetailTotalOrder());
                orderDetailModel.setOrderDetailTotalAmount(itemDTO.getOrderDetailTotalAmount());
                orderDetailModel.setOrderHeaderStatus(itemDTO.getOrderHeaderStatus());
                orderDetailModel.setMenuCode(itemDTO.getMenuCode());
                orderDetailModel.setMenuName(itemDTO.getMenuName());
                orderDetailModel.setSeatCode(itemDTO.getSeatCode());
                orderDetailModel.setCarriageCode(itemDTO.getCarriageCode());
                orderDetailModel.setTrainCode(itemDTO.getTrainCode());
                detailModels.add(orderDetailModel);
                OrderDetailDBManager.getInstance().insertEntity(orderDetailModel);
            }

            iSynchronizeOrderDetail.onPostSyncOrderDetail(detailModels);
        }else{
            Log.d("Sync Orderlist Object Result", "not found");
        }
    }

    @Override
    public void onPostSyncOrderDetail(List<OrderDetailModel> orderDetailModels) {
        iSynchronizeOrderDetail.onPostSyncOrderDetail(orderDetailModels);
    }
}
