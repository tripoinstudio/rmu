package com.tripoin.rmu.view.fragment.api;

import com.tripoin.rmu.model.persist.OrderListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 4:45 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public interface ISynchronizeOrderList {

    public void onPostFirstSyncOrderList(List<OrderListModel> orderListModels);

    public void onPostContSyncOrderList(List<OrderListModel> orderListModels);
}
