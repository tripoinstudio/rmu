package com.tripoin.rmu.view.fragment.api;

import com.tripoin.rmu.model.persist.OrderDetailModel;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 9:13 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public interface ISynchronizeOrderDetail {

    public void onPostFirstSyncOrderDetail(OrderDetailModel orderDetailModel, List<OrderDetailModel> orderDetailModels);

    public void onPostContSyncOrderDetail(OrderDetailModel orderDetailModel, List<OrderDetailModel> orderDetailModels);

}
