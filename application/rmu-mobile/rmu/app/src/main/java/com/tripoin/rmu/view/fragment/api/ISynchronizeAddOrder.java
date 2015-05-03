package com.tripoin.rmu.view.fragment.api;

import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.OrderDetailModel;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 4:45 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface ISynchronizeAddOrder {

    public void onPostFirstSyncOrderList(List<OrderDetailModel> orderDetailModels);

    public void onPostContSyncOrderList(List<OrderDetailModel> orderDetailModels);
}
